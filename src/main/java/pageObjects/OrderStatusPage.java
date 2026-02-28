package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderStatusPage {

    private WebDriver driver;
    private WebDriverWait wait;


    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    // Изображение 'Такого заказа нет'
    private By imageNotFound = By.xpath(".//img[@alt='Not found']");

    // Проверяем, что изобаржение 'Такого заказа нет' появилось
    public boolean isImageNotFoundIsDisplaid() {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(imageNotFound));
        return driver.findElement(imageNotFound).isDisplayed();
    }
}
