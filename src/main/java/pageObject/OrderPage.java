package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //блок личной информации заказа
    private final By ORDER_HEADER = By.xpath("//div[(text()= 'Для кого самокат')]");
    private final By INPUT_NAME = By.xpath("//input[@placeholder ='* Имя']");
    private final By INPUT_SURNAME = By.xpath("//input[@placeholder ='* Фамилия']");
    private final By INPUT_ADDRESS = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");
    private final By INPUT_METRO = By.xpath("//input[@placeholder ='* Станция метро']");
    private final By INPUT_PHONE = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private final By BUTTON_NEXT = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    //блок подробностей аренды
    private final By RENT_HEADER = By.xpath("//div[(text()= 'Про аренду')]");
    private final By INPUT_DATE_IN_CALENDAR = By.xpath("//input[@placeholder ='* Когда привезти самокат']");
    private final By INPUT_DATE = By.xpath("//div[contains(@class, 'day--today')]");
    private final By INPUT_RENTAL_PERIOD = By.className("Dropdown-placeholder");
    private final By INPUT_RENTAL_DAY_IN_DROP_DAWN = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']");
    private final By CHECKBOX_COLOR = By.id("grey");
    private final By INPUT_COMMENT = By.xpath("//input[@placeholder ='Комментарий для курьера']");
    private final By BUTTON_ORDER = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[contains(text(), 'Заказать')]");
    private final By BUTTON_YES = By.xpath("//button[contains(text(), 'Да')]");
    public final By IMG_ORDER_PLACED = By.xpath("//div[(text()= 'Заказ оформлен')]");

    public void inputPersonalInfo(String name, String surname, String address, String phone, String metro) {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitElement(getTitleOrder());
        driver.findElement(INPUT_NAME).sendKeys(name);
        driver.findElement(INPUT_SURNAME).sendKeys(surname);
        driver.findElement(INPUT_ADDRESS).sendKeys(address);
        driver.findElement(INPUT_PHONE).sendKeys(phone);
        WebElement webElement = driver.findElement(INPUT_METRO);
        new Actions(driver).moveToElement(webElement).click().sendKeys(metro)
                .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();


    }

    public void inputDetailRent() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitElement(getTitleRent());
        driver.findElement(INPUT_DATE_IN_CALENDAR).click();
        driver.findElement(INPUT_DATE).click();
        driver.findElement(INPUT_RENTAL_PERIOD).click();
        driver.findElement(INPUT_RENTAL_DAY_IN_DROP_DAWN).click();
    }

    public By getTitleOrder() {
        return ORDER_HEADER;
    }

    public WebElement getNextButton() {
        return driver.findElement(BUTTON_NEXT);
    }

    public By getTitleRent() {
        return RENT_HEADER;
    }

    public WebElement getColorScooter() {
        return driver.findElement(CHECKBOX_COLOR);
    }

    public void inputText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public WebElement getComment() {
        return driver.findElement(INPUT_COMMENT);
    }

    public WebElement getOrderButton() {
        return driver.findElement(BUTTON_ORDER);
    }

    public WebElement getButtonYes() {
        return driver.findElement(BUTTON_YES);
    }
}
