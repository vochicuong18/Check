package com.company.demo.base;

import com.aventstack.extentreports.Status;
import com.company.demo.drivers.DriverFactory;
import com.company.demo.pages.method.ContactListPage;
import com.company.demo.pages.method.HomePage;
import com.company.demo.ultis.DataTest;
import com.company.demo.ultis.ReportUtility;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.StandardSystemProperty.USER_DIR;

public class BaseTest {
    private static final Map<AppiumDriver, ContactListPage> contactListPage = new HashMap<>();
    private static final Map<AppiumDriver, String> className = new HashMap<>();
    private static final Map<AppiumDriver, Integer> localCount = new HashMap<>();
    private static final Map<AppiumDriver, Boolean> statusResult = new HashMap<>();
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public synchronized static AppiumDriver getDriver() {
        return driver.get();
    }

    public synchronized static ContactListPage getContactListPage() {
        return contactListPage.get(getDriver());
    }

    @BeforeSuite
    public void setSuite(ITestContext ctx) {
        ReportUtility.init(ctx.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeTest
    public void setUp() {
        initMobile();
        DataTest.init();
    }

    @BeforeClass
    public void beforeClass() {
        preCondition();
        className.put(getDriver(), getClass().getSimpleName());
        localCount.put(getDriver(), 0);
        ReportUtility.getInstance().startTest(className.get(getDriver()));
        getContactListPage().skipSync();
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
            getDriver().quit();
        }
    }

    private void initMobile() {
        driver.set(DriverFactory.getDriver("Android", "e8a82aab", "com.google.android.contacts", "com.android.contacts.activities.PeopleActivity"));
        contactListPage.put(getDriver(), new ContactListPage(getDriver()));
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
        localCount.put(getDriver(), localCount.get(getDriver()) + 1);
        String screenshotPath = "test-output" + File.separator + "report"
                + File.separator + "image" + File.separator + className.get(getDriver()) +".png";
        FileUtils.copyFile(scrFile, new File(screenshotPath));
    }
}
