import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyOrders_Page extends Page_Utils{
    public MyOrders_Page(WebDriver driver) {
        super(driver);
    }

    protected final By orderNum=By.cssSelector(".first.last.odd>.number");
    protected final By orderStatus=By.cssSelector(".status>em");
    protected final By viewOrderLink=By.xpath("//a[text()='View Order']");
    protected final By printOrderLink=By.linkText("Print Order");
}
