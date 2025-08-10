package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseTest;

public class ManualSearchPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public ManualSearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators

    By productsLink = By.xpath("//a[@href='/products']");
    By productsOnScreen = By.cssSelector(".product-image-wrapper");
    By searchBox = By.id("search_product");
    By searchButton = By.id("submit_search");
    By searchResultsHeader = By.xpath("//h2[text()='Searched Products']");

    // Confirm selected category URL
    public boolean validateManualUrl(String expectedProductsUrl) {
        return driver.getCurrentUrl().contains(expectedProductsUrl);
    }

    // Validate search results products count on screen
    public int getSearchResultsCount() {
        waitForVisibility(productsOnScreen, 5);
        return driver.findElements(productsOnScreen).size();
    }

    // Validate search results title
    public boolean validateSearchTitle() {
        WebElement title = waitForVisibility(searchResultsHeader, 5);
        return title.getText().equalsIgnoreCase("Searched Products");
    }

    // Check if no products are displayed
    public boolean noProductsFound() {
        return driver.findElements(productsOnScreen).isEmpty();
    }

    // Search by text box
    public void searchByTextBox(String product){
        waitForClickable(productsLink, 5);
        click(productsLink);
        waitForVisibility(searchBox, 5);
        clear(searchBox);
        sendKeys(product, searchBox);
        click(searchButton);
    }

}
