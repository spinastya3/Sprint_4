package ru.samokat.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

public class OrderStatusPage {

    // Изображение 'Такого заказа нет'
    private final SelenideElement imageNotFound = $(By.cssSelector("img[alt='Not found']"));
    // Кнопка 'Отменить заказ'
    private final SelenideElement buttonDeleteOrder = $(byText("Отменить заказ"));
    // логотип Самоката в левом верхнем углу
    private final SelenideElement samokatLogo = $("img[alt='Scooter']");

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
        buttonDeleteOrder.shouldBe(visible, Duration.ofSeconds(15));
    }
}