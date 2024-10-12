import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetails_Page extends Page_Utils{
    public ProductDetails_Page(WebDriver driver) {
        super(driver);
    }

    private WebDriverWait wait;
    private final By productPrice=By.className("price");
    private final By productQty=By.cssSelector("input[title='Qty']");
    private final By AddToCartBtn=By.cssSelector(".button.btn-cart");
    private final By errorMsg=By.className("error-msg");
    private final By updateBtn=By.cssSelector(".button.btn-update");
    private final By emptyCartBtn=By.id("empty_cart_button");
    private final By CartErrorMsg=By.className("page-title");
    private final By noItemStg=By.className("cart-empty");



    public String getProductPrice()
    {
        return getText(productPrice);
    }

    public void enterQty(int Qty)
    {
        clickOn(productQty);
        EnterText(productQty, ((Integer)Qty).toString());
    }

    public void clickAddToCartBtn()
    {
        clickOn(AddToCartBtn);
    }

    public String getErrorMsg()
    {

        return getText(this.errorMsg);
    }

    public void clickUpdateBtn()
    {
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(updateBtn));
        clickOn(this.updateBtn);
    }

    public void clickOnEmptyCardBtn()
    {
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartBtn));
        clickOn(this.emptyCartBtn);
    }

    public String getEmptyErrorMsg()
    {
        return getText(CartErrorMsg);
    }

    public String checkCartIsEmpty()
    {
        return getText(this.noItemStg);
    }
}
