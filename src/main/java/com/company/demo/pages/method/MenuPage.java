package com.company.demo.pages.method;

import com.company.demo.pages.locator.MenuLocator;
import io.appium.java_client.AppiumDriver;

public class MenuPage extends MenuLocator {

    public MenuPage(AppiumDriver driver) {
        super(driver);
    }

    public ContactListPage goToContactApp(){
        getWaitUtility().waitUntilToBeClickAble(weContact);
        weContact.click();
        return new ContactListPage(driver);
    }
}
