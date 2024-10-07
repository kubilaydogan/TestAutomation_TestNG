package Framework.Core.CustomAnnotations;

import Framework.Core.Listeners.SuiteListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@Listeners({SuiteListener.class})
public class TestWithCustomAnnotation {
    private static WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.saucedemo.com/");
    }

    @RerunIfTestFails(2)
    @Test
    public void loginToTheWebsite() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys( "standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
       Thread.sleep(1000);
        assertEquals(driver.getTitle(), "Hello", "Title verification failed!!!");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
