package com.company.demo.pages.locator;

import com.company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ContactListLocator extends AppiumFactory {
    @FindBy(how = How.ID, using = "com.samsung.android.contacts:id/floating_action_button")
    protected WebElement weAddContact;

    public ContactListLocator(AppiumDriver driver) {
        super(driver);
    }
}
