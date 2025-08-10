package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BaseTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrderPlacedPage extends BaseTest {

    private WebDriver driver;

    // Constructor
    public OrderPlacedPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators
    By orderConfirmedLocator = By.cssSelector("div[class='col-sm-9 col-sm-offset-1']>p");
    By btnDownloadInvoice = By.cssSelector("a[href='/download_invoice/2000']");
    By btnContinue = By.cssSelector("a[data-qa='continue-button']");
    By linkCart = By.xpath("//a[@href='/view_cart' and text()=' Cart']");
    By cartEmptyLocator = By.xpath("//b[text()='Cart is empty!']");

    // ****** Methods ******

    public void validateFileDownload() throws IOException {

        waitForVisibility(btnDownloadInvoice, 5);
        waitForClickable(btnDownloadInvoice, 5);

        String link = findElement(btnDownloadInvoice).getAttribute("href"); // gets the href link

        // HTTP connection
        HttpURLConnection httpConnection = (HttpURLConnection) (new URL(link).openConnection());
        httpConnection.setRequestMethod("HEAD");
        httpConnection.connect();

        String contentType = httpConnection.getContentType(); // content type
        int contentLength = httpConnection.getContentLength(); // content length

        Assert.assertTrue(
                contentType.equals("application/octet-stream") || contentType.equals("text/plain"),
                "Unexpected content type: " + contentType
        );

        Assert.assertTrue(
                contentLength == -1 || contentLength > 0,
                "Invalid file size: " + contentLength
        );

        httpConnection.disconnect();
    }

    public void validateOrder() throws IOException {

        // Validate URL
        assertCurrentUrl("https://automationexercise.com/payment_done/2000");

        // Validate order message
        waitForVisibility(orderConfirmedLocator, 5);
        Assert.assertTrue(isDisplayed(orderConfirmedLocator));

        // Validate file download
        validateFileDownload();

        // Continue
        waitForVisibility(btnContinue, 5);
        waitForClickable(btnContinue, 5);
        click(btnContinue);

        // Validate empty cart
        waitForVisibility(linkCart, 5);
        waitForClickable(linkCart, 5);
        click(linkCart);

        waitForVisibility(cartEmptyLocator, 5);
        Assert.assertTrue(isDisplayed(cartEmptyLocator));

    }

}