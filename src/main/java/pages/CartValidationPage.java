package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BaseTest;

public class CartValidationPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public CartValidationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    By viewCartLink = By.cssSelector("a[href='/view_cart'] u");
    By shoppingCartBreadcrumb = By.cssSelector("ol.breadcrumb > li.active");
    By cartLink = By.xpath("//a[@href='/view_cart' and text()=' Cart']");

    By deleteProductButton = By.className("cart_quantity_delete");
    By checkoutButton = By.cssSelector(".btn.btn-default.check_out");

    // Methods

    // Clear cart
    public void clearCart() {
        waitForVisibility(cartLink, 5);
        waitForClickable(cartLink, 5);
        click(cartLink);

        while (findElements(deleteProductButton) != null && isDisplayed(deleteProductButton)) {
            // use findElements because there can be multiple elements
            waitForClickable(deleteProductButton, 5);
            click(deleteProductButton);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Validate product in cart
    public void validateCartProduct(String expectedTitle, String expectedQuantity){

        // Navigate to shopping cart
        waitForVisibility(viewCartLink, 5);
        waitForClickable(viewCartLink, 5);
        click(viewCartLink);

        // Validate URL
        assertCurrentUrl("https://automationexercise.com/view_cart");

        // Validate breadcrumb path
        validatePageIsDisplayed(shoppingCartBreadcrumb, "Error: Not on the correct page");

        // Validate product title
        By productTitleLocator = By.cssSelector(".cart_description > h4 > a");
        waitForVisibility(productTitleLocator, 5);
        String actualTitle = getText(productTitleLocator);
        Assert.assertEquals(actualTitle, expectedTitle, "Product title does not match.");

        // Validate product quantity
        By productQuantityLocator = By.xpath("//button[text()='" + expectedQuantity + "']");
        waitForVisibility(productQuantityLocator, 5);
        String actualQuantity = getText(productQuantityLocator);
        Assert.assertEquals(actualQuantity, expectedQuantity, "Product quantity does not match.");

        // Proceed to checkout
        waitForVisibility(checkoutButton, 5);
        waitForClickable(checkoutButton, 5);
        click(checkoutButton);
    }
}