package Task80.strategy;

import org.openqa.selenium.WebElement;

public class Context {
    private SelectFileStrategy strategy;

    public Context(SelectFileStrategy strategy) {
        this.strategy = strategy;
    }

    public void selectFile(WebElement element, String path, String fileName) throws Exception {
        strategy.selectFile(element, path, fileName);
    }
}
