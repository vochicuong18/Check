package company.demo.pages.locator.android;

import company.demo.drivers.AppiumFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomeLocator extends AppiumFactory {
    @FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@resource-id=\"com.sec.android.app.launcher:id/iconview_imageView\"])[2]")
    protected WebElement weMenu;

    public HomeLocator(AppiumDriver driver) {
        super(driver);
    }
}
