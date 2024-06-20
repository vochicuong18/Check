package company.demo.ultis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {
    private static final int TIMEOUT_INTERVAL_UNIT = 30;
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtility(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_INTERVAL_UNIT));
    }

    public WebElement waitForInvisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilToBeClickAble(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForPresentOf(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
