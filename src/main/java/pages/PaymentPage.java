package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BaseTest;

public class PaymentPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    By paymentBreadcrumbLocator = By.cssSelector("ol.breadcrumb > li[class='active']");
    By nameOnCardInput = By.cssSelector("input[data-qa='name-on-card']");
    By cardNumberInput = By.cssSelector("input[data-qa='card-number']");
    By cvcInput = By.cssSelector("input[data-qa='cvc']");
    By expiryMonthInput = By.cssSelector("input[data-qa='expiry-month']");
    By expiryYearInput = By.cssSelector("input[data-qa='expiry-year']");
    By confirmOrderButton = By.id("submit");
    By orderPlacedMessage = By.cssSelector("h2[data-qa='order-placed']");


    // Methods

    public void enterCardDetails(String nameOnCard, String cardNumber, String cvc, String month, String year) {

        // Validate URL
        assertCurrentUrl("https://automationexercise.com/payment");

        // Validate breadcrumb path
        validatePageIsDisplayed(paymentBreadcrumbLocator, "Error: not on the correct page");

        // Enter payment details
        waitForVisibility(nameOnCardInput, 5);
        waitForClickable(nameOnCardInput, 5);
        sendKeys(nameOnCard, nameOnCardInput);

        waitForVisibility(cardNumberInput, 5);
        waitForClickable(cardNumberInput, 5);
        sendKeys(cardNumber, cardNumberInput);

        waitForVisibility(cvcInput, 5);
        waitForClickable(cvcInput, 5);
        sendKeys(cvc, cvcInput);

        waitForVisibility(expiryMonthInput, 5);
        waitForClickable(expiryMonthInput, 5);
        sendKeys(month, expiryMonthInput);

        waitForVisibility(expiryYearInput, 5);
        waitForClickable(expiryYearInput, 5);
        sendKeys(year, expiryYearInput);

        // Confirm order
        waitForVisibility(confirmOrderButton, 5);
        waitForClickable(confirmOrderButton, 5);
        click(confirmOrderButton);

        // Validate order placed message is displayed
        waitForVisibility(orderPlacedMessage, 5);
        Assert.assertTrue(isDisplayed(orderPlacedMessage), "Order placed confirmation not displayed");
    }
}