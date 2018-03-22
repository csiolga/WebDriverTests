package Task80;

import Task80.driver.Driver;
import Task80.pageobject.DownloadPage;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DownloadFile {
    private static final String URL = "https://the-internet.herokuapp.com/download";
    private static final String PATH = "C:\\test\\";
    private WebDriver driver;
    private Path filePath;
    private DownloadPage downloadPage;

    @BeforeMethod
    public void startUp() throws Exception {
        driver = Driver.getInstance().open(URL);
        downloadPage = new DownloadPage();
        filePath = Paths.get(PATH + downloadPage.getFileName());
        if(Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void downloadFile() throws Exception {
        downloadPage.downloadFile();

        assertTrue(Files.exists(filePath), "File '" + downloadPage.getFileName() + "' doesn't exist");
    }
}