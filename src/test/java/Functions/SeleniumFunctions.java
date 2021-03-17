package Functions;
import StepDefinitions.Hooks;
import io.qameta.allure.Allure;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;

public class SeleniumFunctions {

    WebDriver driver;
    public SeleniumFunctions() { driver = Hooks.driver; }

    /********* Page Path *********/
    public static String FileName = "";
    public static String PagesFilePath = "src/test/resources/Pages/";

    /********* Scenario Test Data *********/
    public static Map<String, String> scenarioData = new HashMap<>();
    public static String Environment = "";
    public static String elementText = "";
    public static boolean isDisplayed = Boolean.parseBoolean(null);

    /********* DOM pages/json *********/
    public static String GetFieldBy = "";
    public static String ValueToFind = "";

    /********* Explicit Waits *********/
    public static int EXPLICIT_TIMEOUT = 15;

    /********* test properties config *********/
    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");

    /********* Log Attribute *********/
    private static Logger log = Logger.getLogger(CreateDriver.class);

    public static Object readJson() throws Exception {
        FileReader reader = new FileReader(PagesFilePath + FileName);
        try {
            if (reader != null) {
                JSONParser jsonParser = new JSONParser();
                return jsonParser.parse(reader);
            } else {
                return null;
            }
        } catch (FileNotFoundException | NullPointerException e) {
            log.error("ReadEntity: File doesn't exist " + FileName);
            throw new IllegalStateException("ReadEntity: File doesn't exist " + FileName, e);
        }
    }

    public static JSONObject ReadEntity(String element) throws Exception {
        JSONObject Entity = null;
        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        log.info(Entity.toJSONString());
        return Entity;
    }

    public static By getCompleteElement(String element) throws Exception {
        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind = (String) Entity.get("ValueToFind");

        if ("className".equalsIgnoreCase(GetFieldBy)) {
            result = By.className(ValueToFind);
        } else if ("cssSelector".equalsIgnoreCase(GetFieldBy)) {
            result = By.cssSelector(ValueToFind);
        } else if ("id".equalsIgnoreCase(GetFieldBy)) {
            result = By.id(ValueToFind);
        } else if ("linkText".equalsIgnoreCase(GetFieldBy)) {
            result = By.linkText(ValueToFind);
        } else if ("name".equalsIgnoreCase(GetFieldBy)) {
            result = By.name(ValueToFind);
        } else if ("link".equalsIgnoreCase(GetFieldBy)) {
            result = By.partialLinkText(ValueToFind);
        } else if ("tagName".equalsIgnoreCase(GetFieldBy)) {
            result = By.tagName(ValueToFind);
        } else if ("xpath".equalsIgnoreCase(GetFieldBy)) {
            result = By.xpath(ValueToFind);
        }
        return result;
    }

    public String readProperties(String property) throws IOException {
        prop.load(in);
        return prop.getProperty(property);
    }

    public void SaveInScenario(String key, String text) {
        if (!this.scenarioData.containsKey(key)) {
            this.scenarioData.put(key, text);
            log.info(String.format("Save as Scenario Context key: %s with value: %s", key, text));
        } else {
            this.scenarioData.replace(key, text);
            log.info(String.format("Update Scenario Context key: %s with value %s", key, text));
        }
    }

