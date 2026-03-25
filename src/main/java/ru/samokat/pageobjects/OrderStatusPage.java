package ru.samokat.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

public class OrderStatusPage {

    // Изображение 'Такого заказа нет'
    private final SelenideElement imageNotFound = $("img[alt*='Not found']");
    // Кнопка 'Отменить заказ'
    private final SelenideElement buttonDeleteOrder = $(byText("Отменить заказ"));
    // логотип Самоката в левом верхнем углу
    private final SelenideElement samokatLogo = $("[class*='Header_Logo'] img[alt*='Scooter']");

    // Кликаем на логотип Самоката и получаем Url
    public MainPage clickSamokatLogo() {
        samokatLogo.click();
        return page(MainPage.class);
    }

    // Проверяем, что изобаржение 'Такого заказа нет' появилось
    public void checkImageNotFoundDisplayed() {
        imageNotFound.shouldBe(visible, Duration.ofSeconds(15));
    }

    // Проверяем, что кнопка 'Отменить заказ' появилось
    public void checkButtonDeleteOrderDisplayed() {
        if (buttonDeleteOrder.is(visible)){
            Selenide.refresh();
        }
        buttonDeleteOrder.shouldBe(visible, Duration.ofSeconds(15));
    }
}