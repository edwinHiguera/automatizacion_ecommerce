package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BaseTest;

public class CheckoutPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    By checkoutBreadcrumbLocator = By.cssSelector("ol.breadcrumb > li.active");
    By addressDetailsHeaderLocator = By.xpath("//h2[@class='heading' and text()='Address Details']");
    By commentTextareaLocator = By.name("message");
    By placeOrderButtonLocator = By.cssSelector("a[href='/payment']");

    // Methods

    // Validate shipping details and products in order

    public void validateShippingDetailsAndProduct(String expectedUserName, String expectedCountry,
                                                  String expectedProductTitle, String expectedProductQuantity) {

        // Validate URL
        assertCurrentUrl("https://automationexercise.com/checkout");

        // Validate breadcrumb path
        validatePageIsDisplayed(checkoutBreadcrumbLocator, "Error: Not on the correct path.");

        // Validate shipping details (you can add more validations here)
        waitForVisibility(addressDetailsHeaderLocator, 5);

        // Validate customer name
        By customerNameLocator = By.cssSelector("ul#address_delivery > li.address_firstname.address_lastname");
        waitForVisibility(customerNameLocator, 5);
        String actualUserName = getText(customerNameLocator);
        Assert.assertEquals(actualUserName, expectedUserName, "Customer name does not match.");

        // Validate country
        By countryLocator = By.cssSelector("ul#address_delivery > li.address_country_name");
        waitForVisibility(countryLocator, 5);
        String actualCountry = getText(countryLocator);
        Assert.assertEquals(actualCountry, expectedCountry, "Customer country does not match.");

        // Validate product details

        // Product title
        By productTitleLocator = By.cssSelector(".cart_description > h4 > a");
        waitForVisibility(productTitleLocator, 5);
        String actualProductTitle = getText(productTitleLocator);
        Assert.assertEquals(actualProductTitle, expectedProductTitle, "Product title does not match.");

        // Product quantity
        By productQuantityLocator = By.cssSelector("td.cart_quantity > button");
        waitForVisibility(productQuantityLocator, 5);
        String actualProductQuantity = getText(productQuantityLocator);
        Assert.assertEquals(actualProductQuantity, expectedProductQuantity, "Product quantity does not match.");

        // Enter comments
        waitForVisibility(commentTextareaLocator, 5);
        scrollToElement(commentTextareaLocator);
        waitForClickable(commentTextareaLocator, 5);
        clear(commentTextareaLocator);
        sendKeys("This is a Selenium test order comment.", commentTextareaLocator);

        // Place order
        waitForVisibility(placeOrderButtonLocator, 5);
        scrollToElement(placeOrderButtonLocator);
        waitForClickable(placeOrderButtonLocator, 5);
        click(placeOrderButtonLocator);
    }
}