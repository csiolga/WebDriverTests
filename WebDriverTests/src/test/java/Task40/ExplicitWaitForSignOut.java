package Task40;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExplicitWaitForSignOut {
    private static final String DESTINATION_URL = "https://192.168.100.26/";
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
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
        driver.findElement(By.id("Username")).sendKeys(USERNAME);
        driver.findElement(By.id("Password")).sendKeys(PASSWORD);
        driver.findElement(By.id("SubmitButton")).click();

        By signOutLink = By.cssSelector("a[title='Sign out']");
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(signOutLink));

        Assert.assertTrue(driver.findElement(signOutLink).isDisplayed(), "Sign Out link is not displayed.");
    }
}