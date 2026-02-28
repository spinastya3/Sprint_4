import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.MainPage;
import static org.junit.Assert.assertEquals;

public class ClickLogoTests {

    @Rule

    public FactoryDriver factoryDriver = new FactoryDriver();

    // Проверяем, что но клику на логотип Самоката попадаем на глвную страницу Самоката
    @Test
    public void ClickSamokatLogoTest()  {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        mainPageElement.clickSamokattLogotip();
        String finalUrl = driver.getCurrentUrl();
        assertEquals("Переход по неверному адрессу", "https://qa-scooter.praktikum-services.ru/", finalUrl);
    }

    // Проверяем, что но клику на логотип Яндекса открывается новое окно с главной страницей Яндекса
    @Test
    public void ClickYaLogoTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        String initialWindowHandle = driver.getWindowHandle();
        mainPageElement.clickYaLogotip();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String finalUrl = driver.getCurrentUrl();
        assertEquals("Переход по неверному адрессу", "https://ya.ru/", finalUrl);
    }
}
