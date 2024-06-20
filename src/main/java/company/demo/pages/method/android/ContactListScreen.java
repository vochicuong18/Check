package company.demo.pages.method.android;

import company.demo.components.Label;
import company.demo.pages.locator.android.ContactListLocator;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ContactListScreen extends ContactListLocator {

    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }

    public void skipSync(){
        Label label = new Label(driver,SKIP_SYNC);
        if (label.isDisplay()){
            label.click();
        }
    }

    public CreateContactScreen addContact(){
        getWaitUtility().waitUntilToBeClickAble(weAddContact);
        weAddContact.click();
        return new CreateContactScreen(driver);
    }

    public ContactDetailsScreen goToContactDetails(String contactName){
        Label label = new Label(driver,String.format(CONTACT_NAME, contactName));
        label.waitUntilToBeClickAble();
        label.click();
        return new ContactDetailsScreen(driver);
    }

    public List<String> getContactList() {
        return weContacts.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    
    public boolean isContactExist(String contactName){
        return getContactList().contains(contactName);
    }
}
