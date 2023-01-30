package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    //локаторы на главной странице
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By STATUS_STATE_BUTTON = By.cssSelector("button.Header_Link__1TAG7");
    private static final By INPUT_FIELD = By.xpath("//*[@class=\"Input_Input__1iN_Z Header_Input__xIoUq\"]");
    private static final By BUTTON_GO = By.xpath("//*[@class=\"Button_Button__ra12g Header_Button__28dPO\"]");
    private static final By IMG_NOT_FOUND = By.cssSelector("div.Track_NotFound__6oaoY > img");
    public final By ORDER_BUTTON_TOP = By.className("Button_Button__ra12g");
    public final By ORDER_BUTTON_DOWN = By.xpath("//button[contains(@class, 'Button_Middle')]");
    private final By IMG_SCOOTER = By.xpath("//img[@alt='Scooter blueprint']");
    // Локаторы из списка вопросов и ответов
    private final By LIST_QUESTIONS = By.xpath("//*[@class='accordion__button']"); // элемент вопросов
    private final By LIST_ANSWER = By.xpath("//div[@data-accordion-component='AccordionItemPanel']"); // элемент ответов
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage() {
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public MainPage clickOrderStatusButton() {
        driver.findElement(STATUS_STATE_BUTTON).click();
        return this;
    }

    public MainPage enterOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(INPUT_FIELD));
        driver.findElement(INPUT_FIELD).clear();
        driver.findElement(INPUT_FIELD).sendKeys(orderNumber);
        driver.findElement(BUTTON_GO).click();
        return this;
    }

    public boolean isImageNotFoundDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(IMG_NOT_FOUND));
        return driver.findElement(IMG_NOT_FOUND).isDisplayed();
    }

    public By getImage() {
        return IMG_SCOOTER;
    }

    public void scroll(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void click(WebElement webElement) {
        scroll(webElement);
        webElement.click();
    }

    public void click(By element) {
        WebElement webElement = driver.findElement(element);
        scroll(webElement);
        webElement.click();
    }

    public void waitElement(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public List<WebElement> getListQuestions() {
        return driver.findElements(LIST_QUESTIONS);
    }

    public List<WebElement> getListAnswer() {
        return driver.findElements(LIST_ANSWER);
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}

