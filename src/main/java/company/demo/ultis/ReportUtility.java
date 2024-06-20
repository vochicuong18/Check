package company.demo.ultis;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ReportUtility {

    private ExtentReports report;
    private ExtentTest logger;
    private static ThreadLocal<ReportUtility> ourInstance = new ThreadLocal<>();
    private final static String USER_DIR = System.getProperty("user.dir");

    public static void init(String suiteName) {
        ReportUtility localOurInstance = new ReportUtility(suiteName);
        ourInstance.set(localOurInstance);
    }

    public static ReportUtility getInstance() {
        return ourInstance.get();
    }

    private ReportUtility(String suiteName) {
        String reportName = suiteName + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(USER_DIR + File.separator + "test-output" + File.separator + "report" + File.separator + reportName);
        sparkReporter.config().setReportName("Appium Demo");
        sparkReporter.config().setTheme(Theme.STANDARD);
        report = new ExtentReports();
        report.attachReporter(sparkReporter);
    }

    public void log(Status status, String content) {
        logger.log(status, encodingContent(content));
    }

    public void logInfo(String content) {
        logger.log(Status.INFO, encodingContent(content));
    }

    public void logPass(String content) {
        logger.log(Status.PASS, encodingContent(content));
    }

    public void logFail(String content) {
        logger.log(Status.FAIL, encodingContent(content));
    }

    public void addScreenCapture(Status status, String path) {
        logger.addScreenCaptureFromBase64String(path);
    }

    public void addScreenCapture(Status status, String path1, String path2) {
        logger.log(status, "<td>" + logger.addScreenCaptureFromPath(path1) + "</td><td>" + logger.addScreenCaptureFromPath(path2) + "</td>");
    }

    public void addScreenCapture(Status status, String path1, String path2, String path3) {
        logger.log(status, "<td>" + logger.addScreenCaptureFromPath(path1) + "</td><td>" + logger.addScreenCaptureFromPath(path2) + "</td><td>" + logger.addScreenCaptureFromPath(path3) + "</td>");
    }

    public void startTest(String testName) {
        logger = report.createTest(testName);
    }

    public void flush() {
        report.flush();
    }

    private String encodingContent(String content) {
        byte[] ptext = content.getBytes(StandardCharsets.UTF_8);
        return new String(ptext);
    }


}
