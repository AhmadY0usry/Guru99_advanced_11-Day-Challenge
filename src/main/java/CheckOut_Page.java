import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class CheckOut_Page extends Page_Utils{
    public CheckOut_Page(WebDriver driver) {
        super(driver);
    }

    protected final By addressField=By.id("billing:street1");
    protected final By CityField = By.id("billing:city");
    protected final By StateField = By.id("billing:region_id"); // dropDown
    protected final By ZipField= By.id("billing:postcode");
    protected final By CountryField= By.id("billing:country_id"); // dropDown
    protected final By TelephoneField= By.id("billing:telephone");
    protected final By billingInfoContinueBtn =By.cssSelector("#billing-buttons-container>.button");
    protected final By shippingContinueBtn =By.cssSelector(".button[onclick='shippingMethod.save()']");
    protected final By checkMoneyOrderRadioBtn= By.id("p_method_checkmo");
    protected final By paymentInfoContinue=By.cssSelector(".button[onclick='payment.save()']");
    protected final By placeOrderBtn= By.cssSelector(".button.btn-checkout");
    protected final By orderID=By.cssSelector(".col-main >p:first-of-type >a");



}
