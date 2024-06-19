package com.company.demo.pages.method.ios;

import com.company.demo.pages.locator.ios.IOSContactListLocator;
import io.appium.java_client.AppiumDriver;

public class IOSContactListScreen extends IOSContactListLocator {
    public IOSContactListScreen(AppiumDriver driver) {
        super(driver);
    }

    public IOSNewContactScreen addContact(){
        getWaitUtility().waitUntilToBeClickAble(weAddContact);
        weAddContact.click();
        return new IOSNewContactScreen(driver);
    }
}
