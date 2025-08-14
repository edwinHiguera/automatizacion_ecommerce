package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import pages.*;
import utils.ConfigReader;

@Epic("Ecommerce Checkout")
@Feature("Checkout Process")

public class CheckoutTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private BrandSearchPage brandsPage;
    private ViewAddProductPage viewAddProductPage;
    private CartValidationPage cartValidationPage;
    private CheckoutPage checkoutPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Setup browser and perform login")
    public void setup() {
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver);
        driver = base.connectBrowser();
        loginPage = new LoginPage(driver);
        brandsPage = new BrandSearchPage(driver);
        viewAddProductPage = new ViewAddProductPage(driver);
        cartValidationPage = new CartValidationPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");
    }

    @Test(description = "Complete checkout process for Polo T-shirt")
    @Story("Checkout Polo T-shirt")
    @Description("Clears cart, searches Polo brand, adds product, validates cart and verifies shipping details")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutPoloTshirt() {
        cartValidationPage.clearCart();
        brandsPage.searchAndValidateBrand("Polo");
        viewAddProductPage.viewDetailsAndAddProduct("29", "Green Side Placket Detail T-Shirt", "2");
        cartValidationPage.validateCartProduct("Green Side Placket Detail T-Shirt", "2");
        checkoutPage.validateShippingDetailsAndProduct("Mr. Edwin Higuera", "Australia",
                "Green Side Placket Detail T-Shirt", "2");
    }

    @AfterMethod
    @Step("Close browser")
    public void tearDown() {
        driver.quit();
    }
}