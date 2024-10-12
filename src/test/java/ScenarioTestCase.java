import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;





public class ScenarioTestCase extends Test_Base{

    private Home_Page homePage;
    private Mobile_Menu_Page mobileMenuPage;
    private ProductDetails_Page productDetailsPage;
    private AccountCreation_Page accountCreationPage;
    private MyWishList_Page myWishListPage;
    @Test
    public void Test_Scenarios() // Verify the sorting function
    {
        homePage = new Home_Page(driver);
        Assert.assertTrue(homePage.getPageTitle().contains("THIS IS DEMO SITE"));
        homePage.clickOnMobileMenu();
        mobileMenuPage=new Mobile_Menu_Page(driver);
        mobileMenuPage.getPageTitle();
        mobileMenuPage.sortBy("Name");
        Assert.assertTrue(mobileMenuPage.checkSorting().contentEquals("The product list is sorted correctly."),
                "The product are not sorted correctly");
    }
    @Test (priority = 1)
    public void Test_Scenario2() //Verify the cost of the product in list page and details page are equal
    {
        productDetailsPage=new ProductDetails_Page(driver);
        String XperiaPrice=(mobileMenuPage.getXperiaPrice());
        mobileMenuPage.clickOnXperia();
        Assert.assertEquals(productDetailsPage.getProductPrice(),XperiaPrice);
    }

    @Test(priority = 2)
    public void Test_Scenario3() // Verify that you cannot add more product in cart than the product available in the store
    {
        productDetailsPage.clickAddToCartBtn();
        productDetailsPage.enterQty(1000);
        productDetailsPage.clickUpdateBtn();
        Assert.assertEquals(productDetailsPage.getErrorMsg(),"Some of the products cannot be ordered in requested quantity.");
        productDetailsPage.clickOnEmptyCardBtn();
        Assert.assertEquals(productDetailsPage.getEmptyErrorMsg(),"SHOPPING CART IS EMPTY");
        Assert.assertTrue(productDetailsPage.checkCartIsEmpty().contains("You have no items in your shopping cart."));

    }
    @Test (priority = 4)
    public void Test_Scenario4()
    {
        driver.get(baseUrl);
        homePage.clickOnMobileMenu();
        mobileMenuPage.clickOnCompareBtn("Sony Xperia");
        mobileMenuPage.clickOnCompareBtn("IPhone");
        mobileMenuPage.clickCompareBetweenProducts();
        ProductComparison_Page productComparisonPage=new ProductComparison_Page(driver);
        String mainWindow= driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Assert.assertEquals(productComparisonPage.getPageTitle(),"COMPARE PRODUCTS");
        driver.close();
        driver.switchTo().window(mainWindow);
        Assert.assertEquals(driver.getWindowHandles().size(), 1);

    }

