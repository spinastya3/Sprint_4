package ru.samokat.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

public class OrderStatusPage {

    // Изображение 'Такого заказа нет'
    private final SelenideElement imageNotFound = $("img[alt*='Not found']");
    // Кнопка 'Отменить заказ'
    private final SelenideElement buttonDeleteOrder = $(byText("Отменить заказ"));
    // логотип Самоката в левом верхнем углу
    private final SelenideElement samokatLogo = $("[class*='Header_Logo'] img[alt*='Scooter']");

    @Step("Кликаем на логотип Самоката и получаем Url")
    public MainPage clickSamokatLogo() {
        samokatLogo.click();
        return page(MainPage.class);
    }

    @Step("Проверяем, что изобаржение 'Такого заказа нет' появилось")
    public void checkImageNotFoundDisplayed() {
        imageNotFound.shouldBe(visible, Duration.ofSeconds(15));
    }

    @Step("Проверяем, что кнопка 'Отменить заказ' появилась'")
    public OrderStatusPage checkButtonDeleteOrderDisplayed() {
        if (buttonDeleteOrder.is(visible)){
            Selenide.refresh();
        }
        buttonDeleteOrder.shouldBe(visible, Duration.ofSeconds(15));
        return this;
    }
}