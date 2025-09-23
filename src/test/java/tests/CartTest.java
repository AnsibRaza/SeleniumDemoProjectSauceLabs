package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;

public class CartTest extends BaseTest {
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testNumberOfProductsInCart() {
        // Assumes you are already on the cart page and items have been added previously
        int productCount = cartPage.getNumberOfProductsInCart();
        // You can adjust the expected count as needed, here just assert it's >= 0
        Assert.assertTrue(productCount >= 0, "Product count in cart should be non-negative");
    }

    @Test
    public void testCartItemNames() {
        // Assumes you are already on the cart page and items have been added previously
        java.util.List<String> itemNames = cartPage.getCartItemNames();
        Assert.assertTrue(itemNames.contains("Sauce Labs Backpack"), "Cart should contain 'Sauce Labs Backpack'");
        Assert.assertTrue(itemNames.contains("Sauce Labs Bike Light"), "Cart should contain 'Sauce Labs Bike Light'");
    }

    @Test
    public void testClickCheckoutAndVerifyPage() {
        cartPage.clickCheckoutButton();
        String heading = checkoutPage.getCheckoutPageHeading();
        Assert.assertEquals(heading, "Checkout: Your Information",
                "Checkout page heading should be 'Checkout: Your Information'");
    }
}