    @DataProvider (name = "New User DataProvider")
    public Object[][] getNewUserData()
    {
        Faker faker= new Faker();
        return new Object [][] {{faker.name().firstName(),faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),faker.internet().password(),faker.lorem().sentence()}};

    }

    @Test (dataProvider = "New User DataProvider",testName = "New User Creation")
    public Object [] Test_Scenario5(Object fName, Object mName, Object lName, Object email, Object password, Object msg)
    {
        //1- Create a new user
        homePage = new Home_Page(driver);
        homePage.clickOnAccount();
        homePage.selectMyAccount();
        MyAccount_Page myAccountPage = new MyAccount_Page(driver);
        myAccountPage.clickOnCreateAnAccount();
        accountCreationPage=new AccountCreation_Page(driver);
        String userMail=email.toString();
        String userPass= password.toString();
        accountCreationPage.enterNewUserData(fName.toString(),mName.toString(),lName.toString(),userMail,userPass,userPass);
        accountCreationPage.clickOnRegisterBtn();
        Assert.assertTrue(accountCreationPage.getWelcomeMsgOnHeader().contains(fName.toString().toUpperCase()+ " "+ mName.toString().toUpperCase()+ " " +lName.toString().toUpperCase() ));
        Assert.assertEquals(accountCreationPage.getRegistrationSuccessMsg(),"Thank you for registering with Main Website Store.");
        //2- Add product to wishList
        accountCreationPage.clickOnTv();
        TVMenu_Page tvMenuPage=new TVMenu_Page(driver);
        tvMenuPage.addToWishList("LG LCD");
        myWishListPage=new MyWishList_Page(driver);
        myWishListPage.clickOnShareWishListBtn();
        myWishListPage.enterDataInMailField(email.toString());
        myWishListPage.enterDataInMsgField(msg.toString());
        myWishListPage.clickOnShareWishListBtn();
        Assert.assertEquals(myWishListPage.getSharingSuccessMsg(),"Your Wishlist has been shared.");
        return new Object [] {userMail,userPass};
    }

    @Test (dataProvider = "New User DataProvider")
    public void Test_Scenario6(Object fName,Object mName, Object lName, Object email, Object password,Object msg)
    {
        // 1- User Creation
        Object[] UserData = Test_Scenario5(fName,mName,lName,email,password,msg);
        String userMail=UserData[0].toString();
        String userPass=UserData[1].toString();
        // Login
        homePage = new Home_Page(driver);
        homePage.clickOnAccount();
        homePage.selectLogOut();
        homePage.clickOnAccount();
        homePage.selectLogIn();
        //Login with user credentials
        accountCreationPage.enterLogInEmail(userMail);
        accountCreationPage.enterLogInPassword(userPass);
        accountCreationPage.clickOnLoginBtn();
        // Click on My wish list
        accountCreationPage.selectFromLeftList("My Wishlist");
        // Add to cart
        myWishListPage.clickOnAddToCartBtnFromWishListPage();
        Cart_Page cartPage=new Cart_Page(driver);
        //enter shipping info
        cartPage.selectFromState("New York");
        cartPage.enterZipCodeValue("542896");
        // Estimate shipping
        cartPage.clickOn(cartPage.estimateBtn);
        Assert.assertEquals(cartPage.getShippingCost(),"$5.00");
        // get grand total before adding shipping and shipping cost
        float grandTotalBefore=Float.parseFloat(cartPage.getText(cartPage.grandTotal).replace("$",""));
        float shippingCost=Float.parseFloat(cartPage.getShippingCost().replace("$",""));
        //Update the grand total
        cartPage.clickOn(cartPage.ShippingRadioBtn);
        // Verify that shipping cost is added to the grand total
        float grandTotalAfter=grandTotalBefore+shippingCost;
        cartPage.clickOn(cartPage.updateTotalBtn);
        cartPage.getText(cartPage.grandTotal);
        float currentGrandTotal=Float.parseFloat(cartPage.getText(cartPage.grandTotal).replace("$",""));
        Assert.assertEquals(currentGrandTotal,grandTotalAfter);
        // Click on proceed to check out
        cartPage.clickOn(cartPage.proceedToCheckout);
        CheckOut_Page checkOutPage=new CheckOut_Page(driver);
        // Enter address in checkout page
        checkOutPage.EnterText(checkOutPage.addressField,"ABC");
        // Enter City in city  checkout page
        checkOutPage.EnterText(checkOutPage.CityField,"New York");
        // select state from checkout page
        checkOutPage.selectFromDropdown(checkOutPage.StateField,"New York");
        // Enter Zip code
        checkOutPage.EnterText(checkOutPage.ZipField,"542896");
        // select country from checkout page
        checkOutPage.selectFromDropdown(checkOutPage.CountryField,"United States");
        // Enter telephone in checkout page
        checkOutPage.EnterText(checkOutPage.TelephoneField,"New York");
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
        String OrderId=checkOutPage.getText(checkOutPage.orderID);
        Assert.assertNotNull(OrderId);
        System.out.println(OrderId);
        SaveInExcel saveInExcel=new SaveInExcel();
        saveInExcel.saveSearchResultInExcel(userMail,userPass,OrderId,"D:\\Guru99_answer\\Guru99_2\\UserData.xlsx");
    }

    @DataProvider(name = "User Data")
    public Object [][] getUserData() throws IOException {
        ReadFromExcel ER = new ReadFromExcel();
        return ER.getUserDataFromExcel("D:\\Guru99_answer\\Guru99_2\\UserData.xlsx", "User Data").toArray(new Object[0][]);
    }

    @Test(dataProvider = "User Data")
    public void TestScenario_7(Object user_email,Object user_password,Object OrderId) throws InterruptedException {
        // Login
        homePage = new Home_Page(driver);
        homePage.clickOnAccount();
        homePage.selectLogIn();
        //Login with user credentials
        accountCreationPage=new AccountCreation_Page(driver);
        accountCreationPage.enterLogInEmail(user_email.toString());
        accountCreationPage.enterLogInPassword(user_password.toString());
        accountCreationPage.clickOnLoginBtn();
        accountCreationPage.selectFromLeftList("My Orders");
        MyOrders_Page myOrdersPage=new MyOrders_Page(driver);
        String orderNumber=myOrdersPage.getText(myOrdersPage.orderNum);
        String orderStatus=myOrdersPage.getText(myOrdersPage.orderStatus);
        Assert.assertEquals(orderNumber,OrderId);
        Assert.assertEquals(orderStatus,"Pending");
        myOrdersPage.clickOn(myOrdersPage.viewOrderLink);
        myOrdersPage.clickOn(myOrdersPage.printOrderLink);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.25f), 1000))
                .takeScreenshot(driver);

        // Save the screenshot
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File("C:\\Users\\QC\\Downloads\\Test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

}   }
