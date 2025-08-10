package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategorySearchPage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

@Epic("Ecommerce Category Search Tests")
@Feature("Search by Category")
public class CategorySearchTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private CategorySearchPage categorySearchPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Setup browser and login")
    public void setUp(){
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver);
        driver = base.connectBrowser();
        loginPage = new LoginPage(driver);
        categorySearchPage = new CategorySearchPage(driver);
        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");
    }

    @Test(description = "Search and validate Women Dress category")
    @Story("Search Women Dress category and validate results")
    @Description("Searches Women Dress category, validates URL, title, and product count > 0")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchWomenDress(){
        categorySearchPage.searchWomenDress();
        Assert.assertTrue(categorySearchPage.validateCategoryUrl("category_products/1"), "Search URL validation failed.");
        Assert.assertTrue(categorySearchPage.validateWomenDressTitle(), "Search title validation failed.");

        int productCount = categorySearchPage.getDisplayedProductsCount();
        Assert.assertTrue(productCount > 0, "No products found.");
    }

    @Test(description = "Search and validate Women Tops category")
    @Story("Search Women Tops category and validate results")
    @Description("Searches Women Tops category, validates URL, title, and product count > 0")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchWomenTops(){
        categorySearchPage.searchWomenTops();
        Assert.assertTrue(categorySearchPage.validateCategoryUrl("category_products/2"), "Search URL validation failed.");
        Assert.assertTrue(categorySearchPage.validateWomenTopsTitle(), "Search title validation failed.");

        int productCount = categorySearchPage.getDisplayedProductsCount();
        Assert.assertTrue(productCount > 0, "No products found.");
    }

    @AfterMethod
    @Step("Close browser")
    public void tearDown(){
        driver.quit();
    }

}