package Task80.strategy;

import org.openqa.selenium.WebElement;

public interface SelectFileStrategy {
    public void selectFile(WebElement element, String path, String fileName);
}
