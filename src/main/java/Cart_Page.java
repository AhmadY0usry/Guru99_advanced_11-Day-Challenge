import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart_Page extends Page_Utils{
    public Cart_Page(WebDriver driver) {
        super(driver);
    }

    protected final By proceedToCheckout= By.cssSelector(".checkout-types.top");
    private final By statDropDown=By.id("region_id");
    private final By zipCodeField=By.id("postcode");
    protected final By estimateBtn=By.cssSelector("button[title='Estimate']");
    private final By shippingCost=By.cssSelector("label[for='s_method_flatrate_flatrate']>.price");
    protected final By ShippingRadioBtn=By.id("s_method_flatrate_flatrate");
    protected final By updateTotalBtn=By.name("do");
    protected final By grandTotal=By.cssSelector(".a-right>strong>.price");
    private final By productQty=By.cssSelector("input[title='Qty']");
    private final By updateBtn=By.cssSelector(".button.btn-update");

    public void clickUpdateBtn()
    {
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(updateBtn));
        clickOn(this.updateBtn);
    }
    public void selectFromState(String Value)
    {
        select=new Select(driver.findElement(this.statDropDown));
        select.selectByVisibleText(Value);
    }
    public void enterQty(int Qty)
    {
        clickOn(productQty);
        EnterText(productQty, ((Integer)Qty).toString());
    }
    public void enterZipCodeValue(String ZipCode)
    {
        EnterText(this.zipCodeField,ZipCode);
    }

    public String getShippingCost()
    {
        return getText(this.shippingCost);
    }
}
