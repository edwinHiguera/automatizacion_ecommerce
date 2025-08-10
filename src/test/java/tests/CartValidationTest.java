package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrandSearchPage;
import pages.LoginPage;
import pages.CartValidationPage;
import pages.ViewAddProductPage;
import utils.BaseTest;
import utils.ConfigReader;

@Epic("Ecommerce Cart Validation Tests")
@Feature("Cart Product Validation")
public class CartValidationTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private BrandSearchPage brandSearchPage;
    private ViewAddProductPage viewAddProductPage;
    private CartValidationPage cartValidationPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Setup browser and login")
    public void setUp(){
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver);
        driver = base.connectBrowser();
        loginPage = new LoginPage(driver);
        brandSearchPage = new BrandSearchPage(driver);
        viewAddProductPage = new ViewAddProductPage(driver);
        cartValidationPage = new CartValidationPage(driver);
        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");
    }

    @Test(description = "Validate adding a Polo T-shirt to the cart and verify quantity")
    @Story("Add Polo T-shirt and validate in cart")
    @Description("This test clears the cart, searches for brand Polo, adds a green Polo T-shirt, and validates product and quantity in the cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidateCartPoloTshirt(){
        cartValidationPage.clearCart();
        brandSearchPage.searchAndValidateBrand("Polo");
        viewAddProductPage.viewDetailsAndAddProduct("29", "Green Side Placket Detail T-Shirt", "2");
        cartValidationPage.validateCartProduct("Green Side Placket Detail T-Shirt", "2");
    }

    @AfterMethod
    @Step("Close browser")
    public void tearDown(){
        driver.quit();
    }
}