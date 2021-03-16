package Functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.apache.log4j.Logger;

public class WebDriverFactory {
    static String resourceFolder = "src/test/java/Software/";

    private static Logger log = Logger.getLogger(String.valueOf(WebDriverFactory.class));
    private static WebDriverFactory instance = null;

    private WebDriverFactory() {}

    public static WebDriverFactory getInstance() {
        if (instance == null) {
            instance = new WebDriverFactory();
        }
        return instance;
    }


    public static WebDriver createNewWebDriver(String browser, String os){
        WebDriver driver;

        /******** The driver selected is Local: Firefox  ********/
        if ("FIREFOX".equalsIgnoreCase(browser)) {
            if("WINDOWS".equalsIgnoreCase(os)){
                System.setProperty("webdriver.gecko.driver", resourceFolder+os+"/geckodriver.exe");
            }
            else{
                System.setProperty("webdriver.gecko.driver", resourceFolder+os+"/geckodriver");
            }
            driver = new FirefoxDriver();
        }

        /******** The driver selected is Chrome  ********/
        /*  Atención: Acá le sacamos la variable "os" que iba en el set properties. Originalmente iba así:
        *   System.setProperty("webdriver.chrome.driver", resourceFolder + os + "/chromedriver.exe")
        * */
        else if ("CHROME".equalsIgnoreCase(browser)) {
            if("WINDOWS".equalsIgnoreCase(os)){
                System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver.exe");
            }
            else{
                System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver");
            }
            driver = new ChromeDriver();

        }

        /******** The driver selected is Internet Explorer ********/
        else if ("INTERNET EXPLORER".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.ie.driver", resourceFolder+"/IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        }
        /******** The driver is not selected  ********/
        else {
            log.error("The Driver is not selected properly, invalid name: " + browser + ", " + os);
            return null;
        }
        driver.manage().window().maximize();
        return driver;

    }
}
