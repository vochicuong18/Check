package company.demo.ultis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtility {
    private WebDriver driver;
    private Actions actions;

    public ActionUtility(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public Actions moveToElement(WebElement element) {
        return actions.moveToElement(element);
    }

    public void clickAndHold(WebElement element) {
        actions.clickAndHold(element).perform();
    }
}
