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

        OrderPage orderPage = mainPage.clickOrderButtonUp();
        orderPage.registration("f","f","f",null, "f");
        orderPage.clickNextButton();
        orderPage.checkRegisrationErrors();
    }

    @Test
    @DisplayName("Нельзя оформить заказ без заполнения формы заказа")
    public void checkConfirmOrderFormValidationErrors (){

        OrderPage orderPage = mainPage.clickOrderButtonUp();
        orderPage.registration("Гермиона", "Грейнджер", "Лондон сити", "Курская", "00000000000");
        orderPage.clickNextButton();
        orderPage.clickOrderButton();
        orderPage.checkConfirmWindowNotDisplayed();
        orderPage.checkSecondOrderHeaderDisplayed();
    }

    @Test
    @DisplayName("Нельзя оформить заказ на прошедшую дату")
    public void checkPastDateOrderError(){

        OrderPage orderPage = mainPage.clickOrderButtonUp();
        orderPage.registration("Гермиона", "Грейнджер", "Лондон сити", "Курская", "00000000000");
        orderPage.clickNextButton();
        orderPage.confirmOrder("01.01.1600", "семеро суток", "серая безысходность", "");
        orderPage.checkConfirmWindowNotDisplayed();
        orderPage.checkSecondOrderHeaderDisplayed();
    }
}
