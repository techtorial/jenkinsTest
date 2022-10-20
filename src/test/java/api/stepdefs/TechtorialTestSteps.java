package api.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by nurkulov 12/30/20
 */
public class TechtorialTestSteps {
    {
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

    private final Logger LOG = LogManager.getLogger(TechtorialTestSteps.class);

    @When("first test executed")
    public void first_test_executed() {
        LOG.info("First step executed");
        waitFor2Sec();
    }

    @Then("do verification")
    public void do_verification() {
        LOG.info("Second step verified");
        waitFor2Sec();
    }

    @When("second test executed")
    public void second_test_executed() {
        LOG.info("Second test executed");
        waitFor2Sec();
    }

    @Then("do second verification")
    public void do_second_verification() {
        LOG.info("Second step verified");
        waitFor2Sec();
    }

    @When("third test executed")
    public void third_test_executed() {
        LOG.info("Third step executed");
        waitFor2Sec();
    }

    @Then("do third verification")
    public void do_third_verification() {
        LOG.info("Third step verified");
        waitFor2Sec();
    }

    @When("fourth test executed")
    public void fourth_test_executed() {
        LOG.info("Fourth test executed");
        waitFor2Sec();
    }

    @Then("do fourth verification")
    public void do_fourth_verification() {
        LOG.info("Fourth step verified");
        waitFor2Sec();
    }

    private void waitFor2Sec() {
        try {
            LOG.info("Waiting for 2 seconds");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
