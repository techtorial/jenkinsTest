package api.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

/**
 * Created by nurkulov 12/30/20
 */
public class Hooks {


    @Before
    public void setup() {
        System.out.println("Before hook has been executed");
        RestAssured.baseURI = "https://petstore.swagger.io";
    }

    @After
    public void cleanup() {
        System.out.println("After hook has been executed");
    }


}
