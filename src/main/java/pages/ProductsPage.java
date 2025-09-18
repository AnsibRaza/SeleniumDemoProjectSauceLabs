
package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ProductsPage extends BasePage {
    // Checks if the 'Add to cart' button is visible for a specific item by name
    public boolean isAddToCartButtonVisibleForItem(String itemLabel) {
        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));
        for (WebElement item : items) {
            WebElement labelElement = item.findElement(By.cssSelector(".inventory_item_label .inventory_item_name"));
            if (labelElement.getText().trim().equals(itemLabel)) {
                List<WebElement> addToCartButtons = item.findElements(By.cssSelector("button.btn_primary.btn_inventory"));
                return !addToCartButtons.isEmpty() && addToCartButtons.get(0).isDisplayed();
            }
        }
        return false;
    }
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    // Add any item to cart by item label text
    public void addItemToCart(String itemLabel) {
        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));
        for (WebElement item : items) {
            WebElement labelElement = item.findElement(By.cssSelector(".inventory_item_label .inventory_item_name"));
            if (labelElement.getText().trim().equals(itemLabel)) {
                // Find the button with class 'btn_primary btn_inventory' inside this item
                WebElement addToCartButton = item.findElement(By.cssSelector("button.btn_primary.btn_inventory"));
                addToCartButton.click();
                break;
            }
        }
    }

    // Check if any item is in the cart by expected count
    public boolean isItemInCart(int expectedCount) {
        try {
            String badge = driver.findElement(By.className("shopping_cart_badge")).getText();
            return Integer.toString(expectedCount).equals(badge);
        } catch (Exception e) {
            return false;
        }
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("inventory");
    }

    //verify products page heading. Products
    public boolean isProductsPageHeadingCorrect() {
        String heading = driver.findElement(By.className("product_label")).getText();
        return "Products".equals(heading);
    }

    // Verifies if at least one "Add to cart" button is available on the products page
    public boolean isAddToCartButtonPresent() {
        // The button has class 'btn_primary btn_inventory' according to the HTML
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("button.btn_primary.btn_inventory"));
        return !addToCartButtons.isEmpty();
    }
}
