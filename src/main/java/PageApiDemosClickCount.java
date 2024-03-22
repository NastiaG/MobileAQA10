import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PageApiDemosClickCount {
    private AppiumDriver driver;
    private WebDriverWait wait;
    private Swiper swiper;

    public PageApiDemosClickCount(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        swiper = new Swiper(driver);
    }

    private static class LocatorsClickCount {
        private static final By views = MobileBy.AccessibilityId("Views");
        private static final By clickableElement = MobileBy.xpath("//android.widget.TextView[@clickable=\"true\"]");
        private static final By textSwitcher = MobileBy.AccessibilityId("TextSwitcher");
        private static final By nextButton = MobileBy.AccessibilityId("Next");
        private static final By resultNumber = MobileBy.xpath("(//android.widget.TextView)[2]");
    }

    public void clickOnViewButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsClickCount.views)).click();
    }

    public void scrollToElement() {
        while (driver.findElements(LocatorsClickCount.textSwitcher).isEmpty()) {
            swiper.swipe(Swiper.Directions.UP);
        }
    }

    public void clickOnTextSwitcher() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsClickCount.textSwitcher)).click();
    }

    public void clickOnNextButton(int numberOfClicks) {
        for (int i = numberOfClicks; i > 0; i--) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsClickCount.nextButton)).click();
        }
    }

    public int getResultNumberOfClick() {
        AndroidElement resultElement = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsClickCount.resultNumber));
        return Integer.valueOf(resultElement.getText());
    }
}
