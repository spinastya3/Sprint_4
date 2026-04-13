package ru.samokat.pageobjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    // Заголовок страницы
    private final SelenideElement orderHeader = $(byText("Для кого самокат"));
    // Второй заголовок страницы
    private final SelenideElement secondOrderHeader = $(byText("Про аренду"));
    // логотип Самоката в левом верхнем углу
    private final SelenideElement samokatLogo = $("[class*='Header_Logo'] img[alt*='Scooter']");
    // Логотип Яндекса в левом верхнем углу
    private final SelenideElement yandexLogo = $("[class*='Header_Logo'] img[alt*='Yandex']");
    // Поле для ввода имени
    private final SelenideElement nameField = $("input[placeholder*='Имя']");
    // Поле для ввода фамилии
    private final SelenideElement lastnameField = $("input[placeholder*='Фамилия']");
    // Поле для ввода адресса
    private final SelenideElement addressField = $("input[placeholder*='Адрес: куда привезти заказ']");
    // Поле для ввода станции метро
    private final SelenideElement metroField = $("input[placeholder*='Станция метро']");
    // Список станций
    private final SelenideElement metroStations = $(".select-search__row");
    // Поле для ввода номера телефона
    private final SelenideElement phoneField = $("input[placeholder*='Телефон: на него позвонит курьер']");
    // Кнопка 'Далее'
    private final SelenideElement nextButton = $(byText("Далее"));
    // Поле для ввода даты
    private final SelenideElement dateField = $("input[placeholder*='Когда привезти самокат']");
    // Селектор для выбора длительности аренды
    private final SelenideElement howLongSelect = $(".Dropdown-control");
    // Список возможного срока аренды
    private final SelenideElement howLongList =$(".Dropdown-menu");
    // Поле для ввода комментария для курьера
    private final SelenideElement commentField = $("input[placeholder*='Комментарий для курьера']");
    // Кнопка 'Заказать'
    private final SelenideElement orderButton = $("[class*='Order_Buttons']").$(byText("Заказать"));
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
    private final ElementsCollection inputErrors = $$("[class*='Input_ErrorMessage']");

    @Step("Проверяем появления страницы оформления заказа по наличию поля для ввода имени")
    public void checkNameFieldDisplayed() {
        nameField.shouldBe(visible);
    }

    @Step("Вводим имя")
    public void setName(String name) {
        nameField.val(name);
    }

    @Step("Вводим фамилию")
    public void setLastname(String lastname) {
        lastnameField.val(lastname);
    }

    @Step("Вводим адресс")
    public void setAddress(String address) {
        addressField.val(address);
    }

    @Step("Выбираем станцию метро")
    public void setMetro(String metro) {
        metroField.click();
        metroField.sendKeys(metro);
        metroStations.shouldBe((visible), Duration.ofSeconds(10)).click();
    }

    @Step("Вводим номер телефона")
    public void setPhone(String phone) {
        phoneField.val(phone);
    }

    @Step("Регистрируемся")
    public OrderPage registration(String name, String lastname, String address, String metro, String phone) {
        setName(name);
        setLastname(lastname);
        setAddress(address);
        if (metro != null && !metro.isEmpty()) {
            setMetro(metro);
        }
        setPhone(phone);
        return this;
    }

    @Step("Кликаем в заголовок для смены фокуса, нажимаем кнупку 'Далее'")
    public OrderPage clickNextButton(){
        orderHeader.click();
        nextButton.click();
        return this;
    }

    @Step("Вводим дату")
    public void setDate(String date) {
        dateField.click();
        dateField.sendKeys(date);
        dateField.pressEnter();
    }

    @Step("Выбираем длительность аренды")
    public void setHowLong(String duration) {
        howLongSelect.click();
        howLongList.shouldBe(visible).$(byText(duration)).click();
    }

    @Step("Выбираем  цвет")
    public void setColour(String color) {
        if (color != null && !color.isEmpty()) {
            $(byText(color)).shouldBe(visible).click();
        }
    }

    @Step("Вводим комментарий для курьера")
    public void setComment(String comment) {
        commentField.val(comment);
    }

    @Step("Нажимаем кнопку 'Заказать'")
    public OrderPage clickOrderButton() {
        orderButton.click();
        return this;
    }

    @Step("Заполняем форму заказа")
    public OrderPage confirmOrder(String date, String duration, String color, String comment) {
        setDate(date);
        setHowLong(duration);
        setColour(color);
        setComment(comment);
        clickOrderButton();
        return this;
    }

    @Step("Нажимаем кнопку 'Да'")
    public OrderPage clickYesButton() {
        yesButton.click();
        return this;
    }

    @Step("Проверяем, что кнопка 'Посмотреть статус' заказа появилось")
    public OrderPage checkStatusButtonDisplayed() {
        statusButton.shouldBe(visible, Duration.ofSeconds(15));
        return this;
    }

    @Step("Добываем номер заказа'")
    public String getOrderNumber(){
        orderText.should(matchText(".*\\d+.*"), Duration.ofSeconds(10));
        String fullText = orderText.getText();
        String orderNumber = fullText.replaceAll("\\D+", "");
        // System.out.println("Вытащили номер заказа: " + orderNumber);
        return orderNumber;
    }

    @Step("Нажваем кнопку 'Посмотреть статус'")
    public OrderStatusPage clickStatusButton(){
        statusButton.shouldBe(interactable, Duration.ofSeconds(15)).click();
        return page(OrderStatusPage.class);
    }

    @Step("нажимаем кнопку 'Нет'")
    public OrderPage clickNoButton() {
        noButton.click();
        return this;
    }

    @Step("Проверяем, что второй заголовок страницы не пропал")
    public OrderPage checkSecondOrderHeaderDisplayed() {
        secondOrderHeader.shouldBe(visible, Duration.ofSeconds(15));
        return this;
    }

    @Step("Проверяем ошибки в полях регистрации")
    public OrderPage checkRegisrationErrors(){
        inputErrors.filter(visible).shouldHave(CollectionCondition.textsInAnyOrder(
                "Введите корректное имя",
                "Введите корректную фамилию",
                "Введите корректный адрес",
                "Введите корректный номер")
        );
        $(byText("Выберите станцию")).should(exist);
        return this;
    }

    @Step("Проверяем что окно поддтевржения не появилось")
    public OrderPage checkConfirmWindowNotDisplayed(){
        confirmWindow.shouldNotBe(visible);
        return this;
    }

    @Step("Кликаем на логотип Самоката и получаем Url")
    public void clickSamokatLogo() {
        samokatLogo.click();
    }

    @Step("Кликаем на логотип Яндекса, переходим на открывшееся окно, получаем Url")
    public void clickYaLogo() {
        yandexLogo.click();
        switchTo().window(1, Duration.ofSeconds(15));
    }
}









