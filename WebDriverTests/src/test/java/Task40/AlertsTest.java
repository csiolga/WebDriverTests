package Task40;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTest {
    private static final String DESTINATION_URL = "https://the-internet.herokuapp.com/javascript_alerts";
    private static final String ALERT_BUTTON_LOCATOR = "//button[@onclick='jsAlert()']";
    private static final String CONFIRM_BUTTON_LOCATOR = "//button[@onclick='jsConfirm()']";
    private static final String PROMPT_BUTTON_LOCATOR = "//button[@onclick='jsPrompt()']";
    private static final By RESULT = By.cssSelector("#result");
    private WebDriver driver;

    @BeforeMethod
    public void openStartPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DESTINATION_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test(groups = { "smoke", "alerts" })
    public void verifyAlertAcceptResult() {
        driver.findElement(By.xpath(ALERT_BUTTON_LOCATOR)).click();
        driver.switchTo().alert().accept();

        String result = driver.findElement(RESULT).getText();

        Assert.assertEquals("You successfuly clicked an alert", result, "Incorrect result.");
    }

    @Test(groups = { "smoke", "alerts" })
    public void verifyConfirmDismissResult() {
        driver.findElement(By.xpath(CONFIRM_BUTTON_LOCATOR)).click();
        driver.switchTo().alert().dismiss();

        String result = driver.findElement(RESULT).getText();

        Assert.assertEquals("You clicked: Cancel", result, "Incorrect result.");
    }

    @Test(groups = { "smoke", "alerts" })
    public void verifyPromptResult() {
        driver.findElement(By.xpath(PROMPT_BUTTON_LOCATOR)).click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Hello");
        alert.accept();

        String result = driver.findElement(RESULT).getText();

        Assert.assertEquals("You entered: Hello", result, "Incorrect result.");
    }
}