package Task80.pageobject;

import Task80.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {
    private static final By UPLOADED_FILE = By.id("uploaded-files");

    private WebDriver driver = Driver.getInstance().getDriver();

    public ResultPage() {}

    public String getUploadedFileName() {
        return driver.findElement(UPLOADED_FILE).getText();
    }
}
