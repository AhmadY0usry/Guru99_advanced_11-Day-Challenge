import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class Test_Base {

    protected ChromeDriver driver;
    protected String baseUrl;

    @BeforeClass
    public void setUp()
    {
        baseUrl="http://live.techpanda.org/index.php/";
        driver=new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
}
