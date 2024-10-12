import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccount_Page extends Page_Utils{
    public MyAccount_Page(WebDriver driver) {
        super(driver);
    }
    private final By createAccountBtn= By.cssSelector("a[title='Create an Account']");
    public void clickOnCreateAnAccount()
    {
        clickOn(this.createAccountBtn);
    }


}
