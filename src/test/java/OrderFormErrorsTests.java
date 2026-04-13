import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.samokat.pageobjects.MainPage;
import ru.samokat.pageobjects.OrderPage;

@DisplayName("Негативные сценарии заказа")
public class OrderFormErrorsTests extends BaseTest{

    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        mainPage = openMainPage();
    }

    @Test
    @DisplayName("Заполнение формы регистрации невалидными данными")
    public void checkRegistrationOrderFormValidationErrors (){

        OrderPage orderPage = mainPage.clickOrderButtonUp()
        .registration("f","f","f",null, "f")
        .clickNextButton()
        .checkRegisrationErrors();
    }

    @Test
    @DisplayName("Нельзя оформить заказ без заполнения формы заказа")
    public void checkConfirmOrderFormValidationErrors (){

        mainPage.clickOrderButtonUp()
        .registration("Гермиона", "Грейнджер", "Лондон сити", "Курская", "00000000000")
        .clickNextButton()
        .clickOrderButton()
        .checkConfirmWindowNotDisplayed()
        .checkSecondOrderHeaderDisplayed();
    }

    @Test
    @DisplayName("Нельзя оформить заказ на прошедшую дату")
    public void checkPastDateOrderError(){

        mainPage.clickOrderButtonUp()
        .registration("Гермиона", "Грейнджер", "Лондон сити", "Курская", "00000000000")
        .clickNextButton()
        .confirmOrder("01.01.1600", "семеро суток", "серая безысходность", "")
        .checkConfirmWindowNotDisplayed()
        .checkSecondOrderHeaderDisplayed();
    }
}
