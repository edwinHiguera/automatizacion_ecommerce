package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseTest;

public class CategorySearchPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public CategorySearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators

    By productsLink = By.xpath("//a[@href='/products']");
    By productsOnScreen = By.cssSelector(".product-image-wrapper");

    By womenCategory = By.xpath("//a[@href='#Women']");
    By womenDress = By.cssSelector("div[id='Women']>div[class='panel-body']>ul>li>a[href='/category_products/1']");
    By womenDressResultsHeader = By.xpath("//h2[text()='Women - Dress Products']");

    By womenTops = By.cssSelector("div[id='Women']>div[class='panel-body']>ul>li>a[href='/category_products/2']");
    By womenTopsResultsHeader = By.xpath("//h2[text()='Women - Tops Products']");

    By womenSaree = By.cssSelector("div[id='Women']>div[class='panel-body']>ul>li>a[href='/category_products/7']");
    By womenSareeResultsHeader = By.xpath("//h2[text()='Women - Saree Products']");

    // ****** Methods ******

    // Confirm the selected category URL
    public boolean validateCategoryUrl(String expectedUrl) {
        return driver.getCurrentUrl().contains(expectedUrl);
    }

    // Validate number of products displayed
    public int getDisplayedProductsCount() {
        waitForVisibility(productsOnScreen, 5);
        return driver.findElements(productsOnScreen).size();
    }

    // Navigate to Women category section
    public void navigateToWomenCategory(){
        waitForClickable(productsLink, 5);
        click(productsLink);
        waitForVisibility(womenCategory, 5);
        click(womenCategory);
    }

    // ****** Women Dress Category ******

    public boolean validateWomenDressTitle() {
        WebElement title = waitForVisibility(womenDressResultsHeader, 5);
        return title.getText().equalsIgnoreCase("Women - Dress Products");
    }

    public void searchWomenDress(){
        navigateToWomenCategory();
        waitForClickable(womenDress, 5);
        click(womenDress);
    }

    // ****** Women Tops Category ******

    public boolean validateWomenTopsTitle() {
        WebElement title = waitForVisibility(womenTopsResultsHeader, 5);
        return title.getText().equalsIgnoreCase("Women - Tops Products");
    }

    public void searchWomenTops(){
        navigateToWomenCategory();
        waitForClickable(womenTops, 5);
        click(womenTops);
    }

    // ****** Women Saree Category ******

    public boolean validateWomenSareeTitle() {
        WebElement title = waitForVisibility(womenSareeResultsHeader, 5);
        return title.getText().equalsIgnoreCase("Women - Saree Products");
    }

    public void searchWomenSaree(){
        navigateToWomenCategory();
        waitForClickable(womenSaree, 5);
        click(womenSaree);
    }
}