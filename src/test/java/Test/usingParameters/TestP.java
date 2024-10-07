package Test.usingParameters;

import Framework.Core.BaseTest;
import Framework.Core.Constants;
import Framework.Pages.LoginPage;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({ExtentITestListenerAdapter.class})
public class TestP extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(TestP.class);
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
    }

    // this test can run only with the xml file as the params are defined there
    @Parameters({ "username", "password" })
    @Test
    public void parametersTest(String username, String password) {
        loginPage.login(username, password);
        assertEquals(driver.getCurrentUrl(), Constants.EXPECTED_URL);
        log.info("{} has logged in", username);
    }

}
