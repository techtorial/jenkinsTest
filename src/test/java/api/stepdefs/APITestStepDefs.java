package api.stepdefs;

import api.pojo.PetPojo;
import api.pojo.PokemonAbilityPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APITestStepDefs {
    {
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

    private final Logger LOG = LogManager.getLogger(APITestStepDefs.class);

    private Response response;
    private HttpClient client = HttpClientBuilder.create().build();
    private URIBuilder uriBuilder = new URIBuilder();
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpResponse httpResponse;
    private int petId = 878700;
    private String petName = "TRex";
    private String petStatus = "do not touch";


    @Given("pet is created with following info")
    public void pet_is_created(DataTable table) {
        LOG.info("Creating a pet");
        RestAssured.baseURI = "https://petstore.swagger.io";

        Map<String, String> payloadMap = table.asMaps().get(0);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payloadMap).when().post("v2/pet")
                .then().statusCode(200).log().body();
    }

    @When("user executes {string} request")
    public void user_executes_request(String request) {
        response = given().accept(ContentType.JSON).when().get("v2/pet/8787");
        LOG.info("Get request is executed");
    }

    @Then("status code is {int}")
    public void status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("pet has following attributes")
    public void pet_has_following_attributes(io.cucumber.datatable.DataTable dataTable) {
        LOG.info("Verifying pet attributes");
        PetPojo parsedPet = response.as(PetPojo.class);
        List<Map<String, String>> maps = dataTable.asMaps();
        Map<String, String> petData = maps.get(0);
        String petName = petData.get("petName");
        String petStatus = petData.get("petStatus");
        int petId = Integer.parseInt(petData.get("petId"));

        assertEquals(parsedPet.getStatus(), petStatus);
        assertEquals(parsedPet.getName(), petName);
        assertEquals(parsedPet.getId(), petId);
    }

    @When("pet is created")
    public void createPetTest() throws URISyntaxException, IOException {
        LOG.info("Pet is created");
        client = HttpClientBuilder.create().build();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader("accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");


        String postBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 700,\n" +
                "    \"name\": \"German Shepard\"\n" +
                "  },\n" +
                "  \"name\": \"" + petName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + petStatus + "\"\n" +
                "}";


        HttpEntity entity = new StringEntity(postBody);
        httpPost.setEntity(entity);

        httpResponse = client.execute(httpPost);

        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        assertTrue(httpResponse.getEntity().getContentType().getValue().contains("application/json"));
    }

    @And("pet must have following attributes")
    public void pet_must_have(DataTable table) throws IOException {

        Map<String, Object> deserializedResponse = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        String actualPetName = (String) deserializedResponse.get("name");
        int actualPetId = (int) deserializedResponse.get("id");
        String actualPetStatus = (String) deserializedResponse.get("status");

        assertEquals(actualPetId, petId);
        assertEquals(actualPetName, petName);
        assertEquals(actualPetStatus, petStatus);
    }

    @Then("status code must be {int}")
    public void status_code_must_be(int status) {
        assertEquals(httpResponse.getStatusLine().getStatusCode(), status);
    }


    @When("user sends get request")
    public void getAbilityTest() throws URISyntaxException, IOException {
        uriBuilder.setScheme("https")
                .setHost("pokeapi.co")
                .setPath("api/v2/ability");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept", "application/json");

        httpResponse = client.execute(httpGet);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);


        //deserialization/parsing
        ObjectMapper objectMapper = new ObjectMapper();

        PokemonAbilityPojo abilityPojo = objectMapper.readValue(httpResponse.getEntity().getContent(),
                PokemonAbilityPojo.class);

        System.out.println(abilityPojo.getNext());
        System.out.println(abilityPojo.getCount());
    }


    @When("user sends get request for GOT")
    public void getCharactersNamesTest() throws URISyntaxException, IOException {
        uriBuilder.setScheme("https").setHost("api.got.show").setPath("api/show/characters");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.addHeader("accept", "application/json");

        httpResponse = client.execute(get);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        assertTrue(httpResponse.getEntity().getContentType().getValue().contains("application/json"));

        List<Map<String, Object>> deserilizedRespList = objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<List<Map<String, Object>>>() {
        });

        List<String> characterNames = new ArrayList<>();

        for (int i = 0; i < deserilizedRespList.size(); i++) {
            Map<String, Object> map = deserilizedRespList.get(i);
            characterNames.add(map.get("name").toString());
        }

        assertTrue(characterNames.size() > 100);
    }
}
