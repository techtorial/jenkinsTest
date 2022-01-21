package api.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
/**
 * Created by nurkulov 12/30/20
 */
public class Hooks {

    @Before
    public void setup() {
        System.out.println("Before hook has been executed");
    }

    @After
    public void cleanup() {
        System.out.println("After hook has been executed");
    }


}
