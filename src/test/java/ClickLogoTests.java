import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.samokat.pageobjects.MainPage;
import static org.junit.Assert.assertEquals;

public class ClickLogoTests {

    @Rule

    public FactoryDriver factoryDriver = new FactoryDriver();

    // Проверяем, что по клику на логотип Самоката, попадаем на глвную страницу Самоката
    @Test
    public void clickSamokatLogoTest()  {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        String finalUrl = mainPageElement.clickSamokattLogo();
        assertEquals("Переход по неверному адрессу", "https://qa-scooter.praktikum-services.ru/", finalUrl);
    }

    // Проверяем, что по клику на логотип Яндекса, открывается новое окно с главной страницей Яндекса
    @Test
    public void clickYaLogoTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        String finalUrl = mainPageElement.clickYaLogo();
        assertEquals("Переход по неверному адрессу", "https://ya.ru/", finalUrl);
    }
}
