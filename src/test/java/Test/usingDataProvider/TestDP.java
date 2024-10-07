package Test.usingDataProvider;

import Framework.Core.BaseDataProvider;
import Framework.Core.BaseTest;
import Framework.Core.Constants;
import Framework.Pages.LoginPage;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({ExtentITestListenerAdapter.class})
public class TestDP extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Data is defined in BaseDataProvider.java
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test(dataProvider = "valid-authentication", dataProviderClass = BaseDataProvider.class)
    public void loginToTheWebsite(String username, String password) {
        loginPage.login(username, password);
        assertEquals(driver.getCurrentUrl(), Constants.EXPECTED_URL);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test(dataProvider = "data-provider")
    public void testDataProvider(String username, String password) {
        loginPage.login(username, password);
        assertEquals(driver.getCurrentUrl(), Constants.EXPECTED_URL);
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"standard_user", "secret_sauce"}};
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


}