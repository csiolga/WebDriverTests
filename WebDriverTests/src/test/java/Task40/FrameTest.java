package Task40;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FrameTest {
    private static final String DESTINATION_URL = "https://the-internet.herokuapp.com/iframe";
    private static final String FRAME_LOCATOR = "mce_0_ifr";
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

    @Test(groups = { "regression", "frame" })
    public void verifyAddedText() {
        //switch to frame
        driver.switchTo().frame(FRAME_LOCATOR);

        WebElement textEditor = driver.findElement(By.id("tinymce"));
        textEditor.clear();
        textEditor.sendKeys("Hello world!");

        //select 'world!'
        Actions action = new Actions(driver);
        action.keyDown(Keys.SHIFT).perform();

        for (int i = 0; i < 6; i++) {
            action.sendKeys(Keys.ARROW_LEFT).perform();
        }

        action.keyUp(Keys.SHIFT).perform();

        //Make 'world!' text bold
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("#mceu_3 > button")).click();

        driver.switchTo().frame(FRAME_LOCATOR);

        WebElement text = driver.findElement(By.cssSelector("#tinymce > p"));
        By boldTextLocator = By.cssSelector("#tinymce > p > strong");

        Assert.assertEquals("Hello world!", text.getText(), "Incorrect text.");
        Assert.assertTrue(isElementPresent(boldTextLocator), "No bold text.");
        Assert.assertEquals("world!", driver.findElement(boldTextLocator).getText(), "Incorrect bold text.");
    }

    //check if an element is present in DOM
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}