package api.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/report.json"},
        features = "src/test/java/resources/features",
        glue = "api/stepdefs",
        tags = "@get or @post",
        dryRun = false)
public class APITestRunner {
}
