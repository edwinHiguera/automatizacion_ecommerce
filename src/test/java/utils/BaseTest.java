package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public class BaseTest {

    private WebDriver driver;

    // Constructor
    public BaseTest(WebDriver driver){
        this.driver = driver;
    }

    public BaseTest() {
    }

    // Browser connection method
    public WebDriver connectBrowser() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Opciones necesarias para correr en CI sin GUI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless=new");  // o "--headless" para versiones anteriores

        // Crear un directorio temporal único para el perfil del usuario
        try {
            Path tempUserDataDir = Files.createTempDirectory("chrome-user-data-dir");
            options.addArguments("--user-data-dir=" + tempUserDataDir.toAbsolutePath().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver = new ChromeDriver(options);
        return driver;
    }

    // ********* Wrappers ***********

    // findElement
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    // findElements
    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    // getText
    public String getText(WebElement element){
        return element.getText();
    }

    // Overloaded getText with By locator
    public String getText(By locator){
        return driver.findElement(locator).getText();
    }

    // sendKeys
    public void sendKeys(String text, By locator){
        driver.findElement(locator).sendKeys(text);
    }

    // clear
    public void clear(By locator){
        driver.findElement(locator).clear();
    }

    // click
    public void click(By locator){
        driver.findElement(locator).click();
    }

    // isDisplayed
    public Boolean isDisplayed(By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e){
            return false;
        }
    }

    // Assert current URL
    public void assertCurrentUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The current URL does not match the expected one.");
    }

    // Wait for element visibility
    public WebElement waitForVisibility(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Scroll to specific element
    public void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator, 5);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Wait for element to be clickable
    public WebElement waitForClickable(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Validate that page element is displayed
    public void validatePageIsDisplayed(By locator, String errorMessage) {
        waitForVisibility(locator, 5);
        Assert.assertTrue(isDisplayed(locator), errorMessage);
    }

    // Open URL
    public void openUrl(String url){
        driver.get(url);
    }

}
