package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        checkoutPage = new CheckoutPage(driver);
    }
}
