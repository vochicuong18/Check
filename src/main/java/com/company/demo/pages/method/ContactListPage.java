package com.company.demo.pages.method;

import com.company.demo.pages.locator.ContactListLocator;
import io.appium.java_client.AppiumDriver;

public class ContactListPage extends ContactListLocator {

    public ContactListPage(AppiumDriver driver) {
        super(driver);
    }

    public CreateContactPage addContact(){
        getWaitUtility().waitUntilToBeClickAble(weAddContact);
        weAddContact.click();
        return new CreateContactPage(driver);
    }

}
