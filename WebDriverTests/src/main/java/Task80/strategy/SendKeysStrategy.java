package Task80.strategy;

import org.openqa.selenium.WebElement;

public class SendKeysStrategy implements SelectFileStrategy {
    public void selectFile(WebElement element, String path, String fileName) {
        element.sendKeys(path + fileName);
    }
}
