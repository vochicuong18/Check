package com.company.demo.pages.method;

import com.company.demo.pages.locator.HomeLocator;
import io.appium.java_client.AppiumDriver;

public class HomePage extends HomeLocator {

    public MenuPage goToMenuPage (){
        getWaitUtility().waitUntilToBeClickAble(weMenu);
        weMenu.click();
        return new MenuPage(driver);
    }

    public HomePage(AppiumDriver driver) {
        super(driver);
    }
}
