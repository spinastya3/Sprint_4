import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.samokat.pageobjects.MainPage;
import ru.samokat.pageobjects.OrderStatusPage;
import static org.junit.Assert.assertTrue;

public class OrderStatusCheckTests {

    @Rule

    public FactoryDriver factoryDriver = new FactoryDriver();

    //Проверка страницы статуса заказа для несуществующего заказа
    @Test
    public void wrongOrderNumberTest() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        mainPageElement.clickOrderStatusButton();
        mainPageElement.setOrderNumber("1");
        OrderStatusPage orderStatusPage = mainPageElement.clickGoButton();
        assertTrue(orderStatusPage.isImageNotFoundIsDisplaid());
    }
}
