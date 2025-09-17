
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class ProductsPage extends BasePage {
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    // Add any item to cart by item name (e.g., "sauce-labs-backpack")
    public void addItemToCart(String itemName) {
        String selector = String.format("button[data-test='add-to-cart-%s']", itemName);
        driver.findElement(By.cssSelector(selector)).click();
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
}
