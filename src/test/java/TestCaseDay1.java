import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDay1 extends Test_Base{

    @Test
    public void Test_Scenario_1() // Verify the sorting function
    {
        Home_Page homePage = new Home_Page(driver);
        // Verify page contain THIS IS  DEMO SITE
        Assert.assertTrue(homePage.getPageTitle().contains("THIS IS DEMO SITE"));
        // Click on Mobile Menu
        homePage.clickOnMobileMenu();
        Mobile_Menu_Page mobileMenuPage = new Mobile_Menu_Page(driver);
        // Get page title text value
        mobileMenuPage.getPageTitle();
        // Sort the result by name
        mobileMenuPage.sortBy("Name");
        // Check products are sorted correctly
        Assert.assertTrue(mobileMenuPage.checkSorting().contentEquals("The product list is sorted correctly."),
                "The product are not sorted correctly");
    }
}
