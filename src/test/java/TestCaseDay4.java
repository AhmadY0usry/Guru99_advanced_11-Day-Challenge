import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDay4 extends Test_Base{

    @Test
    public void Test_Scenario4() {
        homePage.clickOnMobileMenu();
        // Select to product to compare
        mobileMenuPage.clickOnCompareBtn("Sony Xperia");
        mobileMenuPage.clickOnCompareBtn("IPhone");
        mobileMenuPage.clickCompareBetweenProducts();

        // switch to the new opened window
        String mainWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Verify the page title
        Assert.assertEquals(productComparisonPage.getPageTitle(), "COMPARE PRODUCTS");
        // Close the current window
        driver.close();
        driver.switchTo().window(mainWindow);
        // Verify that the new window is closed
        Assert.assertEquals(driver.getWindowHandles().size(), 1);

    }
}
