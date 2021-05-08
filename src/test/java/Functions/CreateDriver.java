package Functions;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class CreateDriver {
    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");

    private static String properties = "test.properties";
    private static String browser;
    private static String logLevel;

    private static Logger log = Logger.getLogger(String.valueOf(CreateDriver.class));

    private CreateDriver() { CreateDriver.initConfig(); }

    public static WebDriver initConfig(){
        WebDriver driver;

        try {
            log.info("*******************************************************************************************************");
            log.info("[ POM Configuration ] - Read the basic properties configuration from: " + properties);
            prop.load(in);
            browser = prop.getProperty("browser");
            logLevel = prop.getProperty("logLevel");

        } catch (IOException e) {
            log.error("initConfig Error", e);
        }

        log.info("[ POM Configuration ] - " + " Browser: " + browser + " |");
        log.info("[ POM Configuration ] - Logger Level: " + logLevel);
        log.info("*******************************************************************************************************");

        driver = WebDriverFactory.createNewWebDriver(browser);

        return driver;
    }
}
