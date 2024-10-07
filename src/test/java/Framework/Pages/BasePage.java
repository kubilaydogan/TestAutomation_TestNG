package Framework.Pages;

import Framework.Core.ConfigurationReader;
import Framework.Utilities.CommonMethods;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class BasePage extends CommonMethods {
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    public String landingPageURL = ConfigurationReader.get("launchURL");

    public BasePage(WebDriver driver) {
        super();
    }

    public void navigateToHomePage() {
        driver.get(landingPageURL);
    }

    public void verifyHomePageTitle() {
        String expectedTitle = "Home - Example";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Title does not match");
    }

    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = driver -> js.executeScript("return document.readyState").toString().equals("complete");
        try {
            Thread.sleep(1000);
            wait.until(expectation);
        } catch (StaleElementReferenceException | InterruptedException e) {
            log.error("Timeout waiting for Page Load Request to complete.", e);
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

}
