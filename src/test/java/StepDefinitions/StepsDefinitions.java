package StepDefinitions;

import Functions.CreateDriver;
import Functions.SeleniumFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gherkin.ast.Scenario;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.Select;

public class StepsDefinitions {
    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    private static String MainAppUrlBase;

    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();

    public static boolean actualState = Boolean.parseBoolean(null);

    Logger log = Logger.getLogger(StepsDefinitions.class);

    public StepsDefinitions() {
        driver = Hooks.driver;
    }

    Scenario scenario = null;
    public void scenario (Scenario scenario) { this.scenario = scenario; }

    @Given("^I am in App main site")
    public void iAmInAppSite() throws IOException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = functions.readProperties("MainAppUrlBase");
        prop.load(in);
        // String url = prop.getProperty("MainAppUrlBase");
        log.info("Navigate to: " + url);
        driver.get(url);
    }

    @Given("^I go to site (.*)")
    public void iGoToSite(String URL) {
        log.info("Navigate to: " + URL);
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^I load the DOM Information (.*)$")
    public void iLoadTheDOMInformationGaliciaJson(String json) throws Exception {
        SeleniumFunctions.FileName = json;
        SeleniumFunctions.readJson();
        log.info("initialize file: " + json);
    }

    @Then("^I do a click in element (.*)")
    public void iDoAClickInElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        functions.waitForClickableElement(element);
        driver.findElement(SeleniumElement).click();
        log.info("Click on element by " + element);
    }

    @Then("^I set (.*) with text (.*)")
    public void iSetWithText(String element, String text) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);
    }

    @And("^I put (.*) in element (.*)")
    public void iPutInElement(String text, String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);
        driver.findElement(SeleniumElement).submit();
    }

    @Given("^I set (.*) value in Data Scenario")
    public void iSetUserEmailValueInDataScenario(String parameter) throws IOException {
        functions.RetriveTestData(parameter);
    }

    @And("I save text of (.*) as Scenario Context")
    public void iSaveTextOfTituloAsScenarioContext(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        String ScenarioElementText = driver.findElement(SeleniumElement).getText();
        functions.SaveInScenario(element + ".text", ScenarioElementText);
    }

    @And("I set (.*) with key value (.*)")
    public void iSetEmailWithKeyValueTituloText(String element, String key) throws Exception {
        functions.iSetElementWithKeyValue(element, key);
    }

    @And("^I set text (.*) in dropdown (.*)")
    public void iSetTextInDropdown(String option, String element) throws Exception {
        Select opt = (Select) functions.selectOption(element);
        opt.selectByVisibleText(option);
    }

    @And("^I set index (.*) in dropdown (.*)")
    public void iSetIndexInDropdown(int option, String element) throws Exception {
        Select opt = (Select) functions.selectOption(element);
        opt.selectByIndex(option);
    }

    @Then("I close the window")
    public void iCloseTheWindow() {
        log.info("Closing windows");
        driver.close();
    }


    @Then("I check if (.*?) error message is (.*)")
    public void iCheckIfErrorMessageIs(String element, String state) throws Exception {
        actualState = functions.isElementDisplayed(element);
        Assert.assertEquals("El estado es diferente al esperado", actualState, Boolean.valueOf(state));
    }

    @And("^I switch to Frame: (.*)")
    public void iSwitchToFrame(String frame) throws Exception {
        functions.switchToFrame(frame);
    }

    @And("^I switch to parent frame")
    public void iSwitchToParentFrame() {
        functions.switchToParentFrame();
    }

    @And("^I check the checkbox having (.*)$")
    public void iCheckTheCheckboxHavingFrameInput(String element) throws Exception {
        functions.checkCheckbox(element);
    }
}
