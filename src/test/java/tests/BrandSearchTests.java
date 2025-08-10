package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrandSearchPage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

@Epic("Ecommerce Brand Search Tests")
@Feature("Brand Search Functionality")
public class BrandSearchTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private BrandSearchPage brandSearchPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Set up browser, login and navigate to brand search")
    public void setUp(){
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver);
        driver = base.connectBrowser();
        loginPage = new LoginPage(driver);
        brandSearchPage = new BrandSearchPage(driver);
        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");
    }

    @Test(description = "Search and validate the brand 'Polo'")
    @Story("Search brand Polo")
    @Description("Test case to verify that searching for brand 'Polo' returns correct results.")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchBrandPolo(){
        brandSearchPage.searchAndValidateBrand("Polo");
    }

    @Test(description = "Search and validate the brand 'H&M'")
    @Story("Search brand H&M")
    @Description("Test case to verify that searching for brand 'H&M' returns correct results.")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchBrandHM(){
        brandSearchPage.searchAndValidateBrand("H&M");
    }

    @AfterMethod
    @Step("Log out and close browser")
    public void tearDown(){
        loginPage.performLogout();
        driver.quit();
    }

}