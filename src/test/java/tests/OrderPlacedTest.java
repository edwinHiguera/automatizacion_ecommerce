package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.ConfigReader;

import java.io.IOException;

@Epic("Ecommerce Checkout and Payment")
@Feature("Order Placement")
public class OrderPlacedTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private BrandSearchPage brandsPage;
    private ViewAddProductPage viewAddProductPage;
    private CartValidationPage cartValidationPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private OrderPlacedPage placedPage;
    private ConfigReader config;

    @BeforeMethod
    @Step("Setup browser, open URL and login")
    public void setup() {
        config = new ConfigReader();
        BaseTest base = new BaseTest(driver); // Creates a base instance to open the browser

        driver = base.connectBrowser(); // Initializes the driver
        loginPage = new LoginPage(driver);
        brandsPage = new BrandSearchPage(driver);
        viewAddProductPage = new ViewAddProductPage(driver);
        cartValidationPage = new CartValidationPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
        placedPage = new OrderPlacedPage(driver);

        loginPage.openUrl(config.getProperty("baseUrlLogin"));
        driver.manage().window().maximize();
        loginPage.performLogin(config.getProperty("userEmail"), config.getProperty("userPassword"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");
    }

    @Test(description = "Place order for Polo T-shirt and validate order placement")
    @Story("Complete purchase flow with payment")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Clears cart, searches brand, adds product, validates cart and shipping, makes payment and verifies order placed")
    public void testPaymentPoloTshirt() throws IOException {
        cartValidationPage.clearCart();
        brandsPage.searchAndValidateBrand("Polo");
        viewAddProductPage.viewDetailsAndAddProduct("29", "Green Side Placket Detail T-Shirt", "2");
        cartValidationPage.validateCartProduct("Green Side Placket Detail T-Shirt", "2");
        checkoutPage.validateShippingDetailsAndProduct("Mr. Edwin Higuera",
                "Australia", "Green Side Placket Detail T-Shirt", "2");

        paymentPage.enterCardDetails(
                config.getProperty("cardName"),
                config.getProperty("cardNumber"),
                config.getProperty("cardCVC"),
                config.getProperty("cardExpiryMonth"),
                config.getProperty("cardExpiryYear")
        );
        placedPage.validateOrder();
    }

    @AfterMethod
    @Step("Close browser")
    public void tearDown() {
        driver.quit();
    }
}