package StepDefinitions;

import Functions.CreateDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;

public class Hooks {
    public static WebDriver driver;
    Logger log = Logger.getLogger(Hooks.class);
    Scenario scenario = null;

    @Before
    public void initDriver(Scenario scenario) throws MalformedURLException {
        log.info("*******************************************************************************************************");
        log.info("[ Configuration ] - Initializing driver configuration");
        log.info("*******************************************************************************************************");
        this.scenario = scenario;
        driver = CreateDriver.initConfig();
        log.info("*******************************************************************************************************");
        log.info("[ Scenario ] - " + scenario.getName());
        log.info("*******************************************************************************************************");
    }

    
    @After
    public void tearDown() {
        driver.quit();
    }

}
