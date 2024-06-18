package com.company.demo.pages.locator;

import com.company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateContactLocator extends AppiumFactory {
    @FindBy(how = How.XPATH, using = "((//android.widget.LinearLayout[@resource-id=\"com.google.android.contacts:id/editors\"])[1]//android.widget.EditText)[1]")
    protected WebElement weName;
    @FindBy(how = How.XPATH, using = "((//android.widget.LinearLayout[@resource-id=\"com.google.android.contacts:id/editors\"])[3]//android.widget.EditText)[1]")
    protected WebElement wePhoneNumber;
    @FindBy(how = How.ID, using = "com.google.android.contacts:id/toolbar_button")
    protected WebElement weSave;
    @FindBy(how = How.ID, using = "com.google.android.contacts:id/parentPanel")
    protected WebElement weWarningPopup;
    @FindBy(how = How.ID, using = "android:id/button1")
    protected WebElement weCloseWarningPopup;

    public CreateContactLocator(AppiumDriver driver) {
        super(driver);
    }
}
