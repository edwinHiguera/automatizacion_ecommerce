package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseTest;

public class LoginPage extends BaseTest {

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By loginHeaderText = By.xpath("//h2[text() = 'Login to your account']");
    By emailInput = By.xpath("//input[@data-qa='login-email']");
    By passwordInput = By.xpath("//input[@data-qa='login-password']");
    By loginButton = By.xpath("//button[@data-qa='login-button']");
    By errorMessage = By.xpath("//p[text()='Your email or password is incorrect!']");
    By logoutLink = By.cssSelector("a[href='/logout']");
    By loginLink = By.cssSelector("a[href='/login']");

    // Methods

    public void enterEmail(String email) {
        clear(emailInput);
        sendKeys(email, emailInput);
    }

    public void enterPassword(String password) {
        clear(passwordInput);
        sendKeys(password, passwordInput);
    }

    public void clickLogin() {
        waitForClickable(loginButton, 10);
        click(loginButton);
    }

    public void performLogin(String email, String password){
        waitForVisibility(loginHeaderText, 10); // Validate login header is visible

        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }


    public boolean isLoginFailed() {
        waitForVisibility(errorMessage, 5);
        return isDisplayed(errorMessage);
    }

    public boolean isLoginSuccessful(){
        waitForClickable(logoutLink, 5);
        return isDisplayed(logoutLink);
    }

    public void performLogout(){
        waitForClickable(logoutLink, 5);
        click(logoutLink);
        waitForClickable(loginLink, 5);
    }

    public boolean isLogoutSuccessful(){
        return isDisplayed(loginLink);
    }

}
