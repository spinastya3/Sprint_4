package ru.samokat.pageobjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    // Заголовок страницы
    private final SelenideElement orderHeader = $(byText("Для кого самокат"));
    // Второй заголовок страницы
    private final SelenideElement secondOrderHeader = $(byText("Про аренду"));
    // логотип Самоката в левом верхнем углу
    private final SelenideElement samokatLogo = $("img[alt='Scooter']");
    // Логотип Яндекса в левом верхнем углу
    private final SelenideElement yandexLogo = $("img[alt = 'Yandex']");
    // Поле для ввода имени
    private final SelenideElement nameField = $(By.xpath(".//input[@placeholder= '* Имя']"));
    // Поле для ввода фамилии
    private final SelenideElement lastnameField = $(By.xpath(".//input[@placeholder= '* Фамилия']"));
    // Поле для ввода адресса
    private final SelenideElement addressField = $(By.xpath(".//input[@placeholder= '* Адрес: куда привезти заказ']"));
    // Поле для ввода станции метро
    private final SelenideElement metroField = $(By.xpath(".//input[@placeholder='* Станция метро']"));
    // Поле для ввода номера телефона
    private final SelenideElement phoneField = $(By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"));
    // Кнопка 'Далее'
    private final SelenideElement nextButton = $(byText("Далее"));
    // Поле для ввода даты
    private final SelenideElement dateField = $(By.xpath(".//input[@placeholder= '* Когда привезти самокат']"));
    // Селектор для выбора длительности аренды
    private final SelenideElement howLongSelect = $(By.className("Dropdown-control"));
    // Поле для ввода комментария для курьера
    private final SelenideElement commentField = $(By.xpath(".//input[@placeholder= 'Комментарий для курьера']"));
    // Кнопка 'Заказать'
    private final SelenideElement orderButton = $("div[class*='Order_Buttons']").$(byText("Заказать"));
    // Окно поддтверждения заказа
    private final SelenideElement confirmWindow = $(byText("Хотите оформить заказ?"));
    // Кнопка 'Да'
    private final SelenideElement yesButton = $(byText("Да"));
    // Кнопка 'Нет'
    private final SelenideElement noButton = $(byText("Нет"));
    // Сообщение с номером заказа
    private final SelenideElement orderText = $("[class*='Order_Text']");
    // Кнопка 'Посмотреть статус'
    private final SelenideElement statusButton = $(byText("Посмотреть статус"));
    // Ошибки полей регистрации
    private final ElementsCollection inputErrors = $$("div[class*='Input_ErrorMessage']");

    // Проверяем появления страницы оформления заказа по наличию поля для ввода имени
    public void checkNameFieldDisplayed() {
        nameField.shouldBe(visible);
    }

    // Вводим имя
    public void setName(String name) {
        nameField.val(name);
    }

    // Вводим фамилию
    public void setLastname(String lastname) {
        lastnameField.val(lastname);
    }

    // Вводим адресс
    public void setAddress(String address) {
        addressField.val(address);
    }

    // Выбираем станцию метро
    public void setMetro(String metro) {
        metroField.val(metro);
        $(byText(metro)).shouldBe((visible)).click();
    }

    // Вводим номер телефона
    public void setPhone(String phone) {
        phoneField.val(phone);
    }

    // Регистрируемся
    public void registration(String name, String lastname, String address, String metro, String phone) {
        setName(name);
        setLastname(lastname);
        setAddress(address);
        if (metro != null && !metro.isEmpty()) {
            setMetro(metro);
        }
        setPhone(phone);
    }

    // Кликаем в заголовок для смены фокуса, нажимаем кнупку 'Далее'
    public void clickNextButton(){
        orderHeader.click();
        nextButton.click();
    }

    // Вводим дату
    public void setDate(String date) {
        dateField.val(date).pressEnter();
    }

    // Выбираем длительность аренды
    public void setHowLong(String duration) {
        howLongSelect.click();
        $(byText(duration)).shouldBe(visible).click();
    }

    // Выбираем  цвет
    public void setColour(String color) {
        if (color != null && !color.isEmpty()) {
            $(byText(color)).shouldBe(visible).click();
        }
    }

    // Вводим комментарий для курьера
    public void setComment(String comment) {
        commentField.val(comment);
    }

    // Нажимаем кнопку 'Заказать'
    public void clickOrderButton() {
        orderButton.click();
    }

    // Заполняем форму заказа
    public void confirmOrder(String date, String duration, String color, String comment) {
        setDate(date);
        setHowLong(duration);
        setColour(color);
        setComment(comment);
        clickOrderButton();
    }

    // Нажимаем кнопку 'Да'
    public void clickYesButton() {

        yesButton.click();
    }

    // Проверяем, что кнопка 'Посмотреть статус' заказа появилось
    public void checkStatusButtonDisplayed() {
        statusButton.shouldBe(visible, Duration.ofSeconds(15));
    }

    // Добываем номер заказа
    public String getOrderNumber(){
        orderText.should(matchText(".*\\d+.*"), Duration.ofSeconds(10));
        String fullText = orderText.getText();
        String orderNumber = fullText.replaceAll("\\D+", "");
        // System.out.println("Вытащили номер заказа: " + orderNumber);
        return orderNumber;
    }

    //Нажваем кнопку 'Посмотреть статус'
    public OrderStatusPage clickStatusButton(){
        statusButton.shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(OrderStatusPage.class);
    }

    // нажимаем кнопку 'Нет'
    public void clickNoButton() {
        noButton.click();
    }

    // Проверяем, что второй заголовок страницы не пропал
    public void checkSecondOrderHeaderDisplayed() {
        secondOrderHeader.shouldBe(visible, Duration.ofSeconds(15));
    }

    // Проверяем ошибки в полях регистрации
    public void checkRegisrationErrors(){
        inputErrors.filter(visible).shouldHave(CollectionCondition.textsInAnyOrder(
                "Введите корректное имя",
                "Введите корректную фамилию",
                "Введите корректный адрес",
                "Введите корректный номер")
        );
        $(byText("Выберите станцию")).should(exist);
    }

    //Проверяем что окно поддтевржения не появилось
    public void checkConfirmWindowNotDisplayed(){
        confirmWindow.shouldNotBe(visible);
    }

    // Кликаем на логотип Самоката и получаем Url
    public void clickSamokatLogo() {
        samokatLogo.click();
    }

    // Кликаем на логотип Яндекса, переходим на открывшееся окно, получаем Url
    public void clickYaLogo() {
        yandexLogo.click();
        switchTo().window(1);
    }
}









