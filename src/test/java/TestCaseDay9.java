import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDay9 extends Test_Base{


    @Test
            public void Test_Scenario_9() {
        Home_Page homePage = new Home_Page(driver);
        // Click on Mobile Menu
        homePage.clickOnMobileMenu();
        // Click on IPhone
        Mobile_Menu_Page mobileMenuPage=new Mobile_Menu_Page(driver);
        mobileMenuPage.clickOnAddToCartBtn("IPhone");
        // Enter coupon code and click on apply coupon
        ProductDetails_Page productDetailsPage=new ProductDetails_Page(driver);
        productDetailsPage.EnterText(productDetailsPage.couponCodes,"GURU50");
        productDetailsPage.clickOn(productDetailsPage.applyCouponBtn);
        // Verify that the coupon is applied
        Assert.assertTrue(productDetailsPage.getText(productDetailsPage.successMsg).contains("GURU50"));

    }
}
