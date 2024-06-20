package company.demo.base;

import com.aventstack.extentreports.Status;
import company.demo.drivers.AndroidDriverManager;
import company.demo.drivers.IOSDriverManager;
import company.demo.pages.method.android.ContactListScreen;
import company.demo.pages.method.ios.IOSContactListScreen;
import company.demo.ultis.DataTest;
import company.demo.ultis.ReportUtility;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private static final ThreadLocal<ContactListScreen> androidContactListScreen = new ThreadLocal<>();
    private static final ThreadLocal<IOSContactListScreen> iOSContactListScreen = new ThreadLocal<>();
    private static final ThreadLocal<String> className = new ThreadLocal<>();
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private final static String USER_DIR = System.getProperty("user.dir");
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public synchronized static AppiumDriver getDriver() {
        return driver.get();
    }

    public synchronized static ContactListScreen getAndroidContactListScreen() {
        return androidContactListScreen.get();
    }

    public synchronized static IOSContactListScreen getIOSContactListPage() {
        return iOSContactListScreen.get();
    }

    @BeforeSuite
    public void setSuite(ITestContext ctx) {
        ReportUtility.init(ctx.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeTest
    @Parameters("platformName")
    public void setUp(@Optional("android") String platform) {
        initMobile(platform);
        DataTest.init();
    }

    @BeforeClass
    public void beforeClass() {
        preCondition();
        className.set(getClass().getSimpleName());
        ReportUtility.getInstance().startTest(className.get());
        getAndroidContactListScreen().skipSync();
    }

    @BeforeMethod
    public void setUpBeforeMethod(Method method) {
        ReportUtility.getInstance().log(Status.INFO, "<b>Start method: " + method.getName() + "</b>");
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        String methodName = method.getName();
        if (result.getStatus() == ITestResult.FAILURE) {
            ReportUtility.getInstance().log(Status.FAIL, "Test case: " + methodName + " failed");
            ReportUtility.getInstance().log(Status.FAIL, result.getThrowable().toString());
            captureScreenshot(methodName);
        } else if (result.getStatus() == ITestResult.SKIP) {
            ReportUtility.getInstance().log(Status.SKIP, "Test case: " + methodName + " skipped");
        } else {
            ReportUtility.getInstance().log(Status.PASS, "Test case: " + methodName + " passed");
        }
    }

    @AfterClass
    public void afterClass() throws TimeoutException {
        ReportUtility.getInstance().flush();
    }

    @AfterTest
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    private void initMobile(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            driver.set(AndroidDriverManager.getDriver(capabilities));
            androidContactListScreen.set(new ContactListScreen(getDriver()));
        } else if (platform.equalsIgnoreCase("ios")) {
            driver.set(IOSDriverManager.getDriver(capabilities));
            iOSContactListScreen.set(new IOSContactListScreen(getDriver()));

        }
    }

    protected void preCondition() {
        // Implement pre-condition
    }

    private void captureScreenshot(String methodName) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = methodName + "_" + timestamp + ".png";
        String screenshotPath = USER_DIR + File.separator + "test-output" + File.separator + "report" + File.separator + "screenshots" + File.separator + fileName;
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(screenshotPath));
        ReportUtility.getInstance().addScreenCapture(Status.FAIL, screenshotPath);
    }
}
