import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.samokat.pageobjects.MainPage;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    @BeforeAll
    public static void setupAll(){
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://qa-scooter.praktikum-services.ru";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
    }

    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        if (getWebDriver().getWindowHandles().size() > 1) {
            switchTo().window(0);
            for (String handle : getWebDriver().getWindowHandles()) {
                if (!handle.equals(getWebDriver().getWindowHandle())) {
                    switchTo().window(handle).close();
                }
            }
            switchTo().window(0);
        }
    }

    public MainPage openMainPage() {
        MainPage mainPage = Selenide.open("/", MainPage.class);
        mainPage.clickCookieWarningButton();
        return mainPage;
    }
}
