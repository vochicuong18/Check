package com.company.demo.pages.method.android;

import com.company.demo.pages.locator.android.ContactDetailsLocator;
import com.company.demo.ultis.ReportUtility;
import io.appium.java_client.AppiumDriver;

public class ContactDetailsScreen extends ContactDetailsLocator {
    public ContactDetailsScreen(AppiumDriver driver) {
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

    public ContactListScreen deleteContact(){
        getWaitUtility().waitUntilToBeClickAble(weMoreOptions);
        weMoreOptions.click();
        getWaitUtility().waitUntilToBeClickAble(weDelete);
        weDelete.click();
        getWaitUtility().waitUntilToBeClickAble(weConfirmDelete);
        weConfirmDelete.click();
        return new ContactListScreen(driver);
    }

    public CreateContactScreen editContact(){
        getWaitUtility().waitUntilToBeClickAble(weEditContact);
        weEditContact.click();
        return new CreateContactScreen(driver);
    }

    public ContactListScreen backToContactList(){
        getWaitUtility().waitUntilToBeClickAble(weBack);
        weBack.click();
        return new ContactListScreen(driver);
    }
}
