import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDay3 extends Test_Base{


    @Test
    public void Test_Scenario3() // Verify that you cannot add more product in cart than the product available in the store
    {

        // Click on Mobile Menu
        homePage.clickOnMobileMenu();
        // Get page title text value
        mobileMenuPage.getPageTitle();
        // Go to product details
        mobileMenuPage.clickOnXperia();
        // Click on add to cart button
        productDetailsPage.clickAddToCartBtn();
        // Enter a new quantity more than stock
        productDetailsPage.enterQty(1000);
        // Click on update button
        productDetailsPage.clickUpdateBtn();
        // Check the error msg
        Assert.assertEquals(productDetailsPage.getErrorMsg(), "Some of the products cannot be ordered in requested quantity.");
        // Click on empty cart button
        productDetailsPage.clickOnEmptyCardBtn();
        // Check that the cart is empty
        Assert.assertEquals(productDetailsPage.getEmptyErrorMsg(), "SHOPPING CART IS EMPTY");
        Assert.assertTrue(productDetailsPage.checkCartIsEmpty().contains("You have no items in your shopping cart."));

    }
}
