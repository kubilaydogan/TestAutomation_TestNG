package Framework.Core;

import Framework.Utilities.CommonMethods;
import Framework.Utilities.Selenium.CustomWait;
import Framework.Utilities.Selenium.Screenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest extends CommonMethods {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeSuite() {
    }

    @BeforeClass
    public void start() {
    }

    @BeforeMethod(alwaysRun = true)
    public void initializeDriver(Method method) {
        log.info("============ TEST STARTED: " + method.getName() + "============");
        try {
            initObjects();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(TIMEOUT);

        } catch (Exception e) {
            log.error("Error initializing driver", e);
            throw e;
        }
    }

    @AfterMethod
    public void quit(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                Screenshot.captureScreenshot(result.getName());
            }
            log.info("Test executed: " + result.getName());
        } catch (Exception e) {
            log.error("Error during test teardown", e);
        } finally {
            Driver.closeDriver();
        }
    }

    @AfterClass
    public void afterClass() {
    }

    @AfterSuite
    public void afterSuite() {
    }

    public void initObjects() {
        driver = Driver.getDriver();
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        TIMEOUT  = Duration.ofSeconds(20);
        wait = new WebDriverWait(driver, TIMEOUT);
        customWait = new CustomWait();

    }
}
