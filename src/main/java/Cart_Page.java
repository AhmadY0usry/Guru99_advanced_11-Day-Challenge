import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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
    public void selectFromState(String Value)
    {
        select=new Select(driver.findElement(this.statDropDown));
        select.selectByVisibleText(Value);
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
