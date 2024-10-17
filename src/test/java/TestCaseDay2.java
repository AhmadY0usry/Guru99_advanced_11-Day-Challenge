import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDay2 extends Test_Base{

    @Test
    public void Test_Scenario2() //Verify the cost of the product in list page and details page are equal
    {
        // Click on Mobile Menu
        homePage.clickOnMobileMenu();
        // Get page title text value
        mobileMenuPage.getPageTitle();
        // Get Sony Xperia Price
        String XperiaPrice = (mobileMenuPage.getXperiaPrice());
        // Go to product details
        mobileMenuPage.clickOnXperia();
        // Check the product price are the same
        Assert.assertEquals(productDetailsPage.getProductPrice(), XperiaPrice);
    }
}
