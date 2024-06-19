package com.company.demo.pages.method.ios;

import com.company.demo.pages.locator.ios.IOSNewContactLocator;
import io.appium.java_client.AppiumDriver;

public class IOSNewContactScreen extends IOSNewContactLocator {
    public IOSNewContactScreen(AppiumDriver driver) {
        super(driver);
    }

    public void fillFirstName(String firstName){
        getWaitUtility().waitUntilToBeClickAble(weFirstName);
        weFirstName.clear();
        weFirstName.sendKeys(firstName);
    }

    public void addPhoneNumber(){
        getWaitUtility().waitUntilToBeClickAble(weAddPhone);
        weAddPhone.click();
    }

    public void fillPhoneNumber(String phoneNumber){
        getWaitUtility().waitUntilToBeClickAble(wePhoneNumber);
        wePhoneNumber.clear();
        wePhoneNumber.sendKeys(phoneNumber);
    }

    public IOSContactDetailsScreen saveContact(){
        getWaitUtility().waitUntilToBeClickAble(weSave);
        weSave.click();
        return new IOSContactDetailsScreen(driver);
    }
}
