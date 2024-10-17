import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCaseDay8 extends Test_Base{

    @DataProvider(name = "User Data")
    public Object[][] getUserData() throws IOException {
        ReadFromExcel ER = new ReadFromExcel();
        return ER.getUserDataFromExcel("D:\\Guru99_answer\\Guru99_2\\UserData.xlsx", "User Data")
                .toArray(new Object[0][]);
    }

    @Test(dataProvider = "User Data")
    public void Test_Scenario_8(Object user_email, Object user_password,Object Order)
    {
        homePage.clickOnAccount();
        homePage.selectLogIn();
        //Login with user credentials

        accountCreationPage.enterLogInEmail(user_email.toString());
        accountCreationPage.enterLogInPassword(user_password.toString());
        accountCreationPage.clickOnLoginBtn();
        // Click on My Order
        accountCreationPage.selectFromLeftList("My Orders");
        // Click on reorder for the displayed product
        accountCreationPage.clickOn(accountCreationPage.reOrderBtn);
        // Verifying the grand total is updated after updating the quantity
        String totalPriceBeforeUpdate=cartPage.getText(cartPage.grandTotal);
        cartPage.enterQty(10);
        cartPage.clickUpdateBtn();
        String totalPriceAfterUpdate=cartPage.getText(cartPage.grandTotal);
        Assert.assertNotEquals(totalPriceBeforeUpdate,totalPriceAfterUpdate,"The total price did not changed after updating qty");
        // Click on proceed check out
        cartPage.clickOn(cartPage.proceedToCheckout);
        CheckOut_Page checkOutPage=new CheckOut_Page(driver);
        // Click on continue button for billing info
        checkOutPage.clickOn(checkOutPage.billingInfoContinueBtn);
        // Click on continue button for shipping method
        checkOutPage.clickOn(checkOutPage.shippingContinueBtn);
        // Click on Check/Money order
        checkOutPage.clickOn(checkOutPage.checkMoneyOrderRadioBtn);
        // Click on continue button for payment info
        checkOutPage.clickOn(checkOutPage.paymentInfoContinue);
        // Click on Place order button
        checkOutPage.clickOn(checkOutPage.placeOrderBtn);
        //
        String OrderId = checkOutPage.getText(checkOutPage.orderID);
        System.out.println("Order Id : "+OrderId);
        Assert.assertNotNull(OrderId);

    }

}
