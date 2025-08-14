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


@Epic("Ecommerce Checkout and Payment")
@Feature("Payment Process")
public class PaymentTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private BrandSearchPage brandsPage;
    private ViewAddProductPage viewAddProductPage;
    private CartValidationPage cartValidationPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Setup browser, open URL and login")
    public void setup() {
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver);
        driver = base.connectBrowser();
        loginPage = new LoginPage(driver);
        brandsPage = new BrandSearchPage(driver);
        viewAddProductPage = new ViewAddProductPage(driver);
        cartValidationPage = new CartValidationPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);

        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");
    }

    @Test(description = "Test payment process for Polo T-shirt")
    @Story("Make payment after adding product to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Clear cart, search brand, add product, validate cart and shipping, then enter payment card details")
    public void testPaymentPoloTshirt() {
        cartValidationPage.clearCart();
        brandsPage.searchAndValidateBrand("Polo");
        viewAddProductPage.viewDetailsAndAddProduct("29", "Green Side Placket Detail T-Shirt", "2");
        cartValidationPage.validateCartProduct("Green Side Placket Detail T-Shirt", "2");
        checkoutPage.validateShippingDetailsAndProduct("Mr. Edwin Higuera", "Australia",
                "Green Side Placket Detail T-Shirt", "2");

        paymentPage.enterCardDetails(
                config.getProperty("cardName"),
                config.getProperty("cardNumber"),
                config.getProperty("cardCVC"),
                config.getProperty("cardExpiryMonth"),
                config.getProperty("cardExpiryYear")
        );
    }

    @AfterMethod
    @Step("Close browser")
    public void tearDown() {
        driver.quit();
    }
}