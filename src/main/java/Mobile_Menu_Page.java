import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mobile_Menu_Page extends Page_Utils{

    private final By sortBy = By.cssSelector("div.toolbar-bottom select[title='Sort By']");
    private final By pageTitle= By.className("page-title");
    private final By products=By.className("product-name");
    private final By XPeriaPrice=By.cssSelector("#product-price-1 > span.price");
    private final By XPeria=By.id("product-collection-image-1");
    private String CssSelector;
    private By productCompareBtn;
    private final By compareBtn = By.cssSelector("button[title='Compare']");

    public Mobile_Menu_Page(WebDriver driver) {
        super(driver);
    }


    public void sortBy(String visibleText)
    {
        selectFromDropdown(this.sortBy,visibleText);
    }
    public String getPageTitle()
    {
        return getText(pageTitle);
    }

    public String checkSorting()
    {
        List<WebElement> products = driver.findElements(this.products);

        // Extract product names from web elements and add them to a list
        List<String> productNames = new ArrayList<>();
        for (WebElement product : products) {
            productNames.add(product.getText());
        }

        // Create a copy of the above list and sort it
        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames);

        // Compare the original list with the sorted list
        if (productNames.equals(sortedProductNames)) {
            return "The product list is sorted correctly.";
        } else {
            return "The product list is not sorted correctly.";
        }
    }

    public String getXperiaPrice()
    {
        return getText(XPeriaPrice);
    }
    public void clickOnXperia()
    {
        clickOn(XPeria);
    }

    public void clickOnCompareBtn(String productName)
    {
        CssSelector=String.format(".item.last:has(.product-name a[title='%s']) .add-to-links .link-compare",productName);
        productCompareBtn =By.cssSelector(CssSelector);
        clickOn(this.productCompareBtn);
    }
    public void clickOnAddToCartBtn(String productName)
    {
        CssSelector=String.format(".item.last:has(.product-name a[title='%s']) .button.btn-cart",productName);
        productCompareBtn =By.cssSelector(CssSelector);
        clickOn(this.productCompareBtn);
    }

    public void clickCompareBetweenProducts()
    {
        clickOn(this.compareBtn);
    }

}
