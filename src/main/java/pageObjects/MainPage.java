package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Логотип Яндекса в левом верхнем углу
    private By yandexLogotip = By.xpath(".//img[@alt = 'Yandex']");

    // Кликаем на логотип Яндекса
    public void clickYaLogotip() {
        clickElement(yandexLogotip);
    }

    // логотип Самоката в левом верхнем углу
    private By samokatLogotip = By.xpath(".//img[@alt = 'Scooter']");

    // Кликаем на логотип Самоката
    public void clickSamokattLogotip() {
        clickElement(samokatLogotip);
    }

    // Кнопка 'Статус заказа'
    private By orderStatusButton = By.className("Header_Link__1TAG7");

    // Нажимаем на кнопку 'Статус заказа'
    public void clickOrderStatusButton(){
        clickElement(orderStatusButton);
    }

    // Поле ввода номера заказа
    private By orderNumberField = By.className("Header_Input__xIoUq");

    // Водим номер закза
    public void setOrderNumber(String orderNumber){
        sendKeysToElement(orderNumberField, orderNumber);
    }

    // Кнопка 'Go'
    private By goButton = By.className("Header_Button__28dPO");

    // Нажимаем на кнопку 'Go'
    public OrderStatusPage clickGoButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(goButton));
        clickElement(goButton);
        return new OrderStatusPage(driver);
    }

    // Кнопка 'Заказать' вверху страницы
    private By orderButtonUp = By.xpath(".//button[@class='Button_Button__ra12g']");

    // Нажимаем на кнопку 'Заказать' вверху страницы
    public OrderPage clickOrderUpButton() {
        clickElement(orderButtonUp);
        return  new OrderPage(driver);
    }

    // Кнопка 'Заказать' посередине страницы
    private By orderButtonMiddle = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Прокручиваем страницу до кнопки 'Заказать' посередине страницы и нажимаем на нее
    public OrderPage clickOrderButtonMiddle() {
        WebElement element = driver.findElement(orderButtonMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        clickElement(orderButtonMiddle);
        return new OrderPage(driver);
    }

    // Массив для хранения элеметнов выпадающего списка вопросов
    private By[] questionLists = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7"),
    };

    public By[] getQuestionLists() {
        return questionLists;
    }

    // Массив для хранения элеметнов выпадающего списка ответов
    private By[] answerLists = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7"),
    };

    public By[] getAnswerLists() {
        return answerLists;
    }
}