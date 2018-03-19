package Task40;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExplicitWaitForSearchByOffice {
    private static final String DESTINATION_URL = "https://192.168.100.26/";
    private static final String LOGIN_PAGE_TITLE = "RMSys - Sign In";
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
    private static final String HOME_PAGE_TITLE = "RMSys - Home";
    private static By USERNAME_LOCATOR = By.id("Username");
    private static By PASSWORD_LOCATOR = By.id("Password");
    private static By LOGIN_BUTTON = By.id("SubmitButton");
    private static By OFFICE_TAB_LOCATOR = By.id("officeMenu");
    private static By SEARCH_INPUT_LOCATOR = By.cssSelector("#input-search");
    private WebDriver driver;
    WebElement usernameInput;
    WebElement passwordInput;
    WebElement searchInput;

    @BeforeClass
    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBrowser() {
        driver.close();
    }

    @Test(groups = { "smoke" })
    public void verifyLoginPageDisplayed() {
        driver.get(DESTINATION_URL);
        String result = driver.getTitle();

        Assert.assertEquals(LOGIN_PAGE_TITLE, result,
                "Expected title is '" + LOGIN_PAGE_TITLE + "' but actual title is '" + result + "'.");
    }

    @Test(dependsOnMethods = { "verifyLoginPageDisplayed" }, groups = { "smoke" })
    public void verifyUsernameValue() {
        usernameInput = driver.findElement(USERNAME_LOCATOR);

        usernameInput.sendKeys(USERNAME);
        String result = usernameInput.getAttribute("value");

        Assert.assertEquals(USERNAME, result,
                "Expected username is '" + USERNAME + "' but actual username is '" + result + "'.");
    }

    @Test(dependsOnMethods = { "verifyUsernameValue" }, groups = { "smoke" })
    public void verifyPasswordValue() {
        passwordInput = driver.findElement(PASSWORD_LOCATOR);

        passwordInput.sendKeys(PASSWORD);
        String result = passwordInput.getAttribute("value");

        Assert.assertEquals(PASSWORD, result,
                "Expected password is '" + PASSWORD + "' but actual password is '" + result + "'.");
    }

    @Test(dependsOnMethods = { "verifyPasswordValue" }, groups = { "smoke" })
    public void verifyHomePageDisplayed() {
        driver.findElement(LOGIN_BUTTON).click();
        String result = driver.getTitle();

        Assert.assertEquals(HOME_PAGE_TITLE, result,
                "Expected title is '" + HOME_PAGE_TITLE + "' but actual title is '" + result + "'.");
    }

    @Test(dependsOnMethods = { "verifyHomePageDisplayed" }, groups = { "smoke", "wait" })
    public void verifySearchByOfficeButtonDisplayed() {
        driver.findElement(OFFICE_TAB_LOCATOR).click();
        searchInput = driver.findElement(SEARCH_INPUT_LOCATOR);
        new WebDriverWait(driver, 15, 2700).until(ExpectedConditions.visibilityOf(searchInput));

        Assert.assertTrue(searchInput.isDisplayed(), "Search by office input is not displayed");
    }
}