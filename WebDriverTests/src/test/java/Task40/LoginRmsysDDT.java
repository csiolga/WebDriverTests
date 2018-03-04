package Task40;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginRmsysDDT {
    private static final String DESTINATION_URL = "https://192.168.100.26/";
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
    private static final String BY_DOMAIN = "BY-MINSK";
    private static final String US_DOMAIN = "US-MPLS";

    private static final By DOMAIN_DROPDOWN = By.xpath("//ul[contains(@class,'domains')]");
    private static final By CURRENT_DOMAIN = By.xpath("//li[@class='current-domain']");
    private static final By REMEBER_CHECKBOX = By.cssSelector(".remember-chBox");
    private WebDriver driver;

    @BeforeMethod
    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DESTINATION_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

   @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                new Object[]{BY_DOMAIN, USERNAME, PASSWORD, true},
                new Object[]{BY_DOMAIN, USERNAME, PASSWORD, false},
                new Object[]{US_DOMAIN, USERNAME, PASSWORD, true},
                new Object[]{US_DOMAIN, USERNAME, PASSWORD, false},
        };
    }

    @Test(groups = { "happypath" }, dataProvider = "loginData")
    public void loginRmsys (String domain, String username, String password, Boolean remember) {
        if(!driver.findElement(CURRENT_DOMAIN).getText().equals(domain)) {
            driver.findElement(DOMAIN_DROPDOWN).click();
            driver.findElement(By.xpath("//span[contains(text(), '" + domain + "')]/ancestor::li")).click();
        }

        driver.findElement(By.id("Username")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);

        WebElement checkbox = driver.findElement(REMEBER_CHECKBOX);

        if(remember) {
            checkbox.click();
        }

        driver.findElement(By.id("SubmitButton")).click();
    }
}
