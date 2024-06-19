package com.company.demo.pages.method.android;

import com.company.demo.pages.locator.android.CreateContactLocator;
import io.appium.java_client.AppiumDriver;

public class CreateContactScreen extends CreateContactLocator {
    public CreateContactScreen(AppiumDriver driver) {
        super(driver);
    }

    public void fillName(String name){
        getWaitUtility().waitUntilToBeClickAble(weName);
        weName.clear();
        weName.sendKeys(name);
    }

    public void fillPhoneNumber(String phoneNumber){
        getWaitUtility().waitUntilToBeClickAble(wePhoneNumber);
        wePhoneNumber.clear();
        wePhoneNumber.sendKeys(phoneNumber);
    }

    public ContactDetailsScreen saveContact(){
        getWaitUtility().waitUntilToBeClickAble(weSave);
        weSave.click();
        return new ContactDetailsScreen(driver);
    }

    public boolean isWarningPopupDisplayed(){
        getWaitUtility().waitUntilToBeClickAble(weWarningPopup);
        return weWarningPopup.isDisplayed();
    }

    public void closeWarningPopup(){
        getWaitUtility().waitUntilToBeClickAble(weCloseWarningPopup);
        weCloseWarningPopup.click();
    }
}
