package Task30;

import org.openqa.selenium.By;

public class ByVariables {
    By byId = By.id("someId");
    By byClassName = By.className("someClassName");
    By byTagName = By.tagName("div");
    By byName = By.name("value");
    By byLinkText = By.linkText("some text");
    By byPartialLinkText = By.partialLinkText("text");
    By byCss = By.cssSelector("#someId .someClassName");
    By byXpath = By.xpath("//input[@id='someId']");
}
