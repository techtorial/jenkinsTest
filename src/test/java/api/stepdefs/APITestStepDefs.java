package api.stepdefs;

import api.pojo.PetPojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITestStepDefs {

    Response response;


    @Given("pet is created with following info")
    public void pet_is_created(DataTable table) {
        RestAssured.baseURI = "https://petstore.swagger.io";

        Map<String, String> payloadMap = table.asMaps().get(0);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payloadMap).when().post("v2/pet")
                .then().statusCode(200).log().all();
    }

    @When("user executes {string} request")
    public void user_executes_request(String request) {
        response = given().accept(ContentType.JSON).when()
                .get("v2/pet/8787");
    }

    @Then("status code is {int}")
    public void status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("pet has following attributes")
    public void pet_has_following_attributes(io.cucumber.datatable.DataTable dataTable) {

        PetPojo parsedPet = response.as(PetPojo.class);
        List<Map<String, String>> maps = dataTable.asMaps();
        Map<String, String> petData = maps.get(0);
        String petName = petData.get("petName");
        String petStatus = petData.get("petStatus");
        int petId = Integer.parseInt(petData.get("petId"));

        Assert.assertEquals(parsedPet.getStatus(), petStatus);
        Assert.assertEquals(parsedPet.getName(), petName);
        Assert.assertEquals(parsedPet.getId(), petId);
    }

}
