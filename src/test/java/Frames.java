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

public class Frames {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFrames() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/frames");

        WebElement iFrameButton = driver.findElement(By.cssSelector("a[href^='/iframe']"));
        iFrameButton.click();
        WebElement Iframe = driver.findElement(By.cssSelector("#mce_0_ifr"));
        driver.switchTo().frame(Iframe);
        WebElement textFrameInput = driver.findElement(By.cssSelector("#tinymce"));
        textFrameInput.click();
        Assert.assertEquals(textFrameInput.getText(), "Your content goes here.");
        driver.switchTo().defaultContent();
    }
}
