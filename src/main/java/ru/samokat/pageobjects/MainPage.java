package ru.samokat.pageobjects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class MainPage {

    // кнопка убрать куки
    private final SelenideElement cookieWarningButton = $("#rcc-confirm-button");
    // Логотип Яндекса в левом верхнем углу
    private final SelenideElement yandexLogo = $("[class*='Header_Logo'] img[alt*='Yandex']");
    // логотип Самоката в левом верхнем углу
    private final SelenideElement samokatLogo = $("[class*='Header_Logo'] img[alt*='Scooter']");
    // Кнопка 'Статус заказа'
    private final SelenideElement orderStatusButton = $(byText("Статус заказа"));
    // Поле ввода номера заказа
    private final SelenideElement orderNumberField = $("input[class*='Header_Input']");
    // Кнопка 'Go'
    private final SelenideElement goButton = $(byText("Go!"));
    // Кнопка 'Заказать' вверху страницы
    private final SelenideElement orderButtonUp = $("div[class*='Header_Nav'] button[class*='Button_Button']");
    // Кнопка 'Заказать' посередине страницы
    private final SelenideElement orderButtonMiddle = $("[class*='Home_FinishButton'] button");
    // Выпадающий список вопрос - ответ
    private final ElementsCollection questionList = $$("[class*='accordion__item']");

    // бираем предупреждение о куках
    public void clickCookieWarningButton() {
        if (cookieWarningButton.isDisplayed()) {
            cookieWarningButton.click();
        }
    }

    // Кликаем на логотип Яндекса, переходим на открывшееся окно, получаем Url
    public void clickYaLogo() {
        yandexLogo.click();
        switchTo().window(1, Duration.ofSeconds(15));
    }

    // Кликаем на логотип Самоката и получаем Url
    public void clickSamokatLogo() {
        samokatLogo.click();
    }

    // Нажимаем на кнопку 'Статус заказа'
    public void clickOrderStatusButton() {
        orderStatusButton.click();
    }

    // Водим номер закза
    public void setOrderNumber(String orderNumber) {
        orderNumberField.setValue(orderNumber);
    }

    // Нажимаем на кнопку 'Go'
    public OrderStatusPage clickGoButton() {
        goButton.shouldBe(enabled).click();
        return page(OrderStatusPage.class);
    }

    // Нажимаем на кнопку 'Заказать' вверху страницы
    public OrderPage clickOrderButtonUp() {
        orderButtonUp.click();
        return page(OrderPage.class);
    }

    // Прокручиваем страницу до кнопки 'Заказать' посередине страницы и нажимаем на нее
    public OrderPage clickOrderButtonMiddle() {
        orderButtonMiddle.scrollIntoView(true).click();
        return page(OrderPage.class);
    }

    // Открываем вопрос, пореряем текст ответа
    public void checkQuestion(int index, String expectedAnswer){
        questionList.get(index).scrollIntoView(true).shouldBe(visible).click();
        questionList.get(index).shouldHave(text(expectedAnswer));
    }
}