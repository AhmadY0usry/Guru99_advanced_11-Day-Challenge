import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseDay9 extends Test_Base{


    @Test
            public void Test_Scenario_9() {
        // Click on Mobile Menu
        homePage.clickOnMobileMenu();
        // Click on IPhone
        mobileMenuPage.clickOnAddToCartBtn("IPhone");
        // Enter coupon code and click on apply coupon
        productDetailsPage.EnterText(productDetailsPage.couponCodes,"GURU50");
        productDetailsPage.clickOn(productDetailsPage.applyCouponBtn);
        // Verify that the coupon is applied
        Assert.assertTrue(productDetailsPage.getText(productDetailsPage.successMsg).contains("GURU50"));

    }
}
