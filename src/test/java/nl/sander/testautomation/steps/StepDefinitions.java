package nl.sander.testautomation.steps;


import io.cucumber.java.Before;
import nl.sander.testautomation.Mode;

/**
 * Common stepdefinitions. Used to derive behavior from tags
 */
public class StepDefinitions {

    public static Mode testMode;

    @Before(value = "@DatabaseIntegrationTest", order = 1)
    public void databaseIntegrationTest() {
        testMode = Mode.DATABASE_INTEGRATION_TEST;
    }

//    @Before(value = "@UnitTest", order = 2)
//    public void unitTest() {
//        testMode = Mode.UNIT_TEST;
//    }
//
//    @Before(value = "@UnitIntegrationTest", order = 3)
//    public void withDatabase() {
//        testMode = Mode.UNIT_INTEGRATION_TEST;
//    }
}

