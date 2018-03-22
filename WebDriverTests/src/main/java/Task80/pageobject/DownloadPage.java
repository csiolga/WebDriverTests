package Task80.pageobject;

import Task80.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DownloadPage {
    private static final By FILE_LINK = By.xpath("//div[@class='example']/a[1]");
    private WebDriver driver = Driver.getInstance().getDriver();

    public DownloadPage() {}

    private WebElement getFile() {
        return driver.findElement(FILE_LINK);
    }

    public String getFileName() {
        return getFile().getText();
    }

    public void downloadFile() throws Exception{
        getFile().click();
        Thread.sleep(500);
    }
}
