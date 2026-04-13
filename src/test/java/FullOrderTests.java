import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.samokat.pageobjects.MainPage;
import ru.samokat.pageobjects.OrderPage;

@DisplayName("Полный флоу создания заказа")
public class FullOrderTests extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        mainPage = openMainPage();
    }

    @Test
    @DisplayName("Создание заказа и просмотр закза через кнопку 'Посмотреть статус'")
    public void fullOrderSteps() {

        OrderPage orderPage = mainPage.clickOrderButtonUp()
                .registration("Альбус", "Дамблдор", "школа Хогвартс", "Южная", "99999999999")
                .clickNextButton()
                .confirmOrder("01.11.2042", "пятеро суток", "", "")
                .clickYesButton();
                orderPage.getOrderNumber();
                orderPage.clickStatusButton()
                        .checkButtonDeleteOrderDisplayed();
    }

    @Test
    @DisplayName("Создание заказа и его поиск по номеру")
    public void checkOrderNumberInBase() {
        OrderPage orderPage = mainPage.clickOrderButtonUp()
                .registration("Минерва", "МакГонагал", "школа Хогвартс", "Фили", "99999999999")
                .clickNextButton()
                .confirmOrder("11.01.2042", "трое суток", "", "")
                .clickYesButton();
        String orderNumber = orderPage.getOrderNumber();
        orderPage.clickStatusButton()
                .clickSamokatLogo()
                .clickOrderStatusButton()
                .setOrderNumber(orderNumber)
                .clickGoButton()
                .checkButtonDeleteOrderDisplayed();
    }
}
