package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ManualSearchPage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

@Epic("Ecommerce Search")
@Feature("Manual Search Functionality")
public class ManualSearchTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ManualSearchPage manualSearchPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Setup browser and perform login")
    public void setUp() {
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver);
        driver = base.connectBrowser();
        loginPage = new LoginPage(driver);
        manualSearchPage = new ManualSearchPage(driver);

        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");
    }

    @Test(description = "Search products by keyword 'tshirt'")
    @Story("Successful product search")
    @Severity(SeverityLevel.NORMAL)
    @Description("Searches for 'tshirt', verifies URL, page title and that products are displayed")
    public void testSearchTShirt() {
        manualSearchPage.searchByTextBox("tshirt");
        Assert.assertTrue(manualSearchPage.validateManualUrl("products?search=tshirt"), "Search URL validation failed.");
        Assert.assertTrue(manualSearchPage.validateSearchTitle(), "Search title validation failed.");

        int productCount = manualSearchPage.getSearchResultsCount();
        Assert.assertTrue(productCount > 0, "No products found.");
    }

    @Test(description = "Search with a keyword that yields no results")
    @Story("Unsuccessful product search")
    @Severity(SeverityLevel.MINOR)
    @Description("Searches for '34782345', verifies URL, page title and that no products are found")
    public void testSearchNoProducts() {
        manualSearchPage.searchByTextBox("34782345");
        Assert.assertTrue(manualSearchPage.validateManualUrl("products?search=34782345"), "Search URL validation failed.");
        Assert.assertTrue(manualSearchPage.validateSearchTitle(), "Search title validation failed.");

        Assert.assertTrue(manualSearchPage.noProductsFound(), "Products were found but should not be.");
    }

    @AfterMethod
    @Step("Close browser")
    public void tearDown() {
        driver.quit();
    }
}