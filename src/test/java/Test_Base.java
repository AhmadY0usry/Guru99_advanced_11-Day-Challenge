import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Test_Base {

    protected ChromeDriver driver;
    protected String baseUrl;
    protected Home_Page homePage ;
    protected Mobile_Menu_Page mobileMenuPage;
    protected ProductDetails_Page productDetailsPage;
    protected ProductComparison_Page productComparisonPage;
    protected MyAccount_Page myAccountPage;
    protected AccountCreation_Page accountCreationPage;
    protected TVMenu_Page tvMenuPage;
    protected MyWishList_Page myWishListPage;
    protected Cart_Page cartPage;
    protected CheckOut_Page checkOutPage;
    protected MyOrders_Page myOrdersPage;
    @BeforeClass
    public void setUp()
    {
        baseUrl="http://live.techpanda.org/index.php/";
        driver=new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void instantiateClass()
    {
        homePage = new Home_Page(driver);
        mobileMenuPage = new Mobile_Menu_Page(driver);
        productDetailsPage = new ProductDetails_Page(driver);
        productComparisonPage = new ProductComparison_Page(driver);
        myAccountPage = new MyAccount_Page(driver);
        accountCreationPage = new AccountCreation_Page(driver);
        tvMenuPage = new TVMenu_Page(driver);
        myWishListPage = new MyWishList_Page(driver);
        cartPage = new Cart_Page(driver);
        checkOutPage = new CheckOut_Page(driver);
        myOrdersPage = new MyOrders_Page(driver);
    }

}
