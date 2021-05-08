package StepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:Features",
    glue = "classpath:StepDefinitions",
    tags = {"@AddToCart"},
    monochrome = true,
    strict = true
    )
public class TestRunner {}
