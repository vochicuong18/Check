package com.company.demo.pages.method;

import com.company.demo.pages.locator.CreateContactLocator;
import io.appium.java_client.AppiumDriver;

public class CreateContactPage extends CreateContactLocator {
    public CreateContactPage(AppiumDriver driver) {
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

    public ContactDetailsPage saveContact(){
        getWaitUtility().waitUntilToBeClickAble(weSave);
        weSave.click();
        return new ContactDetailsPage(driver);
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
