import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TVMenu_Page extends Page_Utils{
    public TVMenu_Page(WebDriver driver) {
        super(driver);
    }

    public void addToWishList(String productName)
    {
        String cssSelector=String.format(".item.last:has(.product-name a[title='%S']) .add-to-links .link-wishlist",productName);
        clickOn(By.cssSelector(cssSelector));
    }
}
