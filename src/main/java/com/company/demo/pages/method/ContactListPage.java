package com.company.demo.pages.method;

import com.company.demo.components.Label;
import com.company.demo.pages.locator.ContactListLocator;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ContactListPage extends ContactListLocator {

    public ContactListPage(AppiumDriver driver) {
        super(driver);
    }

    public void skipSync(){
        Label label = new Label(driver,SKIP_SYNC);
        if (label.isDisplay()){
            label.click();
        }
    }

    public CreateContactPage addContact(){
        getWaitUtility().waitUntilToBeClickAble(weAddContact);
        weAddContact.click();
        return new CreateContactPage(driver);
    }

    public ContactDetailsPage goToContactDetails(String contactName){
        Label label = new Label(driver,String.format(CONTACT_NAME, contactName));
        label.waitUntilToBeClickAble();
        label.click();
        return new ContactDetailsPage(driver);
    }

    public List<String> getContactList() {
        return weContacts.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    
    public boolean isContactExist(String contactName){
        return getContactList().contains(contactName);
    }
}
