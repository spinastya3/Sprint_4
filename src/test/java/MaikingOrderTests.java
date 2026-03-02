import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.samokat.pageobjects.MainPage;
import ru.samokat.pageobjects.OrderPage;
import static org.junit.Assert.assertTrue;

public class MaikingOrderTests {

    @Rule

    public FactoryDriver factoryDriver = new FactoryDriver();

    // Прверяем создание заказа, кнопка 'Создать' вверху страницы, срок аренды сутки, самокат черного цвета
    @Test
    public void testOrderButtonUp() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.registration("Гарри", "Поттер", "Тисовая ул", "Сокол", "+7123456789");
        orderPageElement.clickNextButton();
        orderPageElement.confirmOrder("01.01.2030","сутки", "black", "23");
        orderPageElement.clickYesButton();
        assertTrue("информация с номером заказа не появилась", orderPageElement.isConfirmOrderBroyWindowDisplayed());
    }

    // Прверяем создание заказа, кнопка 'Создать' посредине страницы, срок аренды четверо суток, самокат серого цвета
    @Test
    public void testOrderButtonMiddle() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderButtonMiddle();
        orderPageElement.registration("Рон", "Уизли", "Нора дом", "Царицино", "+7987654321");
        orderPageElement.clickNextButton();
        orderPageElement.confirmOrder("11.11.2066", "четверо суток", "gray", "");
        orderPageElement.clickYesButton();
        assertTrue("информация с номером заказа не появилась", orderPageElement.isConfirmOrderBroyWindowDisplayed());
    }
}
