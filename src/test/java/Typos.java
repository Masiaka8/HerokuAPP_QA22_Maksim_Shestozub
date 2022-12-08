import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;

public class Typos {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testTypos() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/typos");

        List <WebElement> checkText = driver.findElements(By.tagName("p"));
        Assert.assertEquals(checkText.get(0).getText(), "This example demonstrates a typo being introduced. It does it randomly on each page load.");
        Assert.assertEquals(checkText.get(1).getText(), "Sometimes you'll see a typo, other times you won't.");
    }
}