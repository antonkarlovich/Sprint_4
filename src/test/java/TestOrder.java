import static org.junit.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;
import pageObject.OrderPage;

@RunWith(Parameterized.class)
public class TestOrder {
    public final By ORDER_BUTTON_TOP = By.className("Button_Button__ra12g");

    public ChromeDriver driver;
    public OrderPage orderPage;
    public MainPage mainPage;

    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;
    private final String comment;
    private final By clickOrderButton;

    public TestOrder(String name, String surname, String address, String phoneNumber, String station, String comment, By clickOrderButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.station = station;
        this.comment = comment;
        this.clickOrderButton = clickOrderButton;
    }


    @Parameterized.Parameters
    public static Object[][] getData() {
        MainPage mainPage = new MainPage();
        return new Object[][]{
                {"Иван", "Иванов", "Москва", "73454543464", "Академическая", "Комментарий 1", mainPage.ORDER_BUTTON_TOP},
                {"Смирнов", "Петр", "Москва", "+75653432376", "Баррикадная", " Комментарий 2", mainPage.ORDER_BUTTON_DOWN}
        };

    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
        orderPage = new OrderPage(driver);
        mainPage.waitElement(mainPage.getImage());
    }

    @Test
    public void checkCreateOrder() {
        mainPage.click(mainPage.ORDER_BUTTON_TOP);
        orderPage.inputPersonalInfo(name, surname, address, phoneNumber, station);
        mainPage.click(orderPage.getNextButton());
        orderPage.inputDetailRent();
        mainPage.click(orderPage.getColorScooter());
        orderPage.inputText(orderPage.getComment(), comment);
        mainPage.click(orderPage.getOrderButton());
        mainPage.click(orderPage.getButtonYes());

        assertTrue("Нет сообщения об успешно созданном заказе", mainPage.isElementPresent(orderPage.IMG_ORDER_PLACED));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
