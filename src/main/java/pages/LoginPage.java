
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private static final String INVALID_CREDENTIALS_ERROR = "Epic sadface: Username and password do not match any user in this service";
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isAt() {
        return driver.findElement(loginButton).isDisplayed();
    }

    // Get error message text after failed login
    public String getErrorMessage() {
        try {
            WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
            return error.getText();
        } catch (Exception e) {
            return null;
        }
    }

    // Reusable check for invalid credentials error message
    public boolean isInvalidCredentialsError() {
        String errorMsg = getErrorMessage();
        return INVALID_CREDENTIALS_ERROR.equals(errorMsg);
    }
}
