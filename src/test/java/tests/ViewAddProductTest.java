package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrandSearchPage;
import pages.LoginPage;
import pages.ViewAddProductPage;
import utils.BaseTest;
import utils.ConfigReader;

@Epic("Ecommerce Product Browsing")
@Feature("View and Add Product to Cart")
public class ViewAddProductTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private BrandSearchPage brandSearchPage;
    private ViewAddProductPage viewAddProductPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Setup browser, open login page and perform login")
    public void setUp() {
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver);
        driver = base.connectBrowser();
        loginPage = new LoginPage(driver);
        brandSearchPage = new BrandSearchPage(driver);
        viewAddProductPage = new ViewAddProductPage(driver);

        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");
    }

    @Test(description = "Search Polo brand, view details and add product to cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Add product from brand search results")
    @Description("Search for Polo brand, view details of product with id 29 and add 2 units to cart")
    public void testViewAddPoloTshirt() {
        brandSearchPage.searchAndValidateBrand("Polo");
        viewAddProductPage.viewDetailsAndAddProduct("29", "Green Side Placket Detail T-Shirt", "2");
    }

    @AfterMethod
    @Step("Close browser session")
    public void tearDown() {
        driver.quit();
    }
}