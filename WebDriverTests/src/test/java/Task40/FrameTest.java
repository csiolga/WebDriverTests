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
import org.testng.asserts.SoftAssert;

public class FrameTest {
    private static final String DESTINATION_URL = "https://the-internet.herokuapp.com/iframe";
    private static final String FRAME_LOCATOR = "mce_0_ifr";
    private static final String TEXT = "Hello world!";
    private static final String BOLD_TEXT= "world!";
    private static final By TEXT_EDITOR_LOCATOR = By.id("tinymce");
    private static final By BUTTON_BOLD_LOCATOR = By.cssSelector("#mceu_3 > button");
    private static final By TEXT_ELEMENT_LOCATOR = By.cssSelector("#tinymce > p");
    private static final By BOLD_TEXT_LOCATOR = By.cssSelector("#tinymce > p > strong");
    WebDriver driver;
    WebElement textEditor;
    Actions action;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void startup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DESTINATION_URL);

        driver.switchTo().frame(FRAME_LOCATOR);
        textEditor = driver.findElement(TEXT_EDITOR_LOCATOR);
        textEditor.clear();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test(groups = { "regression", "frame" })
    public void verifyAddedText() {
        textEditor.sendKeys(TEXT);
        selectText();
        driver.switchTo().defaultContent();
        driver.findElement(BUTTON_BOLD_LOCATOR).click();
        driver.switchTo().frame(FRAME_LOCATOR);
        String textResult = driver.findElement(TEXT_ELEMENT_LOCATOR).getText();
        String bolTextResult = driver.findElement(BOLD_TEXT_LOCATOR).getText();

        softAssert.assertEquals(TEXT, textResult, "Expected text is '" + TEXT + "' but actual text is '" + textResult + "'.");
        softAssert.assertTrue(isElementPresent(BOLD_TEXT_LOCATOR), "There is no bold text.");
        softAssert.assertEquals(BOLD_TEXT, bolTextResult, "Expected bold text is '" + BOLD_TEXT + "' but actual bold text is '" + bolTextResult + "'.");
        softAssert.assertAll();
    }

    //select text 'world!'
    public void selectText() {
        action = new Actions(driver);
        action.keyDown(Keys.SHIFT).perform();

        for (int i = 0; i < 6; i++) {
            action.sendKeys(Keys.ARROW_LEFT).perform();
        }
        action.keyUp(Keys.SHIFT).perform();
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