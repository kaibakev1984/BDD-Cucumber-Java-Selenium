package Functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.apache.log4j.Logger;

public class WebDriverFactory {
    private final static Logger log = Logger.getLogger(String.valueOf(WebDriverFactory.class));
    private static WebDriverFactory instance = null;

    private WebDriverFactory() {}

    public static WebDriverFactory getInstance() {
        if (instance == null) {
            instance = new WebDriverFactory();
        }
        return instance;
    }


    public static WebDriver createNewWebDriver(String browser){
        WebDriver driver;

        if ("FIREFOX".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        else if ("CHROME".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }

        else if ("INTERNET EXPLORER".equalsIgnoreCase(browser)) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();

        } else if("MICROSOFT EDGE".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            log.error("The Driver is not selected properly, invalid name: " + browser);
            return null;
        }
        driver.manage().window().maximize();
        return driver;

    }
}
