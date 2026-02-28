import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.MainPage;import static org.junit.Assert.assertTrue;

public class ImportantQestionsTests {

    @Rule

    public FactoryDriver factoryDriver = new FactoryDriver();

    // Прверяем, что при нажатии на стрелочку вападающего списка 'Вопросы о важном', открывается соответсвующий ответ
    @Test
    public void test() {

        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPageElement = new MainPage(driver);
        By[] questionLists = mainPageElement.getQuestionLists();
        By[] answerLists = mainPageElement.getAnswerLists();

        for (int i = 0; i< questionLists.length; i++) {
            WebElement questionElement = driver.findElement(questionLists[i]);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);
            questionElement.click();
            assertTrue("ответ не открылся", driver.findElement(answerLists[i]).isDisplayed());
        }
    }
}
