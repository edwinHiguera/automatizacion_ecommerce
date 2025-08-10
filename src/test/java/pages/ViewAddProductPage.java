package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BaseTest;

public class ViewAddProductPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public ViewAddProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators

    By productQuantityInput = By.id("quantity");
    By addProductButton = By.cssSelector("button[class='btn btn-default cart']");
    By productAddedMessage = By.xpath("//h4[text()='Added!']");

    // Methods

    // *** Method to view product details and add product ***

    public void viewDetailsAndAddProduct(String productId, String expectedTitle, String productQuantity) {

        By productLocator = By.cssSelector("a[href='/product_details/" + productId + "']"); // Create product locator without spaces

        waitForVisibility(productLocator, 5);
        scrollToElement(productLocator);
        waitForClickable(productLocator, 5);
        click(productLocator);

        // Validate URL
        assertCurrentUrl("https://automationexercise.com/product_details/" + productId);

        // Validate product image
        By productImage = By.cssSelector("img[src='/get_product_picture/" + productId + "']");
        waitForVisibility(productImage, 5);
        Assert.assertTrue(isDisplayed(productImage), "Product image is not displayed");

        // Validate product title
        By productTitle = By.cssSelector(".product-information > h2"); // grabs h2 element on the page
        waitForVisibility(productTitle, 5);
        String actualTitle = getText(productTitle).trim(); // trim removes extra spaces
        Assert.assertEquals(actualTitle, expectedTitle, "Product title does not match");

        // Enter product quantity
        waitForVisibility(productQuantityInput, 5);
        waitForClickable(productQuantityInput, 5);
        clear(productQuantityInput);
        sendKeys(productQuantity, productQuantityInput);

        // Add product to cart
        waitForVisibility(addProductButton, 5);
        waitForClickable(addProductButton, 5);
        click(addProductButton);

        // Validate product added message
        waitForVisibility(productAddedMessage, 5);
        Assert.assertTrue(isDisplayed(productAddedMessage), "Error adding product");
    }
}