    public void RetriveTestData(String parameter) throws IOException {
        Environment = readProperties("Environment");
        try {
            SaveInScenario(parameter, readProperties(parameter + "." + Environment));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iSetElementWithKeyValue(String element, String key) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        boolean exist = this.scenarioData.containsKey(key);
        if (exist){
            String text = this.scenarioData.get(key);
            driver.findElement(SeleniumElement).sendKeys(text);
            log.info(String.format("Set on element %s with text %s", element, text));
        }else{
            Assert.assertTrue(String.format("The given key %s do not exist in Context", key), this.scenarioData.containsKey(key));
        }

    }

    public void selectOptionDropdownByText(String element, String option) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("Waiting Element: %s", element));
        Select opt = new Select(driver.findElement(SeleniumElement));
        log.info("Select option: " + option + " by text");
        opt.selectByVisibleText(option);
    }

    public Select selectOption(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("Waiting Element: %s", element));
        Select opt = new Select(driver.findElement(SeleniumElement));
        return opt;
    }

    public void waitForElementPresent(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        log.info("Waiting fo the element: " + element + " to be present");
        wait.until(ExpectedConditions.presenceOfElementLocated((SeleniumElement)));
    }

    public void waitForElementVisible(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        log.info("Waiting for the element: " + element + " to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(SeleniumElement));
    }

    public void waitForClickableElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        log.info("Waiting for the element: " + element + " to be visible");
        wait.until(ExpectedConditions.elementToBeClickable(SeleniumElement));
    }

    public boolean isElementDisplayed(String element) throws Exception {
        boolean isDisplayed;
        try {
            By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
            log.info(String.format("Waiting Element: %s", element));
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            isDisplayed = false;
            log.info(e);
        }
        log.info(String.format("%s visibility is: %s", element, isDisplayed));
        return isDisplayed;
    }

    public void switchToFrame(String frame) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(frame);
        log.info("Switching to frame: " + frame);
        driver.switchTo().frame(driver.findElement(SeleniumElement));
    }

    public void switchToParentFrame() {
        log.info("Switching to parent frame");
        driver.switchTo().parentFrame();
    }

    public void checkCheckbox(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        boolean isChecked = driver.findElement(SeleniumElement).isSelected();
        if (!isChecked) {
            log.info("Clicking on the checkbox to select: " + element);
            driver.findElement(SeleniumElement).click();
        }
    }

    public void uncheckCheckbox(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        boolean isChecked = driver.findElement(SeleniumElement).isSelected();
        if (isChecked) {
            log.info("Clicking on the checkbox to select: " + element);
            driver.findElement(SeleniumElement).click();
        }
    }

    public void clickJSElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        log.info("Click to element with: " + element);
        jse.executeScript("arguments[0].click()", driver.findElement(SeleniumElement));
    }

    public void scrollPage(String to) throws Exception {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if(to.equals("top")) {
            log.info("Scrolling to the top of the page");
            jse.executeScript("scroll(0,-250);");
        } else if(to.equals("end")) {
            log.info("Scrolling to the end of the page");
            jse.executeScript("scroll(0, 250);");
        }
    }

    public void scrollToElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        log.info("Scrolling to element: " + element);
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(SeleniumElement));

    }

    public void pageHasLoaded() {
        String GetActual = driver.getCurrentUrl();
        log.info(String.format("Checking if %s page is loaded.", GetActual));
        new WebDriverWait(driver, EXPLICIT_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public String GetTextElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
        log.info(String.format("Waiting to the element: %s", element));
        elementText = driver.findElement(SeleniumElement).getText();
        return elementText;

    }

    public void checkPartialTextElementNotPresent(String element,String text) throws Exception {
        elementText = GetTextElement(element);
        boolean isFoundFalse = elementText.indexOf(text) !=-1? true: false;
        Assert.assertFalse("Text is present in element: " + element + " current text is: " + elementText, isFoundFalse);

    }

    public void checkPartialTextElementPresent(String element,String text) throws Exception {
        elementText = GetTextElement(element);
        boolean isFound = elementText.indexOf(text) !=-1? true: false;
        Assert.assertTrue("Text is not present in element: " + element + " current text is: " + elementText, isFound);
    }

    public void acceptAlert(String want) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String statusAlert = "";
            if (want.equals("accept")) {
                alert.accept();
                statusAlert = "accepted";
            } else {
                alert.dismiss();
                statusAlert = "dismissed";
            }
            log.info(String.format("The alert was %s successfully.", statusAlert));
        } catch(Throwable e) {
            log.error("Error came while waiting for the alert popup");
        }
    }

    public void ScreenShot(String TestCaptura) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
        String screenShotName = readProperties("ScreenShotPath") + "\\" + readProperties("browser") + "\\" + TestCaptura + "_(" + dateFormat.format(GregorianCalendar.getInstance().getTime()) + ")";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        log.info("Screenshot saved as:" + screenShotName);
        FileUtils.copyFile(scrFile, new File(String.format("%s.png", screenShotName)));
    }

    public byte[] attachScreenShot(){

        log.info("Attaching Screenshot");
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return screenshot;

    }

}
