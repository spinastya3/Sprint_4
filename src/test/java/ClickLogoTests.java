import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.samokat.pageobjects.MainPage;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@DisplayName("Проверки перехода при кликах на логотипы")
public class ClickLogoTests extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        mainPage = openMainPage();
    }

    @Test
    @DisplayName("Клик по логотипу Самоката на главной странице - Главная страница Самоката")
    public void clickSamokatLogoTestFromMainPage() {

       mainPage.clickSamokatLogo();
       webdriver().shouldHave(url("https://qa-scooter.praktikum-services.ru/"));
    }

    @Test
    @DisplayName("Клик по логотипу Самоката на странице закза - Главная страница Самоката")
    public void clickSamokatLogoTestFromOrderPage() {

        mainPage.clickOrderButtonUp().clickSamokatLogo();
        webdriver().shouldHave(url("https://qa-scooter.praktikum-services.ru/"));
    }

    @Test
    @DisplayName("Клик по логотипу Яндекса на главной странице - Главная страница Яндеска в новом окне")
    public void clickYaLogoTestFromMainPage() {

        mainPage.clickYaLogo();
        webdriver().shouldHave(urlContaining("ya.ru/"), Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Клик по логотипу Яндекса на странице закза - Главная страница Яндеска в новом окне")
    public void clickYaLogoTestFromOrderPage() {

        mainPage.clickOrderButtonUp().clickYaLogo();
        webdriver().shouldHave(urlContaining("ya.ru/"), Duration.ofSeconds(15));
    }
}
