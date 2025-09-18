package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {
    private ProductsPage productsPage;
    private final String itemName = "Sauce Labs Backpack";

    @BeforeClass
    public void setUp() {
        productsPage = new ProductsPage(driver);
    }

    // Test: verify at least one ADD TO CART button is present on the page using POM
    @Test(priority = 1)
    public void testAddToCartButtonIsPresent() {
        Assert.assertTrue(productsPage.isAddToCartButtonPresent(),
                "At least one 'Add to cart' button should be present on the page");
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
        // Check if 'Add to cart' button is still visible for this item
        boolean addToCartVisible = productsPage.isAddToCartButtonVisibleForItem(itemName);
        Assert.assertFalse(addToCartVisible,
                "'Add to cart' button should not be visible for the item after adding to cart");
    }

    // Edge case: add item after page reload
    @Test(priority = 4)
    public void testAddItemAfterReload() {
        driver.navigate().refresh();
        Assert.assertTrue(productsPage.isItemInCart(1), "Item should be in cart after adding");
        // Check if 'Add to cart' button is still visible for this item
        boolean addToCartVisible = productsPage.isAddToCartButtonVisibleForItem(itemName);
        Assert.assertFalse(addToCartVisible,
                "'Add to cart' button should not be visible for the item after adding to cart");

    }
}
