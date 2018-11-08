package tests.init;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.config.DriverTools;
import tests.config.StaticParameters;
import tests.logic.Common;

import static com.codeborne.selenide.Selenide.open;

public class LoginInit {
    public static WebDriver driver;
    static String url;

    @Parameters({"baseUrl", "browser", "login", "password"})
    @BeforeSuite
    public static void setUp(String baseUrl, String browser, String login, String password) throws Exception {
        StaticParameters.BROWSER = browser;
        StaticParameters.BASE_URL = baseUrl;
        StaticParameters.LOGIN = login;
        StaticParameters.PASSWORD = password;

        driver = DriverTools.createDriver(browser);
        driver.manage().window().maximize();

        WebDriverRunner.setWebDriver(driver);

        if (baseUrl != null) {
            url = baseUrl;
            open(url);
        } else {
            throw new RuntimeException("baseUrl is null!");
        }
    }

    @AfterSuite(description = "Close driver")
    public static void tearDown() throws Exception {
        driver.quit();
    }


        @Test(description = "LoginInit on website")
        public static void login () {
            Common.loginAtVk(StaticParameters.LOGIN, StaticParameters.PASSWORD);
        }

    }
