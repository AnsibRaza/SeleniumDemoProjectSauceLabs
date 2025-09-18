package tests;

import base.BaseTest;
import org.testng.Assert;
import org.openqa.selenium.By;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {
    private ProductsPage productsPage;
    private final String itemName = "sauce-labs-backpack";

    // Test: verify at least one ADD TO CART button is present on the page using POM
    @Test(priority = 1)
    public void testAddToCartButtonIsPresent() {
        int addToCartButtons = productsPage.getAddToCartButtonsCount();
        Assert.assertTrue(addToCartButtons > 0, "At least one 'Add to cart' button should be present on the page");
    }

    // Positive test: add item to cart
    @Test(priority = 2)
    public void testAddItemToCart() {
        productsPage.addItemToCart(itemName);
        Assert.assertTrue(productsPage.isItemInCart(1), "Item should be in cart after adding");
    }

    // Edge case: add item to cart twice
    @Test(priority = 3)
    public void testAddItemToCartTwice() {
        productsPage.addItemToCart(itemName);
        try {
            productsPage.addItemToCart(itemName);
            Assert.assertTrue(productsPage.isItemInCart(1), "Item should still be in cart after second add");
        } catch (Exception e) {
            Assert.assertTrue(productsPage.isItemInCart(1), "Item should still be in cart after second add");
        }
    }

    // Edge case: add item after page reload
    @Test(priority = 4)
    public void testAddItemAfterReload() {
        driver.navigate().refresh();
        productsPage.addItemToCart(itemName);
        Assert.assertTrue(productsPage.isItemInCart(1), "Item should be in cart after reload and add");
    }
}
