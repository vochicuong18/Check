package company.demo.pages.locator.ios;

import company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class IOSNewContactLocator extends AppiumFactory {

    @FindBy(how = How.ID, using = "First name")
    protected WebElement weFirstName;
    @FindBy(how = How.XPATH, using = "//XCUIElementTypeStaticText[@name=\"add phone\"]")
    protected WebElement weAddPhone;
    @FindBy(how = How.XPATH, using = "//XCUIElementTypeTable/XCUIElementTypeCell[4]")
    protected WebElement wePhoneNumber;
    @FindBy(how = How.XPATH, using = "//XCUIElementTypeButton[@name=\"Done\"]")
    protected WebElement weSave;


    public IOSNewContactLocator(AppiumDriver driver) {
        super(driver);
    }


}
