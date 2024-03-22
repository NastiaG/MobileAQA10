import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PageApiDemosViewCount {
    private AppiumDriver driver;
    private WebDriverWait wait;
    private Swiper swiper;

    public PageApiDemosViewCount(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        swiper = new Swiper(driver);
    }

    private static class LocatorsViewCount {
        private static final By views = MobileBy.AccessibilityId("Views");
        private static final By webView3 = MobileBy.AccessibilityId("WebView3");
        private static final By clickableElement = MobileBy.xpath("//android.widget.TextView[@clickable=\"true\"]");
    }

    public void clickOnViewButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsViewCount.views)).click();
    }

    private void listOfElementsToSet(Set<String> setOfElements) {
        List<AndroidElement> listOfButtons = new ArrayList<>(driver.findElements(LocatorsViewCount.clickableElement));
        for (AndroidElement currentElementOfList : listOfButtons) {
            setOfElements.add(currentElementOfList.getText());
        }
    }

    public int countOfButtons() {
        Set<String> setOfButtons = new HashSet<>();
        listOfElementsToSet(setOfButtons);

        while (driver.findElements(LocatorsViewCount.webView3).isEmpty()) {
            swiper.swipe(Swiper.Directions.UP);
            listOfElementsToSet(setOfButtons);
        }
        return setOfButtons.size();
    }
}