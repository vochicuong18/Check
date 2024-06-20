package company.demo.drivers;

import company.demo.ultis.ActionUtility;
import company.demo.ultis.WaitUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class AppiumFactory {
    public static Map<AppiumDriver, WaitUtility> waitUtility = new HashMap<>();
    public static Map<AppiumDriver, ActionUtility> actionUtility = new HashMap<>();
    public AppiumDriver driver;

    public AppiumFactory(AppiumDriver driver) {
        this.driver = driver;
        initComponents();
    }

    private void initComponents() {
        if (waitUtility.get(driver) == null) {
            waitUtility.put(driver, new WaitUtility(driver));
        }
        if (actionUtility.get(driver) == null) {
            actionUtility.put(driver, new ActionUtility(driver));
        }
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WaitUtility getWaitUtility() {
        return waitUtility.get(driver);
    }

    public ActionUtility getActionUtility() {
        return actionUtility.get(driver);
    }
}
