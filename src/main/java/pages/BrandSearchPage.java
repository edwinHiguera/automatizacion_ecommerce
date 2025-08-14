package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.BaseTest;

public class BrandSearchPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public BrandSearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators

    By productsLink = By.xpath("//a[@href='/products']");
    By productsOnScreen = By.cssSelector(".product-image-wrapper");

    // ***** Methods ******

    // Navigate to products and brand, then scroll
    public void goToProductsBrand(By brandLocator){
        waitForClickable(productsLink, 5);
        click(productsLink);

        waitForVisibility(brandLocator, 5);
        scrollToElement(brandLocator);

        waitForClickable(brandLocator, 5);
        click(brandLocator);
    }

    // Validate number of products found on screen
    public int getDisplayedProductsCount() {
        waitForVisibility(productsOnScreen, 5);
        return driver.findElements(productsOnScreen).size();
    }

    public void searchAndValidateBrand(String brandName) {

        By brandLocator = By.cssSelector("a[href='/brand_products/" + brandName + "']"); // Create brand locator without spaces
        goToProductsBrand(brandLocator); // Use generic method

        // Validate title
        By titleLocator = By.xpath("//h2[text()='Brand - " + brandName + " Products']"); // Brand label
        WebElement title = waitForVisibility(titleLocator, 5);
        Assert.assertTrue(title.getText().equalsIgnoreCase("Brand - " + brandName + " Products"), "Title validation failed."); // Compare brand

        // Validate products
        Assert.assertTrue(getDisplayedProductsCount() > 0, "No products found.");

        // Validate URL
        String expectedBrandURL = "https://automationexercise.com/brand_products/" + brandName.replace(" ", "%20"); // Replace spaces in URL
        assertCurrentUrl(expectedBrandURL);
        System.out.println("prueba qa demo");
    }

}