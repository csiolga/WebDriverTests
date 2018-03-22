package Task80.strategy;

import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotStrategy implements SelectFileStrategy {
    public void selectFile(WebElement element, String path, String fileName) {
        element.click();

        StringSelection filePath = new StringSelection(path + fileName);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        try {
            Thread.sleep(500);

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
