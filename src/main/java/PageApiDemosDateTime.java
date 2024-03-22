import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PageApiDemosDateTime {
    private AppiumDriver driver;
    private WebDriverWait wait;
    private Swiper swiper;

    public PageApiDemosDateTime(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        swiper = new Swiper(driver);
    }

    private static class LocatorsDateTime {
        private static final By views = MobileBy.AccessibilityId("Views");
        private static final By dateWidgets = MobileBy.AccessibilityId("Date Widgets");
        private static final By dialog = MobileBy.AccessibilityId("1. Dialog");
        private static final By changeDateButton = MobileBy.AccessibilityId("change the date");
        private static final By changeTimeSpinner = MobileBy.AccessibilityId("change the time (spinner)");
        private static final By firstDayOfMonth = MobileBy.xpath("//android.view.View[@text=\"1\"]");
        private static final By dayOfMonth = MobileBy.xpath("//android.view.View[@class=\"android.view.View\"]");
        private static final By okButton = MobileBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");
        private static final By nextMonthPointer = MobileBy.AccessibilityId("Next month");
        private static final By editHour = MobileBy.xpath("(//android.widget.EditText[@resource-id=\"android:id/numberpicker_input\"])[1]");
        private static final By editMinute = MobileBy.xpath("(//android.widget.EditText[@resource-id=\"android:id/numberpicker_input\"])[2]");
        private static final By editPartOfDay = MobileBy.xpath("(//android.widget.EditText[@resource-id=\"android:id/numberpicker_input\"])[3]");
        private static final By dateTimeCheck = MobileBy.xpath("//android.widget.TextView[@resource-id=\"io.appium.android.apis:id/dateDisplay\"]");
    }

    public void clickOnViewButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.views)).click();
    }

    public void clickOnDateWidgets() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.dateWidgets)).click();
    }

    public void clickOnDialog() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.dialog)).click();
    }

    public void clickOnChangeDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.changeDateButton)).click();
    }

    public void clickOnChangeTimeSpinner() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.changeTimeSpinner)).click();
    }

    public void clickOnOkButton() {
        driver.findElement(LocatorsDateTime.okButton).click();
    }


    public void setTomorrowDay() {
        LocalDate today = LocalDate.now();
        if (today.getMonthValue() != today.plusDays(1).getMonthValue()) {
            clickOnFirstDayOfNextMonth();
        } else {
            LocalDate tomorrow = today.plusDays(1);
            String tomorrowDay = String.valueOf(tomorrow.getDayOfMonth());
            clickOnDayOfThisMonth(tomorrowDay);
        }
    }

    public void clickOnDayOfThisMonth(String day) {
        List<AndroidElement> listOfDays = new ArrayList<>(driver.findElements(LocatorsDateTime.dayOfMonth));
        for (AndroidElement currentDay : listOfDays) {
            if (currentDay.getText().equals(day)) {
                currentDay.click();
                clickOnOkButton();
                break;
            }
        }
    }

    public void clickOnFirstDayOfNextMonth() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.nextMonthPointer)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.firstDayOfMonth)).click();
        clickOnOkButton();
    }

    public void setHour(String time) {
        AndroidElement element = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.editHour));
        while (!driver.findElement(LocatorsDateTime.editHour).getText().equals(time)) {
            swiper.scrollForSpinner(element);
        }
    }

    public void setMinute(String time) {
        AndroidElement element = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.editMinute));
        while (!driver.findElement(LocatorsDateTime.editMinute).getText().equals(time)) {
            swiper.scrollForSpinner(element);
        }
    }

    public void setPartOfDay(String time) {
        AndroidElement element = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.editPartOfDay));
        while (!driver.findElement(LocatorsDateTime.editPartOfDay).getText().equals(time)) {
            swiper.scrollForSpinner(element);
        }
    }

    public boolean check() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        int tomorrowMonth = tomorrow.getMonthValue();
        int tomorrowDay = tomorrow.getDayOfMonth();
        int tomorrowYear = tomorrow.getYear();

        AndroidElement dateTimeCheckElement = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorsDateTime.dateTimeCheck));
        String dateTimeCheckText = dateTimeCheckElement.getText();
        String checkDay = dateTimeCheckText.split("\\s")[0].split("-")[1];
        String checkMonth = dateTimeCheckText.split("\\s")[0].split("-")[0];
        String checkYear = dateTimeCheckText.split("\\s")[0].split("-")[2];
        String checkTime = dateTimeCheckText.split("\\s")[1];

        if (tomorrowYear == Integer.valueOf(checkYear)
                && tomorrowMonth == Integer.valueOf(checkMonth)
                && tomorrowDay == Integer.valueOf(checkDay)
                && checkTime.equals("23:11")) {
            return true;
        } else {
            return false;
        }
    }
}