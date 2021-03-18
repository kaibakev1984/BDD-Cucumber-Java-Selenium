package StepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:Features",
    glue = "classpath:StepDefinitions",
    tags = {"@WomenBlock, @Screenshot"}
    // "classpath:Features"
    )
public class TestRunner {}
