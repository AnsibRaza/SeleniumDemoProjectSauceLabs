
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class ProductsPage extends BasePage {
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    // Add any item to cart by item label text
    public void addItemToCart(String itemLabel) {
        java.util.List<org.openqa.selenium.WebElement> items = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));
        for (org.openqa.selenium.WebElement item : items) {
            org.openqa.selenium.WebElement labelElement = item.findElement(By.className("inventory_item_name"));
            if (labelElement.getText().equals(itemLabel)) {
                item.findElement(By.xpath(".//button[contains(text(),'Add to cart')]")).click();
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

    // Returns the count of "Add to cart" buttons on the products page
    public int getAddToCartButtonsCount() {
        return driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).size();
    }
    //verify products page heading. Products
    public boolean isProductsPageHeadingCorrect() {
        String heading = driver.findElement(By.className("product_label")).getText();
        return "Products".equals(heading);
    }
}
