import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.samokat.pageobjects.MainPage;
import ru.samokat.pageobjects.OrderPage;

import java.util.stream.Stream;

@DisplayName("Проверка создания заказа")
public class MakingOrderWithValidDataTests extends BaseTest {

    private  MainPage mainPage;

    @BeforeEach
    public void setup() {
        mainPage = openMainPage();
    }

    static Stream<Arguments> correctOrderData() {
        return Stream.of(
                Arguments.arguments("Гарри", "Поттер", "Тисовая ул", "Сокол", "+7123456789", "01.01.2030", "сутки", "чёрный жемчуг", "23"),
                Arguments.arguments("Рон", "Уизли", "Нора дом", "Царицыно", "+7987654321", "11.11.2066", "четверо суток", "серая безысходность", "")
        );
    }

    @Test
    @DisplayName("Проверка отмены подтверждения заказа")
    public void cancelOrderConfirmationTest(){

           mainPage.clickOrderButtonMiddle()
        .registration("Гермиона", "Грейнджер", "Лондон сити", "Курская", "00000000000")
        .clickNextButton()
        .confirmOrder("12.12.2050", "семеро суток", "серая безысходность", "")
        .clickNoButton()
        .checkSecondOrderHeaderDisplayed();
    }

    @ParameterizedTest(name="Заказ для {1}")
    @DisplayName("Проверка создания заказа с валидными данными")
    @MethodSource("correctOrderData")
    public void testOrder(String name, String lastname, String address, String metro, String phone, String date, String duration, String color, String comment) {

        mainPage.clickOrderButtonUp()
                .registration(name, lastname, address, metro, phone)
                .clickNextButton()
                .confirmOrder(date, duration, color, comment)
                .clickYesButton()
                .checkStatusButtonDisplayed();
    }
}
