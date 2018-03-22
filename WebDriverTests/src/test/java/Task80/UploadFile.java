package Task80;

import Task80.driver.Driver;
import Task80.pageobject.UploadPage;
import Task80.pageobject.ResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UploadFile {
    private static final String URL = "https://the-internet.herokuapp.com/upload";
    private static final String PATH = "C:\\";
    private static final String FILE_NAME = "testfile.txt";

    private WebDriver driver;
    private UploadPage uploadPage;
    private ResultPage resultPage;
    private String uploadedFileName;

    @BeforeMethod
    public void openStartPage() {
        driver = Driver.getInstance().open(URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void uploadFileWithSendKeys() throws Exception {
        uploadPage = new UploadPage();
        resultPage = uploadPage.uploadFileWithSendKeys(PATH, FILE_NAME);
        uploadedFileName = resultPage.getUploadedFileName();

        Assert.assertEquals(FILE_NAME, uploadedFileName, "Expected file name is '" + FILE_NAME + "' but actual file name is '" + uploadedFileName + "'");
    }

    @Test
    public void uploadFileWithRobot() throws Exception {
        uploadPage = new UploadPage();
        resultPage = uploadPage.uploadFileWithRobot(PATH, FILE_NAME);
        uploadedFileName = resultPage.getUploadedFileName();

        Assert.assertEquals(FILE_NAME, uploadedFileName, "Expected file name is '" + FILE_NAME + "' but actual file name is '" + uploadedFileName + "'");
    }
}