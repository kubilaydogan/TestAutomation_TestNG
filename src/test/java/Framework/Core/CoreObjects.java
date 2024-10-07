package Framework.Core;

import Framework.Utilities.Selenium.CustomWait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CoreObjects extends BaseDataProvider{

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static CustomWait customWait;
    public static Alert alert;
    public static Actions actions;
    public static JavascriptExecutor js;
    public static Duration TIMEOUT;
}
