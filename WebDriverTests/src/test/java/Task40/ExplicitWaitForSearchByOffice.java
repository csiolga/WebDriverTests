package Task40;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExplicitWaitForSearchByOffice {
    private static final String DESTINATION_URL = "https://192.168.100.26/";
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
    private WebDriver driver;

    @BeforeMethod
    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test(groups = { "smoke", "waits" })
    public void verifySearchByOfficeDisplayed() {
        //a)Go to RMSys login page
        driver.get(DESTINATION_URL);

        //RMSys login page should appear
        Assert.assertEquals("RMSys - Sign In", driver.getTitle(), "Login page isn't opened.");

        //b)Enter correct username and password
        WebElement usernameInput = driver.findElement(By.id("Username"));
        usernameInput.sendKeys(USERNAME);
        WebElement passwordInput = driver.findElement(By.id("Password"));
        passwordInput.sendKeys(PASSWORD);

        //Both inputs should be filled in.
        Assert.assertEquals(USERNAME, usernameInput.getAttribute("value"), "Incorrect Username.");
        Assert.assertEquals(PASSWORD, passwordInput.getAttribute("value"), "Incorrect Password.");

        //c)Click Submit button
        driver.findElement(By.id("SubmitButton")).click();

        //RMSys home page should appear.
        Assert.assertEquals("RMSys - Home", driver.getTitle(), "Home page isn't opened.");

        //d)Go to office tab
        driver.findElement(By.id("officeMenu")).click();

        By serchByOffice = By.cssSelector("#input-search");

        //Wait for "Search by office" input to appear (wait 15 seconds, polling frequence - 2,7 seconds).
        new WebDriverWait(driver, 15, 2700).until(ExpectedConditions.visibilityOfElementLocated(serchByOffice));

        Assert.assertTrue(driver.findElement(serchByOffice).isDisplayed(), "Search by office input is not displayed");
    }
}