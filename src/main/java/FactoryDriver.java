import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class FactoryDriver extends ExternalResource {

    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            startFirefox();
        } else {
            startChrome();
        }
    }
    public void startChrome() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void startFirefox() {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Override
    protected void before() throws Throwable {
        initDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
