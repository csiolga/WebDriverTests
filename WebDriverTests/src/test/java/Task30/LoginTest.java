package Task30;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private static final String DESTINATION_URL = "https://192.168.100.26/";
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
    private WebDriver driver;

    @BeforeMethod
    public void openStartPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(DESTINATION_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loginRmsys() {
        driver.findElement(By.id("Username")).sendKeys(USERNAME);
        driver.findElement(By.id("Password")).sendKeys(PASSWORD);
        driver.findElement(By.id("SubmitButton")).click();

        String name = driver.findElement(By.id("name")).getText();

        Assert.assertEquals("Borisik, Eugen", name, "Name is");
    }
}