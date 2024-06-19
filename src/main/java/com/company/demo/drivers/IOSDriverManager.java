package com.company.demo.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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
            capabilities.setCapability("appium:appPackage", iosConfig.get("appPackage"));
            capabilities.setCapability("appium:appActivity", iosConfig.get("appActivity"));
            return new AndroidDriver(appiumUrl, capabilities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
