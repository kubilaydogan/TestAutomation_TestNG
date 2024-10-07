package Framework.Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/*
     Driver class is responsible for creating and managing WebDriver instances.
     Key features:
       - Singleton Pattern:  Line 25, combined with the getDriver method, ensures that only one instance
                                          of WebDriver is created per thread.
       - Thread Safety: ThreadLocal usage to make WebDriver instance thread-safe.
       - Configuration Management: Reads browser type from a configuration file.
       - Exception Handling: Improved exception handling for unsupported browsers.
*/
public class Driver extends CoreObjects {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigurationReader.get("browser");
            driver.set(createDriver(browser));
        }
        return driver.get();
    }

    private static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "chromeheadless":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "firefoxheadless":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(new FirefoxOptions().addArguments("--headless"));
                break;
            case "ie":
                if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                    throw new WebDriverException("Your OS doesn't support Internet Explorer");
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                    throw new WebDriverException("Your OS doesn't support Edge");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                    throw new WebDriverException("Your OS doesn't support Safari");
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Illegal browser type!");
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}