package company.demo.pages.locator.ios;

import company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class IOSContactListLocator extends AppiumFactory {

    @FindBy(how = How.ID, using = "Add")
    protected WebElement weAddContact;

    public IOSContactListLocator(AppiumDriver driver) {
        super(driver);
    }
}
