package com.company.demo.base;

import com.aventstack.extentreports.Status;
import com.company.demo.drivers.DriverFactory;
import com.company.demo.pages.method.HomePage;
import com.company.demo.ultis.ReportUtility;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.StandardSystemProperty.USER_DIR;

public class BaseTest {
    private static Map<AppiumDriver, HomePage> homePage = new HashMap<>();
    private static Map<AppiumDriver, String> className = new HashMap<>();
    private static Map<AppiumDriver, Integer> localCount = new HashMap<>();
    private static Map<AppiumDriver, Boolean> statusResult = new HashMap<>();
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public synchronized static AppiumDriver getDriver() {
        return driver.get();
    }

    public synchronized static HomePage getHomePage() {
        return homePage.get(getDriver());
    }

    @BeforeSuite
    public void setSuite(ITestContext ctx) {
        ReportUtility.init(ctx.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeTest
    public void setUp() {
        initMobile();
    }

    @BeforeClass
    public void beforeClass() {
        preCondition();
        className.put(getDriver(), getClass().getSimpleName());
        localCount.put(getDriver(), 0);
        ReportUtility.getInstance().startTest(className.get(getDriver()));
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
                getscreenshot();
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
            getDriver().quit();
        }
    }

    private void initMobile() {
        driver.set(DriverFactory.getDriver("Android", "e8a82aab"));
        homePage.put(getDriver(), new HomePage(getDriver()));
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

    private void getscreenshot() throws Exception {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        localCount.put(getDriver(), localCount.get(getDriver()) + 1);
        String screenshotPath = USER_DIR + File.separator + "test-output" + File.separator + "report"
                + File.separator + "log" + File.separator + "test"+".png";
        FileUtils.copyFile(scrFile, new File(screenshotPath));
    }
}
