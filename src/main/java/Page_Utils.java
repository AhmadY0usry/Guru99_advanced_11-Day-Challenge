import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Page_Utils {

    protected WebDriver driver;
    protected Select select;
    private WebDriverWait wait;
    public Page_Utils(WebDriver driver) {
        this.driver = driver;
            }

    public String getPageTitle()
    {
        return driver.getTitle();
    }

    public void clickOn(By byElement)
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
        driver.findElement(byElement).click();
    }

    public void selectFromDropdown(By By_element, String visibleText)
    {

        select=new Select(driver.findElement(By_element));
        select.selectByVisibleText(visibleText);
    }

    public String getText(By By_Element)
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By_Element));
        return driver.findElement(By_Element).getText();
    }
    public void EnterText(By webElement,String Text)
    {
        driver.findElement(webElement).clear();
        driver.findElement(webElement).sendKeys(Text);
    }
}
