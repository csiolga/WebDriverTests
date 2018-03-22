package Task80.pageobject;

import Task80.driver.Driver;
import Task80.strategy.Context;
import Task80.strategy.RobotStrategy;
import Task80.strategy.SendKeysStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UploadPage {
    private static final By UPLOAD_FILE_INPUT = By.id("file-upload");
    private static final By UPLOAD_FILE_BUTTON = By.id("file-submit");

    private Context context;
    private WebDriver driver = Driver.getInstance().getDriver();
    private WebElement uploadFileInput = driver.findElement(UPLOAD_FILE_INPUT);

    public UploadPage() {}

    public void clickUploadButton() {
        driver.findElement(UPLOAD_FILE_BUTTON).click();
    }

    public ResultPage uploadFileWithSendKeys(String path, String filName) throws Exception{
        context = new Context(new SendKeysStrategy());
        context.selectFile(uploadFileInput, path, filName);
        clickUploadButton();

        return new ResultPage();
    }

    public ResultPage uploadFileWithRobot(String path, String filName) throws Exception {
        context = new Context(new RobotStrategy());
        context.selectFile(uploadFileInput, path, filName);
        Thread.sleep(500);
        clickUploadButton();
        return new ResultPage();
    }
}
