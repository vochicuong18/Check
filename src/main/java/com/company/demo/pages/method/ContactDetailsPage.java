package com.company.demo.pages.method;

import com.company.demo.pages.locator.ContactDetailsLocator;
import com.company.demo.ultis.ReportUtility;
import io.appium.java_client.AppiumDriver;

public class ContactDetailsPage extends ContactDetailsLocator {
    public ContactDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean checkContactName(String name) {
        String actual = weName.getText();
        ReportUtility.getInstance().logInfo("Check contact name Actual: " + actual + "| Expected: " + name);
        return actual.equals(name);
    }

    public boolean checkContactPhoneNumber(String phoneNumber) {
        String actual = wePhoneNumber.getText().replaceAll("\\D", "");
        ReportUtility.getInstance().logInfo("Check contact phone number Actual: " + actual + "| Expected: " + phoneNumber);
        return actual.equals(phoneNumber);
    }

    public ContactListPage deleteContact(){
        getWaitUtility().waitUntilToBeClickAble(weMoreOptions);
        weMoreOptions.click();
        getWaitUtility().waitUntilToBeClickAble(weDelete);
        weDelete.click();
        getWaitUtility().waitUntilToBeClickAble(weConfirmDelete);
        weConfirmDelete.click();
        return new ContactListPage(driver);
    }

    public CreateContactPage editContact(){
        getWaitUtility().waitUntilToBeClickAble(weEditContact);
        weEditContact.click();
        return new CreateContactPage(driver);
    }

    public ContactListPage backToContactList(){
        getWaitUtility().waitUntilToBeClickAble(weBack);
        weBack.click();
        return new ContactListPage(driver);
    }
}
