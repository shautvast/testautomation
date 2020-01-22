package nl.sander.testautomation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Example CucumberRunner
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"src/test/java/nl/sander/testautomation/tests"},
        glue = {"nl.sander.testautomation.steps"},
        plugin = {"pretty", "html:Reports/cucumber-pretty"}
)
public class CucumberTests {

}
