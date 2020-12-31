package api.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Created by nurkulov 12/30/20
 */
public class TechtorialTestSteps {

    @When("first test executed")
    public void first_test_executed() {
        System.out.println("First step executed");
        waitFor4Sec();
    }


    @Then("do verification")
    public void do_verification() {
        System.out.println("Second step verified");
        waitFor4Sec();
    }


    @When("second test executed")
    public void second_test_executed() {
        System.out.println("Second test executed");
        waitFor4Sec();
    }

    @Then("do second verification")
    public void do_second_verification() {
        System.out.println("Second step verified");
        waitFor4Sec();
    }


    @When("third test executed")
    public void third_test_executed() {
        System.out.println("Third step executed");
        waitFor4Sec();
    }

    @Then("do third verification")
    public void do_third_verification() {
        System.out.println("Third step verified");
        waitFor4Sec();
    }


    @When("fourth test executed")
    public void fourth_test_executed() {
        System.out.println("Fourth test executed");
        waitFor4Sec();
    }


    @Then("do fourth verification")
    public void do_fourth_verification() {
        System.out.println("Fourth step verified");
        waitFor4Sec();
    }


    private static void waitFor4Sec() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


}
