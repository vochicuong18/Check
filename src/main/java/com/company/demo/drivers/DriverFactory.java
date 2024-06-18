package com.company.demo.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static AppiumDriver getDriver(String platformName, String deviceName, String appPackage, String appActivity) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:appPackage", appPackage);
        capabilities.setCapability("appium:appActivity", appActivity);
        URL appiumUrl = null;
        try {
            appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        switch (platformName) {
            case "Android":
                return new AndroidDriver(appiumUrl, capabilities);
            case "iOS":
                return new IOSDriver(appiumUrl, capabilities);
            default:
                throw new IllegalArgumentException("Platform name not supported: " + platformName);
        }
    }
}
