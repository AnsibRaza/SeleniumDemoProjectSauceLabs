package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {
    private final String secondItemName = "Sauce Labs Bike Light";
    private ProductsPage productsPage;
    private CartPage cartPage;
    private final String itemName = "Sauce Labs Backpack";

    @BeforeClass
    public void setUp() {
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
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

    // Test: add a second item and verify cart count is 2
    @Test(priority = 5)
    public void testAddSecondItemToCart() {
        productsPage.addItemToCart(secondItemName);
        Assert.assertTrue(productsPage.isItemInCart(2), "Cart count should be 2 after adding two items");
    }

    // Test: click on the shopping cart and verify cart page is displayed
    @Test(priority = 6)
    public void testGoToCartAndVerifyCartPage() {
        productsPage.goToCart();
        String cartHeading = cartPage.getCartPageHeading();
        Assert.assertEquals(cartHeading, "Your Cart", "Cart page heading should be 'Your Cart'");
    }
}
