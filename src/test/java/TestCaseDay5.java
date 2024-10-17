import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCaseDay5 extends Test_Base{
    @DataProvider(name = "New User DataProvider")
    public Object[][] getNewUserData() {
        Faker faker = new Faker();
        return new Object[][]{{faker.name().firstName(), faker.name().firstName(), faker.name().lastName(),
                faker.internet().emailAddress(), faker.internet().password(), faker.lorem().sentence()}};

    }

    @Test(dataProvider = "New User DataProvider", testName = "New User Creation")
    public void Test_Scenario5(Object fName, Object mName, Object lName, Object email, Object password, Object msg) {
        //1- Create a new user
        homePage.clickOnAccount();
        homePage.selectMyAccount();

        myAccountPage.clickOnCreateAnAccount();
        // Enter User data
        String UserMail=email.toString();
        String UserPass=password.toString();
        accountCreationPage.enterNewUserData(fName.toString(), mName.toString(), lName.toString(), UserMail,UserPass, UserPass);
        accountCreationPage.clickOnRegisterBtn();
        Assert.assertTrue(accountCreationPage.getWelcomeMsgOnHeader().contains(fName.toString().toUpperCase() + " " + mName.toString().toUpperCase() + " " + lName.toString().toUpperCase()));
        Assert.assertEquals(accountCreationPage.getRegistrationSuccessMsg(), "Thank you for registering with Main Website Store.");
        //2- Add product to wishList
        accountCreationPage.clickOnTv();
        tvMenuPage.addToWishList("LG LCD");

        myWishListPage.clickOnShareWishListBtn();
        myWishListPage.enterDataInMailField(email.toString());
        myWishListPage.enterDataInMsgField(msg.toString());
        myWishListPage.clickOnShareWishListBtn();
        // Check that the wish list is shared successfully
        Assert.assertEquals(myWishListPage.getSharingSuccessMsg(), "Your Wishlist has been shared.");
        SaveInExcel saveInExcel = new SaveInExcel();
        saveInExcel.saveSearchResultInExcel(UserMail, UserPass, "122565", "D:\\Guru99_answer\\Guru99_2\\UserData.xlsx");
    }
}
