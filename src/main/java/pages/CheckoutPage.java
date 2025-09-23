package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Returns the heading/title of the checkout page
    public String getCheckoutPageHeading() {
        return driver.findElement(By.className("subheader")).getText();
    }

    // Add more checkout-specific methods as needed
}
