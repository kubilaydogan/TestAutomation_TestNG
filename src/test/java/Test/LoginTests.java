package Test;

import Framework.Core.BaseTest;
import Framework.Pages.LoginPage;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ExtentITestListenerAdapter.class})
public class LoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage(driver);

    @Test(priority = 1, dataProvider = "valid-authentication", description = "Login test with valid credentials", groups = "login")
    public void loginTestWithValidCredentials(String username, String password) {

        loginPage.openLoginPage()
                .verifyTitleIs("Swag Labs")
                .login(username, password)
                .verifySuccessfulLogin();
    }

    @Test(priority = 2, dataProvider = "invalid-authentication", description = "Login test with invalid credentials", groups = { "login", "negative" })
    public void loginTestWithInvalidCredentials(String username, String password, String expectedFailureMessage) {

        loginPage.openLoginPage()
                .login(username, password)
                .verifyFailureMessage(expectedFailureMessage);
    }

}
