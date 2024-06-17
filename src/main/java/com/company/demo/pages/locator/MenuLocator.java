package com.company.demo.pages.locator;

import com.company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MenuLocator extends AppiumFactory {
    @FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@resource-id=\"com.sec.android.app.launcher:id/iconview_imageView\"])[6]")
    protected WebElement weContact;


    public MenuLocator(AppiumDriver driver) {
        super(driver);
    }

}
