import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TestCaseDay7 extends Test_Base{
    @DataProvider(name = "User Data")
    public Object[][] getUserData() throws IOException {
        ReadFromExcel ER = new ReadFromExcel();
        return ER.getUserDataFromExcel("D:\\Guru99_answer\\Guru99_2\\UserData.xlsx", "User Data")
                .toArray(new Object[0][]);
    }

    @Test(dataProvider = "User Data")
    public void TestScenario_7(Object user_email, Object user_password, Object OrderId) {
        // Login

        Home_Page homePage = new Home_Page(driver);
        homePage.clickOnAccount();
        homePage.selectLogIn();
        //Login with user credentials

        AccountCreation_Page accountCreationPage = new AccountCreation_Page(driver);
        accountCreationPage.enterLogInEmail(user_email.toString());
        accountCreationPage.enterLogInPassword(user_password.toString());
        accountCreationPage.clickOnLoginBtn();
        accountCreationPage.selectFromLeftList("My Orders");
        MyOrders_Page myOrdersPage = new MyOrders_Page(driver);
        String orderNumber = myOrdersPage.getText(myOrdersPage.orderNum);
        String orderStatus = myOrdersPage.getText(myOrdersPage.orderStatus);
        Assert.assertEquals(orderNumber, OrderId);
        Assert.assertEquals(orderStatus, "Pending");
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


    }
}
