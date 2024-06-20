package com.company.demo.base;

import com.aventstack.extentreports.Status;
import com.company.demo.drivers.AndroidDriverManager;
import com.company.demo.drivers.IOSDriverManager;
import com.company.demo.pages.method.android.ContactListScreen;
import com.company.demo.pages.method.ios.IOSContactListScreen;
import com.company.demo.ultis.DataTest;
import com.company.demo.ultis.ReportUtility;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private static final Map<AppiumDriver, ContactListScreen> contactListScreen = new HashMap<>();
    private static final Map<AppiumDriver, IOSContactListScreen> iOSContactListScreen = new HashMap<>();
    private static final Map<AppiumDriver, String> className = new HashMap<>();
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final Map<AppiumDriver, Boolean> statusResult = new HashMap<>();
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public synchronized static AppiumDriver getDriver() {
        return driver.get();
    }

    public synchronized static ContactListScreen getContactListScreen() {
        return contactListScreen.get(getDriver());
    }

    public synchronized static IOSContactListScreen getIOSContactListPage() {
        return iOSContactListScreen.get(getDriver());
    }

    @BeforeSuite
    public void setSuite(ITestContext ctx) {
        ReportUtility.init(ctx.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeTest
    @Parameters("platformName")
    public void setUp(@Optional("android") String platform) {
        DataTest.init();
        preCondition();
        initMobile(platform);
    }

    @BeforeClass
    public void beforeClass() {
//        preCondition();
        className.put(getDriver(), getClass().getSimpleName());
        ReportUtility.getInstance().startTest(className.get(getDriver()));
        getContactListScreen().skipSync();
    }

    @BeforeMethod
    public void setUpBeforeMethod(Method method) {
        ReportUtility.getInstance().log(Status.INFO, "<b>Start method: " + method.getName() + "</b>");
    }

    @AfterMethod
    public void cleanOrKeepTrack(ITestResult result, Method method) {
        if (result.getStatus() == ITestResult.FAILURE) {
            statusResult.put(getDriver(), false);
            try {
                ReportUtility.getInstance().log(Status.FAIL, result.getThrowable().toString());
                for (StackTraceElement trace : result.getThrowable().getStackTrace()) {
                    if (!ignoreTrackTrace(trace.toString()))
                        ReportUtility.getInstance().log(Status.FAIL, trace.toString());
                }
                getScreenShot();
                ReportUtility.getInstance().addScreenCapture(Status.FAIL, "log" + "Test" + ".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ReportUtility.getInstance().log(Status.PASS, "<i>End method: " + result.getMethod().getMethodName() + "</i>");
        }
    }

    @AfterClass
    public void afterClass() {
        ReportUtility.getInstance().flush();
    }

    @AfterTest
    public void tearDown() {
        if (getDriver() != null) {
            try {
                getDriver().quit();
            } catch (Exception e) {
                // In thông báo lỗi
                System.err.println("Error while quitting driver: " + e.getMessage());
                // Thực hiện các hành động xử lý lỗi khác (nếu cần)
            }
        }
    }

    private void initMobile(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            driver.set(AndroidDriverManager.getDriver(capabilities));
            contactListScreen.put(getDriver(), new ContactListScreen(getDriver()));

        } else if (platform.equalsIgnoreCase("ios")) {
            driver.set(IOSDriverManager.getDriver(capabilities));
            iOSContactListScreen.put(getDriver(), new IOSContactListScreen(getDriver()));

        }
    }

    protected void preCondition() {
    }

    private boolean ignoreTrackTrace(String log) {
        if (log.contains("org.testng")) {
            return true;
        } else if (log.contains("org.openqa.selenium.remote")) {
            return true;
        } else if (log.contains("sun.reflect")) {
            return true;
        } else if (log.contains("java.lang.reflect")) {
            return true;
        } else return log.contains("org.apache.maven.surefire");
    }

    private void getScreenShot() throws Exception {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "test-output" + File.separator + "report"
                + File.separator + "image" + File.separator + className.get(getDriver()) + ".png";
        FileUtils.copyFile(scrFile, new File(screenshotPath));
    }
}
