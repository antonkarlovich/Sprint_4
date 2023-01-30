import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;

public class TestOrderStatus {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void findOrder_withPom_expectNotFound() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("123");
        Assert.assertTrue(mainPage.isImageNotFoundDisplayed());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
