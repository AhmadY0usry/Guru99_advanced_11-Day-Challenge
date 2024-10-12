import org.testng.Assert;
import org.testng.annotations.Test;

public class ScenarioTestCase extends Test_Base{

    @Test
    public void Test_Scenarios()
    {
        Home_Page homePage = new Home_Page(driver);
        Assert.assertTrue(homePage.getPageTitle().contains("THIS IS DEMO SITE"));
        homePage.clickOnMobileMenu();
        Mobile_Menu_Page mobileMenuPage=new Mobile_Menu_Page(driver);
        mobileMenuPage.getPageTitle();
        mobileMenuPage.sortBy("Name");
        Assert.assertTrue(mobileMenuPage.checkSorting().contentEquals("The product list is sorted correctly."));
    }
}
