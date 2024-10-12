import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductComparison_Page extends Page_Utils{
    public ProductComparison_Page(WebDriver driver) {
        super(driver);
    }

    private final By pageTitle=By.cssSelector("h1");

    public String getPageTitle()
    {
        return getText(pageTitle);
    }


}
