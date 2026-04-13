import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.samokat.pageobjects.MainPage;
import ru.samokat.pageobjects.OrderStatusPage;

import java.util.stream.Stream;

@DisplayName("Проверки страниц статуса заказа")
public class OrderStatusCheckTests extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        mainPage = openMainPage();
    }

    static Stream<Arguments> orderStatusData() {
        return Stream.of(
                Arguments.arguments("1", "NotFound"),
                Arguments.arguments("123", "Found"),
                Arguments.arguments("", "NotFound")
        );
    }

    @ParameterizedTest(name = "Поиск заказа №{0}")
    @MethodSource("orderStatusData")
    public void checkOrderStatusTest(String orderNumber, String resultType) {
        mainPage.clickOrderStatusButton()
                .setOrderNumber(orderNumber);
        OrderStatusPage orderStatusPage = mainPage.clickGoButton();
        if ("Found".equals(resultType)) {
            orderStatusPage.checkButtonDeleteOrderDisplayed();
        } else {
            orderStatusPage.checkImageNotFoundDisplayed();
        }
    }
}

