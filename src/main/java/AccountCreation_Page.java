import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreation_Page extends Page_Utils {
    private final By firstNameField = By.id("firstname");
    private final By middleNameField = By.id("middlename");
    private final By lastNameField = By.id("lastname");
    private final By emailAddressField = By.id("email_address");
    private final By passwordField = By.id("password");
    private final By passConfirmationField = By.id("confirmation");
    private final By registerBtn = By.cssSelector("button[title='Register']");
    private final By registrationSuccess = By.className("success-msg");
    private final By tvLink = By.linkText("TV");
    private final By headerUserName = By.cssSelector(".header-language-container>.welcome-msg");
    private final By logInEmailField = By.id("email");
    private final By logInPassField = By.id("pass");
    private final By loginBtn = By.id("send2");
    //private final By myWishListLink = By.linkText("My Wishlist");
    private String leftList="//div[@class='block-content']/ul/li/a[text()='%s']";
    protected By reOrderBtn= By.className("link-reorder");

    public AccountCreation_Page(WebDriver driver) {
        super(driver);
    }

    public void enterNewUserData(String fName, String mName, String lName, String email, String pass, String passConfirm) {
        EnterText(this.firstNameField, fName);
        EnterText(this.middleNameField, mName);
        EnterText(this.lastNameField, lName);
        EnterText(this.emailAddressField, email);
        EnterText(this.passwordField, pass);
        EnterText(this.passConfirmationField, passConfirm);
    }

    public void clickOnRegisterBtn() {
        clickOn(this.registerBtn);
    }

    public String getRegistrationSuccessMsg() {
        return getText(this.registrationSuccess);

    }

    public String getWelcomeMsgOnHeader() {
        return getText(this.headerUserName);
    }

    public void clickOnTv() {
        clickOn(this.tvLink);
    }

    public void clickOnLoginBtn() {
        clickOn(this.loginBtn);
    }

    public void enterLogInEmail(String email) {
        EnterText(this.logInEmailField, email);
    }

    public void enterLogInPassword(String pass) {
        EnterText(this.logInPassField, pass);
    }
//    public void clickOnMyWishListLink() {
//        driver.findElement(By.xpath("//div[@class='block-content']/ul/li/a[text()='My Wishlist']")).click();
//    }

    public void selectFromLeftList(String list)
    {
        leftList=String.format(this.leftList,list);
        By leftListElement=By.xpath(leftList);
        clickOn(leftListElement);
    }
}
