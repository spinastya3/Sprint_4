package ru.samokat.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    // Поле для ввода имени
    private By nameField = By.xpath(".//input[@placeholder= '* Имя']");

    // Ошибка при вводе имени
    private By nameError = By.xpath(".//div[text()='Введите корректное имя']");
    public boolean isNameErrorTextDisplayed() {
        return driver.findElements(nameError).isEmpty();
    }

    // Поле для ввода фамилии
    private By lastnameField = By.xpath(".//input[@placeholder= '* Фамилия']");

    // Ошибка при вводе фамилии
    private By lastNameError = By.xpath(".//div[text()='Введите корректную фамилию']");
    public boolean isLastnameErrorTextDisplayed() {
        return driver.findElements(lastNameError).isEmpty();
    }

    // Поле для ввода адресса
    private By adressField = By.xpath(".//input[@placeholder= '* Адрес: куда привезти заказ']");

    // Ошибка при вводе адресса
    private By addressError = By.xpath(".//div[text()='Введите корректный адрес']");
    public boolean isAddressErrorTextDisplayed() {
        return driver.findElements(addressError).isEmpty();
    }

    // Поле для ввода станции метро
    private By metroField = By.className("select-search__input");

    // Ошибка при пустом поле метро
    private By emptyMetroError = By.className("Order_MetroError__1BtZb");
    public boolean isMetroErrorTextDisplayed() {
        return driver.findElements(emptyMetroError).isEmpty();
    }

    // Поле для ввода номера телефона
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Ошибка при вводе номера телефона
    private By phoneError = By.xpath(".//div[text()='Введите корректный номер']");
    public boolean isPhoneErrorTextDisplayed() {
        return driver.findElements(phoneError).isEmpty();
    }

    // Кнопка 'Далее'
    private By nextButton = By.className("Button_Middle__1CSJM");

    // Вводим имя
    public void setName(String name) {
        sendKeysToElement(nameField, name);
    }

    // Вводим фамилию
    public void setLastname(String lastname) {
        sendKeysToElement(lastnameField, lastname);
    }

    // Вводим адресс
    public void setAddress(String address) {
        sendKeysToElement(adressField, address);
    }

    // Выбираем станцию метро
    public void setMetro(String metro) {
        WebElement metroFieldElement = driver.findElement(metroField);
        metroFieldElement.sendKeys(metro);
        metroFieldElement.sendKeys(Keys.DOWN);
        metroFieldElement.sendKeys(Keys.ENTER);
    }

    // Вводим номер телефона
    public void setPhone(String phone) {
        sendKeysToElement(phoneField, phone);
    }

    // Регистрируемся
    public void registration(String name, String lastname, String address, String metro, String phone) {
        setName(name);
        setLastname(lastname);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
    }

    // Нажимаем кнопку 'Далее'
    public void clickNextButton() {
        clickElement(nextButton);
    }

    // Поле для ввода даты
    private By dateField = By.xpath(".//input[@placeholder= '* Когда привезти самокат']");

    // Селектор для выбора длительности аренды
    private By howLongSelect = By.className("Dropdown-control");

    // Чек бокс для выбора черного цвета
    private By colourBlackCheck = By.id("black");

    // Чек бокс для выбора серого цвета
    private By colourGrayCheck = By.id("grey");

    // Поле для ввода комментария для курьера
    private By commentField = By.xpath(".//input[@placeholder= 'Комментарий для курьера']");

    // Кнопка 'Заказать'
    private By orderButton = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    // Вводим дату
    public void setDate(String date) {
        sendKeysToElement(dateField, date);
        driver.findElement(dateField).sendKeys(Keys.ENTER);
    }

    // Выбираем длительность аренды
    public void setHowLong(String duration) {
        driver.findElement(howLongSelect).click();
        driver.findElement(By.xpath(".//div[text()='" + duration + "']")).click();
    }

    // Выбираем черный цвет
    public void setBlackColour() {
        clickElement(colourBlackCheck);
    }

    // Выбираем серый цвет
    public void setGrayColour() {
        clickElement(colourGrayCheck);
    }

    // Вводим комментарий для курьера
    public void setComment(String comment) {
        sendKeysToElement(commentField, comment);
    }

    // Нажимаем кнопку 'Заказать'
    public void clickOrderButton() {
        clickElement(orderButton);
    }

    // Заполняем форму заказа
    public void confirmOrder(String date, String duration, String color, String comment) {
        setDate(date);
        setHowLong(duration);
        if (color.equalsIgnoreCase("black")) {
            setBlackColour();
        } else if (color.equalsIgnoreCase("gray")) {
            setGrayColour();
        }
        setComment(comment);
        clickOrderButton();
    }

    // Окно поддтверждения заказа
    private By confirmOrderWindow = By.className("Order_Modal__YZ-d3");

    // Проверяем, что окно поддтверждения заказа не появилось
    public boolean isConfirmOrderWindowNotDisplayed() {
        return driver.findElements(confirmOrderWindow).isEmpty();
    }

    // Кнопка 'Да'
    private By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    // Нажимаем кнопку 'Да'
    public void clickYesButton() {
        clickElement(yesButton);
    }

    // Кнопка 'Посмотреть статус'
    private By statusButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");


    // Проверяем, что кнопка 'Посмотреть статус' заказа появилось
    public boolean isConfirmOrderBroyWindowDisplayed() {
        return !driver.findElements(statusButton).isEmpty();
    }
}