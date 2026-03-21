import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.samokat.pageobjects.MainPage;
import java.util.stream.Stream;

@DisplayName("Проверки соответствия текста ответов")
public class QuestionsTests extends BaseTest{

    private static MainPage mainPage;

    @BeforeAll
    public static void setup() {

        mainPage = openMainPageStatic();
    }

    static Stream<Arguments> correctAnswers() {
        return Stream.of(
                Arguments.arguments(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.arguments(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.arguments(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.arguments(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.arguments(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.arguments(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.arguments(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.arguments(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest(name = "Проверка текста ответа для вопроса №{0}")
    @DisplayName("Проверка текста ответов")
    @MethodSource("correctAnswers")
    public void checkAnswersTest(int index, String expectedAnswer){
        mainPage.checkQuestion(index, expectedAnswer);
    }
}
