package com.company.demo.pages.locator;

import com.company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ContactDetailsLocator extends AppiumFactory {
    @FindBy(how = How.ID, using = "com.google.android.contacts:id/large_title")
    protected WebElement weName;
    @FindBy(how = How.ID, using = "com.google.android.contacts:id/header")
    protected WebElement wePhoneNumber;
    @FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc=\"More options\"]")
    protected WebElement weMoreOptions;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@resource-id=\"com.google.android.contacts:id/title\" and @text=\"Delete\"]")
    protected WebElement weDelete;
    @FindBy(how = How.ID, using = "android:id/button1")
    protected WebElement weConfirmDelete;
    @FindBy(how = How.ID, using = "com.google.android.contacts:id/menu_insert_or_edit")
    protected WebElement weEditContact;

    public ContactDetailsLocator(AppiumDriver driver) {
        super(driver);
    }
}
