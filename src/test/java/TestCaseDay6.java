import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestCaseDay6 extends Test_Base {
    @DataProvider(name = "User Data")
    public Object[][] getUserData() throws IOException {
        ReadFromExcel ER = new ReadFromExcel();
        return ER.getUserDataFromExcel("D:\\Guru99_answer\\Guru99_2\\UserData.xlsx", "User Data")
                .toArray(new Object[0][]);
    }
    @Test(dataProvider = "User Data",dependsOnMethods = "TestCaseDay5.Test_Scenario5")
    public void Test_Scenario_6(Object userMail, Object userPass, Object NotUsed) {
        // Login
        Home_Page homePage = new Home_Page(driver);
        homePage.clickOnAccount();
        homePage.selectLogIn();
        //Login with user credentials
        AccountCreation_Page accountCreationPage = new AccountCreation_Page(driver);
        accountCreationPage.enterLogInEmail(userMail.toString());
        accountCreationPage.enterLogInPassword(userPass.toString());
        accountCreationPage.clickOnLoginBtn();
        // Click on My wish list
        accountCreationPage.selectFromLeftList("My Wishlist");
        // Add to cart
        MyWishList_Page myWishListPage = new MyWishList_Page(driver);
        myWishListPage.clickOnAddToCartBtnFromWishListPage();
        Cart_Page cartPage = new Cart_Page(driver);
        //enter shipping info
        cartPage.selectFromState("New York");
        cartPage.enterZipCodeValue("542896");
        // Estimate shipping
        cartPage.clickOn(cartPage.estimateBtn);
        Assert.assertEquals(cartPage.getShippingCost(), "$5.00");
        // get grand total before adding shipping and shipping cost
        float grandTotalBefore = Float.parseFloat(cartPage.getText(cartPage.grandTotal).replace("$", ""));
        float shippingCost = Float.parseFloat(cartPage.getShippingCost().replace("$", ""));
        //Update the grand total
        cartPage.clickOn(cartPage.ShippingRadioBtn);
        // Verify that shipping cost is added to the grand total
        float grandTotalAfter = grandTotalBefore + shippingCost;
        cartPage.clickOn(cartPage.updateTotalBtn);
        cartPage.getText(cartPage.grandTotal);
        float currentGrandTotal = Float.parseFloat(cartPage.getText(cartPage.grandTotal).replace("$", ""));
        Assert.assertEquals(currentGrandTotal, grandTotalAfter);
        // Click on proceed to check out
        cartPage.clickOn(cartPage.proceedToCheckout);
        CheckOut_Page checkOutPage = new CheckOut_Page(driver);
        // Enter address in checkout page
        checkOutPage.EnterText(checkOutPage.addressField, "ABC");
        // Enter City in city  checkout page
        checkOutPage.EnterText(checkOutPage.CityField, "New York");
        // select state from checkout page
        checkOutPage.selectFromDropdown(checkOutPage.StateField, "New York");
        // Enter Zip code
        checkOutPage.EnterText(checkOutPage.ZipField, "542896");
        // select country from checkout page
        checkOutPage.selectFromDropdown(checkOutPage.CountryField, "United States");
        // Enter telephone in checkout page
        checkOutPage.EnterText(checkOutPage.TelephoneField, "New York");
        // Click on continue button for billing info
        checkOutPage.clickOn(checkOutPage.billingInfoContinueBtn);
        // Click on continue button for shipping method
        checkOutPage.clickOn(checkOutPage.shippingContinueBtn);
        // Click on Check/Money order
        checkOutPage.clickOn(checkOutPage.checkMoneyOrderRadioBtn);
        // Click on continue button for payment info
        checkOutPage.clickOn(checkOutPage.paymentInfoContinue);
        // Click place Order
        checkOutPage.clickOn(checkOutPage.placeOrderBtn);
        // Check Order id is generated
        String OrderId = checkOutPage.getText(checkOutPage.orderID);
        Assert.assertNotNull(OrderId);
        System.out.println(OrderId);
        SaveInExcel saveInExcel = new SaveInExcel();
        saveInExcel.saveSearchResultInExcel(userMail.toString(), userPass.toString(), OrderId, "D:\\Guru99_answer\\Guru99_2\\UserData.xlsx");
    }

}
