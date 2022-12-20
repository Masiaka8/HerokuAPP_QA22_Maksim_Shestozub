import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileUploader {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFileUploader() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/upload");

        WebElement fileInputButton = driver.findElement(By.cssSelector("#file-upload"));
        fileInputButton.sendKeys(System.getProperty("user.dir") + "/src/test/java/Resources/Screenshot_6.png");
        WebElement uploadButton = driver.findElement(By.cssSelector("#file-submit"));
        uploadButton.click();
        WebElement fileUploadMessage = driver.findElement(By.cssSelector("#uploaded-files"));
        Assert.assertEquals(fileUploadMessage.getText(), "Screenshot_6.png");
    }
}