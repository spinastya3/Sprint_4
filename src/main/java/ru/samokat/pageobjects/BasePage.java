package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class basepage {
    protected WebDriver driver;

    public basepage(WebDriver driver) {
        this.driver = driver;
    }

    // Класс для ввода данных в поля
    protected void sendKeysToElement(By element, String text) {
        driver.findElement(element).sendKeys(text);
    }

    // Класс для нажатия кнопок
    protected void clickElement(By element) {
        driver.findElement(element).click();
    }
}

