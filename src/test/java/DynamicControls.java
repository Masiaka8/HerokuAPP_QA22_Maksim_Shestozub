import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class DynamicControls {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testDynamicControls() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        WebElement removeButton = driver.findElement(By.cssSelector("[onclick='swapCheckbox()']"));
        removeButton.click();
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's gone!"));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int numberOfElements = driver.findElements(By.cssSelector("[type=checkbox]")).size();
        assertEquals(numberOfElements, 0, "Элемент присутствует на странице");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement input = driver.findElement(By.cssSelector("[type=text]"));
        Assert.assertFalse(input.isEnabled());
        WebElement enableButton = driver.findElement(By.xpath("//*[@id='input-example']/button"));
        enableButton.click();
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's enabled!"));
        Assert.assertTrue(input.isEnabled());
    }
}