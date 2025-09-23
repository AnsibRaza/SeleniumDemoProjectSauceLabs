package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class CartPage extends BasePage {
    // Clicks the Checkout button on the cart page
    public void clickCheckoutButton() {
        driver.findElement(By.id("checkout")).click();
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Returns the heading/title of the cart page
    public String getCartPageHeading() {
        return driver.findElement(By.className("subheader")).getText();
    }

    // Returns the number of products/items in the cart
    public int getNumberOfProductsInCart() {
        // Each cart item has class 'cart_item' according to SauceDemo structure
        return driver.findElements(By.cssSelector(".cart_item")).size();
    }

     // Returns a list of item names in the cart
    public List<String> getCartItemNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.cssSelector(".cart_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            names.add(name);
        }
        return names;
    }
}
