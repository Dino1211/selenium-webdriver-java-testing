package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_11_Actions {
    WebDriver driver;
    Actions actions;


    @BeforeClass
    public void beforeClass() {

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_To_Elemnet_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement agetoElement = driver.findElement(By.cssSelector("input#age"));

        actions.moveToElement(agetoElement).perform();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_To_Elemnet_Myntra() {
        driver.get("https://www.myntra.com/");

        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        sleepInSeconds(1);

        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Kids Home Bath' and @class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_Menu_Fahasa() {
        driver.get("https://www.fahasa.com/");
        sleepInSeconds(2);

        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSeconds(2);

        actions.moveToElement(driver.findElement(By.xpath("//span[@class='menu-title' and text()='Đồ Chơi']"))).perform();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Ô Tô']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Ô Tô - Xe Buýt']")).isDisplayed());
    }

    @Test
    public void TC_04_Click_and_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);

        actions.clickAndHold(allNumbers.get(0))
                .moveToElement(allNumbers.get(10))
                .release().perform();
        sleepInSeconds(2);

        List<String> allNumberTextExpected = new ArrayList<String>();
        allNumberTextExpected.add("1");
        allNumberTextExpected.add("2");
        allNumberTextExpected.add("3");
        allNumberTextExpected.add("5");
        allNumberTextExpected.add("6");
        allNumberTextExpected.add("7");
        allNumberTextExpected.add("9");
        allNumberTextExpected.add("10");
        allNumberTextExpected.add("11");
        // Tổng các số đã chọn
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 9);

        List<String> allNumbersTextActual = new ArrayList<String>();

        for (WebElement element : allNumbersSelected) {
            allNumbersTextActual.add(element.getText());
        }
        Assert.assertEquals(allNumberTextExpected, allNumbersTextActual);
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    public void sleepInSeconds(long timInSecond) {
        try {
            Thread.sleep(timInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
