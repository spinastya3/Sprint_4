import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.samokat.pageobjects.MainPage;
import ru.samokat.pageobjects.OrderPage;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderFormsErrorsTests {

    @Rule

    public FactoryDriver factoryDriver = new FactoryDriver();

    //Проверка появления ошибки при вводе некорректного имени
    @Test
    public void testWrongNameOrderForm() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.registration("qq123", "Поттер", "Тисовая ул", "Сокол", "+7123456789");
        assertFalse("нет сообщения о неккоректном имени", orderPageElement.isNameErrorTextDisplayed());
    }

    //Проверка появления ошибки при вводе некорректной фамилии
    @Test
    public void testWrongLastnameOrderForm() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.registration("Гарри", "qqq", "Тисовая ул", "Сокол", "+7123456789");
        assertFalse("нет сообщения о неккоректной фамилии", orderPageElement.isLastnameErrorTextDisplayed());
    }

    //Проверка появления ошибки при вводе некорректного адресса
    @Test
    public void testWrongAddressOrderForm() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.registration("Гарри", "Поттер", "123", "Сокол", "+7123456789");
        assertFalse("нет сообщения о неккоректном адрессе", orderPageElement.isAddressErrorTextDisplayed());
    }

    //Проверка появления ошибки, если станция метро не выбрана
    @Test
    public void testEmptyMetroStationForm() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.setName("Гарри");
        orderPageElement.setLastname("Поттер");
        orderPageElement.setAddress("Тисовая ул");
        orderPageElement.setPhone("+7123456789");
        orderPageElement.clickNextButton();
        assertFalse("нет собщения об пустом поле", orderPageElement.isMetroErrorTextDisplayed());
    }

    //Проверка появления ошибки при вводе некорректного номера телефона
    @Test
    public void testWrongPhoneOrderForm() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.registration("Гарри", "Поттер", "Тисовая ул", "Сокол", "+");
        orderPageElement.clickNextButton();
        assertFalse("нет сообщения о неккоректном номере телефона", orderPageElement.isPhoneErrorTextDisplayed());
    }

    //Проверка, что заказ нельзя оформить, если дата не выбрана
    @Test
    public void testEmptyDateForm() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.registration("Гарри", "Поттер", "Тисовая ул", "Сокол", "+7123456789");
        orderPageElement.clickNextButton();
        orderPageElement.setHowLong("сутки");
        orderPageElement.setBlackColour();
        orderPageElement.clickOrderButton();
        assertTrue("появилось окно подтверждения заказа", orderPageElement.isConfirmOrderWindowNotDisplayed());
    }

    //Проверка, что заказ нельзя оформить, если срок аренды не выбран
    @Test
    public void testEmptyHowLongForm() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        OrderPage orderPageElement = mainPageElement.clickOrderUpButton();
        orderPageElement.registration("Гарри", "Поттер", "Тисовая ул", "Сокол", "+7123456789");
        orderPageElement.clickNextButton();
        orderPageElement.setDate("10.10.2060");
        orderPageElement.setBlackColour();
        orderPageElement.clickOrderButton();
        assertTrue("появилось окно подтверждения заказа", orderPageElement.isConfirmOrderWindowNotDisplayed());
    }
}
