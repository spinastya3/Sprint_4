import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.samokat.pageobjects.MainPage;

public class BaseTest {

    @BeforeAll
    public static void setupAll(){
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://qa-scooter.praktikum-services.ru";
    }

    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public MainPage openMainPage() {
        MainPage mainPage = Selenide.open("/", MainPage.class);
        mainPage.clickCookieWarningButton(); // Убираем куки прямо здесь
        return mainPage;
    }

    public static MainPage openMainPageStatic() {
        MainPage mainPage = Selenide.open("/", MainPage.class);
        mainPage.clickCookieWarningButton();
        return mainPage;
    }
}
