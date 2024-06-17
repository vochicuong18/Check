package com.company.demo.pages.locator;

import com.company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateContactLocator extends AppiumFactory {
    @FindBy(how = How.XPATH, using = "//android.widget.EditText[@text=\"Tên\"]")
    protected WebElement weName;
    @FindBy(how = How.XPATH, using = "//android.widget.EditText[@resource-id=\"com.samsung.android.contacts:id/kind_title_edit\" and @text=\"Điện thoại\"]")
    protected WebElement wePhoneNumber;
    @FindBy(how = How.ID, using = "com.samsung.android.contacts:id/menu_done")
    protected WebElement weSave;
    public CreateContactLocator(AppiumDriver driver) {
        super(driver);
    }
}
