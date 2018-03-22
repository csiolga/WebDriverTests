package Task70;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class LoginRmsys {
    private static final String DESTINATION_URL = "https://192.168.100.26/";
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
    private static final By SIGN_OUT_LINK = By.cssSelector("a[title='Sign out']");
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

    @Test(groups = { "smoke", "waits" })
    public void verifySignOutLinkDisplayed() {
        driver.findElement( By.id("Username")).sendKeys(USERNAME);
        driver.findElement(By.id("Password")).sendKeys(PASSWORD);
        File loginPageScreen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.moveFile(loginPageScreen, new File("src/test/screenshots/Login page.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("SubmitButton")).click();
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(SIGN_OUT_LINK));

        Assert.assertTrue(driver.findElement(SIGN_OUT_LINK).isDisplayed(), "Sign Out link is not displayed.");
    }
}