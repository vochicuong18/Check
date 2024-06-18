package com.company.demo.ultis;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AssertUtility {
      /*
        capture image after check with condition
        condition = all - always capture after check
        condition = false - only capture if check fail
     */

    public enum CAPTURE_SCREENSHOT {ALL, FAILED}

    private static int countImage = 1;
    private static AShot aShot;

    public static void assertTrue(boolean condition, String description) {
        if (condition)
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else {
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
        }
    }

    public static void assertTrue(boolean condition, String description, CAPTURE_SCREENSHOT option, WebDriver driver) {
        boolean status = true;
        if (condition)
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else {
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
            status = false;
        }
        addScreenShotToReport(status, option, description.replaceAll(" ", ""), driver);
    }

    public static void assertFalse(boolean condition, String description) {
        if (!condition)
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
    }

    public static void assertFalse(boolean condition, String description, CAPTURE_SCREENSHOT option, WebDriver driver) {
        boolean status = true;
        if (!condition)
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else {
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
            status = false;
        }
        addScreenShotToReport(status, option, description.replaceAll(" ", ""), driver);
    }

    public static void assertEquals(String actual, String expected, String description, CAPTURE_SCREENSHOT option, WebDriver driver) {
        boolean status = true;
        ReportUtility.getInstance().logInfo("Actual - " + actual);
        ReportUtility.getInstance().logInfo("Expected - " + expected);
        if (actual.equals(expected))
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else {
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
            status = false;
        }
        addScreenShotToReport(status, option, description.replaceAll(" ", ""), driver);
    }

    public static void assertEquals(String actual, String expected, String description) {
        ReportUtility.getInstance().logInfo("Actual - " + actual);
        ReportUtility.getInstance().logInfo("Expected - " + expected);
        if (actual.equals(expected))
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
    }

    public static void assertNotEquals(String actual, String expected, String description) {
        ReportUtility.getInstance().logInfo("Actual - " + actual);
        ReportUtility.getInstance().logInfo("Expected - " + expected);
        if (!actual.equals(expected))
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
    }


    public static void assertIndexOf(String actual, String expected, String description) {
        ReportUtility.getInstance().logInfo("Actual - " + actual);
        ReportUtility.getInstance().logInfo("Expected - " + expected);
        if (expected.indexOf(actual) != -1)
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
    }

    public static void assertContains(String actual, String expected, String description) {
        ReportUtility.getInstance().logInfo("Actual - " + actual);
        ReportUtility.getInstance().logInfo("Expected - " + expected);
        if (actual.contains(expected))
            ReportUtility.getInstance().logPass("<font color=\"blue\">" + description + "</front>");
        else
            ReportUtility.getInstance().logFail("<font color=\"red\">" + description + "</front>");
    }

    /*
            Capture full screen
     */

    private static void addScreenShotToReport(boolean result, CAPTURE_SCREENSHOT option, String name, WebDriver driver) {
        if ((!result) && (option == CAPTURE_SCREENSHOT.FAILED)) {
            getAPartFullScreenshot(name + countImage++, driver);
        } else if (option == CAPTURE_SCREENSHOT.ALL)
            getAPartFullScreenshot(name + countImage++, driver);
    }

    private static void cropImage(String path, int x, int y, int width, int height, CaptureImage.PICTURE_TYPE type) {
        try {
            BufferedImage fullImage = ImageIO.read(new File(path));
            if (width == 0)
                width = fullImage.getWidth();
            if (fullImage.getHeight() > height) {
                BufferedImage subImage = fullImage.getSubimage(x, y, width, height);
                ImageIO.write(subImage, type.toString(), new File(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAPartFullScreenshot(String name, WebDriver driver) {
        if (aShot == null)
            aShot = new AShot();
        String screenshotPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "report"
                + File.separator + "log" + File.separator + name + ".png";
        try {
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath));
            cropImage(screenshotPath, 0, 0, 0, 1500, CaptureImage.PICTURE_TYPE.PNG);
            ReportUtility.getInstance().addScreenCapture(Status.INFO, "log" + File.separator + name + ".png");
        } catch (IOException e) {
            return;
        }
    }

    private static void getScreenshotFullPage(String name, WebDriver driver) {
        if (aShot == null)
            aShot = new AShot();
        String screenshotPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "report"
                + File.separator + "log" + File.separator + name + ".png";
        try {
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath));
            cropImage(screenshotPath, 0, 0, 0, 1500, CaptureImage.PICTURE_TYPE.PNG);
            ReportUtility.getInstance().addScreenCapture(Status.INFO, "log" + File.separator + name + ".png");
        } catch (IOException e) {
            return;
        }
    }
}
