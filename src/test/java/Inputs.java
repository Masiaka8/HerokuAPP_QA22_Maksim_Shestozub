import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Inputs {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testInputs() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/inputs");

        WebElement element = driver.findElement(By.tagName("input"));
        element.click();
        element.sendKeys("1234");
        element.sendKeys(Keys.ARROW_UP);
        element.sendKeys(Keys.ARROW_UP);
        element.sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(element.getAttribute("value"), "1235");
        element.clear();
        element.sendKeys("adfsdgsfgdaf");
        Assert.assertEquals(element.getAttribute("value"), "");
    }
}