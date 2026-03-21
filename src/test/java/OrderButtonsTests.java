import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.samokat.pageobjects.MainPage;

@DisplayName("Проверка открытия формы оформления заказа при клике на кнопки 'Заказать'")
public class OrderButtonsTests extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        mainPage = openMainPage();
    }

    @Test
    @DisplayName("Верхняя кнопка 'Заказать'")
    public void clickOrderButtonUp(){
        mainPage.clickOrderButtonUp().checkNameFieldDisplayed();
    }

    @Test
    @DisplayName("Средняя кнопка 'Заказать'")
    public void clickOrderButtonMiddle(){
        mainPage.clickOrderButtonMiddle().checkNameFieldDisplayed();
    }
}




