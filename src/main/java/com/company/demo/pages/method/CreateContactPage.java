package com.company.demo.pages.method;

import com.company.demo.pages.locator.CreateContactLocator;
import io.appium.java_client.AppiumDriver;

public class CreateContactPage extends CreateContactLocator {
    public CreateContactPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillName(String name){
        getWaitUtility().waitUntilToBeClickAble(weName);
        weName.sendKeys(name);
    }

    public void fillPhoneNumber(String phoneNumber){
        getWaitUtility().waitUntilToBeClickAble(wePhoneNumber);
        wePhoneNumber.sendKeys(phoneNumber);
    }

    public void saveContact(){
        getWaitUtility().waitUntilToBeClickAble(weSave);
        weSave.click();
    }
}
