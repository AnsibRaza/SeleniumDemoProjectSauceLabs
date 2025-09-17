package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    // Helper to reset to login page before each test
    private void goToLoginPage() {
        driver.get("https://www.saucedemo.com/v1/");
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, groups = { "regression" })
    public void testLoginPageDisplayed() {
        goToLoginPage();
        Assert.assertTrue(loginPage.isAt(), "Login button should be displayed");
    }

    // Negative and edge cases first
    @Test(priority = 2, groups = { "regression" })
    public void testLoginWithInvalidUsername() {
        goToLoginPage();
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isAt(), "Should remain on login page with invalid username");
        Assert.assertTrue(loginPage.isInvalidCredentialsError(), "Error message should match invalid credentials");
    }

    @Test(priority = 3, groups = { "regression" })
    public void testLoginWithInvalidPassword() {
        goToLoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isAt(), "Should remain on login page with invalid password");
        Assert.assertTrue(loginPage.isInvalidCredentialsError(), "Error message should match invalid credentials");
    }

    @Test(priority = 4, groups = { "regression" })
    public void testLoginWithEmptyUsername() {
        goToLoginPage();
        loginPage.enterUsername("");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isAt(), "Should remain on login page with empty username");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMsg, "Error message should be displayed");
        Assert.assertTrue(errorMsg.toLowerCase().contains("username is required"),
                "Error message should indicate username is required");
    }

    @Test(priority = 5, groups = { "regression" })
    public void testLoginWithEmptyPassword() {
        goToLoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isAt(), "Should remain on login page with empty password");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMsg, "Error message should be displayed");
        Assert.assertTrue(errorMsg.toLowerCase().contains("password is required"),
                "Error message should indicate password is required");
    }

    @Test(priority = 6, groups = { "regression" })
    public void testLoginWithEmptyFields() {
        goToLoginPage();
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isAt(), "Should remain on login page with both fields empty");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMsg, "Error message should be displayed");
        Assert.assertTrue(errorMsg.toLowerCase().contains("username is required"),
                "Error message should indicate username is required");
    }

    @Test(priority = 7, groups = { "regression" })
    public void testLoginWithLongInput() {
        goToLoginPage();
        String longUsername = "user" + "a".repeat(100);
        String longPassword = "pass" + "b".repeat(100);
        loginPage.enterUsername(longUsername);
        loginPage.enterPassword(longPassword);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isAt(), "Should remain on login page with long input values");
        Assert.assertTrue(loginPage.isInvalidCredentialsError(), "Error message should match invalid credentials");
    }

    // Positive test: valid credentials
    @Test(priority = 8, groups = { "regression" })
    public void testLoginWithValidCredentials() {
        goToLoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        // Verify if the Products page is displayed
        Assert.assertTrue(productsPage.isProductsPageHeadingCorrect(), "Should be on Products page after login");
    }
}
