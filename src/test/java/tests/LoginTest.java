package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

@Epic("Authentication Tests")
@Feature("Login and Logout functionality")
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ConfigReader config;


    @BeforeMethod
    @Step("Setup browser and open login page")
    public void setUp(){
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver); // Create BaseTest instance to open browser
        driver = base.connectBrowser(); // Initialize driver
        loginPage = new LoginPage(driver);
        loginPage.openUrl(config.getProperty("baseUrlLogin")); //read url in config
        driver.manage().window().maximize();
    }

    @Test(description = "Successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User logs in with valid credentials")
    @Description("Test verifies that a user can successfully log in using valid email and password.")
    public void successfulLogin(){
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");
    }


    @Test(description = "Failed login with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Story("User tries to login with invalid password")
    @Description("Test verifies that login fails and error message is shown when password is incorrect.")
    public void failedLogin(){
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("wrongPassword"));
        Assert.assertFalse(loginPage.isLoginFailed(), "Error message is not displayed.");
    }

    @Test(description = "Logout after successful login")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User logs out successfully")
    @Description("Test verifies that the user can logout successfully after login.")
    public void logoutTest(){
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        loginPage.performLogout();
        Assert.assertTrue(loginPage.isLogoutSuccessful(), "Logout failed.");
    }

    @AfterMethod
    @Step("Close browser")
    public void tearDown(){
        driver.quit();
    }
}