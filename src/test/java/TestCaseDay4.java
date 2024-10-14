import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDay4 extends Test_Base{

    @Test
    public void Test_Scenario4() {
        Home_Page homePage=new Home_Page(driver);
        homePage.clickOnMobileMenu();
        // Select to product to compare
        Mobile_Menu_Page mobileMenuPage=new Mobile_Menu_Page(driver);
        mobileMenuPage.clickOnCompareBtn("Sony Xperia");
        mobileMenuPage.clickOnCompareBtn("IPhone");
        mobileMenuPage.clickCompareBetweenProducts();

        ProductComparison_Page productComparisonPage = new ProductComparison_Page(driver);
        String mainWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Assert.assertEquals(productComparisonPage.getPageTitle(), "COMPARE PRODUCTS");
        driver.close();
        driver.switchTo().window(mainWindow);
        Assert.assertEquals(driver.getWindowHandles().size(), 1);

    }
}
