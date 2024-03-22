/* Task 1. Все тесты нужно писать к приложению ApiDemos. Которое мы рассматривали на занятие.
1) Открыть приложение → Views →
Написать тест, который будет проверять количество элементов на данной
(и меются в виду кнопки при тапе на которые можно будет перейти на новую страницу). ( Их тут всего 42).

Task 2. 2) Открыть приложение → Views → Data Widgets → 1. Dialog →
Здесь нужно задать дату и время использую данные кнопки: Change the date | Change the time(spinner)
Нужно задать завтрашнее число и время 11:11 PM

Task 3. 3) Открыть приложение → Views → TextSwitcher →
Проверить работоспособность кнопки Next. Суть проверки заключается в том,
что после определенного количества нажатия на кнопку Next это количество нажатий
должно отобразиться в поле по центру экрана. */

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ApiDemosTest {
    AppiumDriver driver;
    PageApiDemosViewCount pageApiDemosViewCount;
    PageApiDemosDateTime pageApiDemosDateTime;
    PageApiDemosClickCount pageApiDemosClickCount;


    @BeforeMethod
    public void preConditionMethod() {
        driver = new DriverInit().getDriver();
        pageApiDemosViewCount = new PageApiDemosViewCount(driver);
        pageApiDemosDateTime = new PageApiDemosDateTime(driver);
        pageApiDemosClickCount = new PageApiDemosClickCount(driver);
    }

    @AfterMethod
    public void postCondition() {
        driver.quit();
    }

    @Test
    public void checkNumberOfClickableElementsTest() {
        int numberOfButtons = 42;
        pageApiDemosViewCount.clickOnViewButton();
        Assert.assertEquals(pageApiDemosViewCount.countOfButtons(), numberOfButtons, "The number of buttons is not" + numberOfButtons);
    }

    @Test
    public void setDataAndTimeTest() {
        pageApiDemosDateTime.clickOnViewButton();
        pageApiDemosDateTime.clickOnDateWidgets();
        pageApiDemosDateTime.clickOnDialog();
        pageApiDemosDateTime.clickOnChangeDate();
        pageApiDemosDateTime.setTomorrowDay();
        pageApiDemosDateTime.clickOnChangeTimeSpinner();
        pageApiDemosDateTime.setHour("11");
        pageApiDemosDateTime.setMinute("11");
        pageApiDemosDateTime.setPartOfDay("PM");
        pageApiDemosDateTime.clickOnOkButton();
        Assert.assertTrue(pageApiDemosDateTime.check());
    }

    @Test
    public void checkNumberOfClicksTest() {
        int testNumberOfClicks = 3;
        pageApiDemosClickCount.clickOnViewButton();
        pageApiDemosClickCount.scrollToElement();
        pageApiDemosClickCount.clickOnTextSwitcher();
        pageApiDemosClickCount.clickOnNextButton(testNumberOfClicks);
        Assert.assertEquals(pageApiDemosClickCount.getResultNumberOfClick(), testNumberOfClicks, "The result number and number of clicks are not equal or incorrect input data");
    }
}
