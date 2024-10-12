import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyWishList_Page extends Page_Utils{
    public MyWishList_Page(WebDriver driver) {
        super(driver);
    }

    private final By shareWishListBtn=By.cssSelector(".button.btn-share , .button[title='Share Wishlist']");
    private final By addressField=By.id("email_address");
    private final By msgField=By.id("message");
    private final By sharedSuccessMsg = By.className("success-msg");
    private final By addToCartBtn=By.cssSelector(".button.btn-cart");


    public void clickOnShareWishListBtn()
    {
        clickOn(this.shareWishListBtn);
    }
    public void enterDataInMailField(String mail)
    {
        EnterText(this.addressField,mail);
    }
    public void enterDataInMsgField(String msg)
    {
        EnterText(this.msgField,msg);
    }
    public String getSharingSuccessMsg()
    {
        return getText(this.sharedSuccessMsg);
    }

    public void clickOnAddToCartBtnFromWishListPage()
    {
        clickOn(this.addToCartBtn);
    }

}
