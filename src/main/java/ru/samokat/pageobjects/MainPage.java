package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class mainpage extends basepage {

    public mainpage(WebDriver driver) {
        super(driver);
    }

    // Логотип Яндекса в левом верхнем углу
    private By yandexLogo = By.xpath(".//img[@alt = 'Yandex']");

    // Кликаем на логотип Яндекса, переходим на открывшееся окно
    public void clickYaLogo() {
        driver.findElement(yandexLogo).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // логотип Самоката в левом верхнем углу
    private By samokatLogo = By.xpath(".//img[@alt = 'Scooter']");

    // Кликаем на логотип Самоката
    public void clickSamokattLogo() {
        clickElement(samokatLogo);
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
    public orderstatuspage clickGoButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(goButton));
        clickElement(goButton);
        return new orderstatuspage(driver);
    }

    // Кнопка 'Заказать' вверху страницы
    private By orderButtonUp = By.xpath(".//button[@class='Button_Button__ra12g']");

    // Нажимаем на кнопку 'Заказать' вверху страницы
    public orderpage clickOrderUpButton() {
        clickElement(orderButtonUp);
        return  new orderpage(driver);
    }

    // Кнопка 'Заказать' посередине страницы
    private By orderButtonMiddle = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Прокручиваем страницу до кнопки 'Заказать' посередине страницы и нажимаем на нее
    public orderpage clickOrderButtonMiddle() {
        WebElement element = driver.findElement(orderButtonMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        clickElement(orderButtonMiddle);
        return new orderpage(driver);
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

    // Открываем вопросы
    public void clickQuestionByIndex(int questionIndex){
        By questionLocator = questionLists[questionIndex];
        WebElement element = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
         clickElement(questionLocator);
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

    // Получаем текст ответов
    public String  getAnswerByIndex(int answerIndex){
        By answerLocator = answerLists[answerIndex];
        return driver.findElement(answerLocator).getText();
    }
}