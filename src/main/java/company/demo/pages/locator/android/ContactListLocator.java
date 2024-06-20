package company.demo.pages.locator.android;

import company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ContactListLocator extends AppiumFactory {
    public static final String CONTACT_NAME = "xpath=//android.widget.TextView[@content-desc=\"%s\"]";
    public static final String SKIP_SYNC = "id=android:id/button2";

    @FindBy(how = How.ID, using = "com.google.android.contacts:id/floating_action_button")
    protected WebElement weAddContact;
    @FindBy(how = How.ID, using = "android:id/button2")
    protected WebElement weSkipSync;
    @FindBy(how = How.ID, using = "com.google.android.contacts:id/cliv_name_textview")
    protected List<WebElement> weContacts;
    public ContactListLocator(AppiumDriver driver) {
        super(driver);
    }
}
