import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

    public class Home_Page extends Page_Utils {
        public Home_Page(WebDriver driver) {
            super(driver);
        }

    private final By mobile_Menu=By.linkText("MOBILE");
    private final By pageTitle= By.className("page-title");
    private final By accountBtn=By.cssSelector(".skip-link.skip-account");
    private final By myAccountBtn=By.linkText("My Account");
    private final By logInBtn=By.linkText("Log In");
    private final By logOutBtn=By.linkText("Log Out");

    public void clickOnMobileMenu()
    {

        clickOn(mobile_Menu);
    }
    @Override
    public String getPageTitle()
    {
        return getText(pageTitle);
    }

    public void clickOnAccount()
    {
        clickOn(this.accountBtn);
    }

    public void selectMyAccount()
    {
        clickOn(this.myAccountBtn);
    }
    public void selectLogIn()
    {
            clickOn(this.logInBtn);
    }
        public void selectLogOut()
        {
            clickOn(this.logOutBtn);
        }

}
