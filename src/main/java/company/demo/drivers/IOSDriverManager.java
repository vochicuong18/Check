package company.demo.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class IOSDriverManager {
    public static AppiumDriver getDriver(DesiredCapabilities capabilities) {
        try {
            URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
            File file = new File(System.getProperty("user.dir") + File.separator + "environment.json");
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject env = new JSONObject(content);
            JSONObject iosConfig = env.getJSONObject("ios");
            capabilities.setCapability("platformName", iosConfig.get("platformName"));
            capabilities.setCapability("appium:deviceName", iosConfig.get("deviceName"));
            capabilities.setCapability("appium:bundleId", iosConfig.get("bundleID"));
            capabilities.setCapability("appium:udid", iosConfig.get("udid"));
            capabilities.setCapability("appium:automationName", iosConfig.get("automationName"));
            capabilities.setCapability("appium:platformVersion", iosConfig.get("platformVersion"));
            return new IOSDriver(appiumUrl, capabilities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
