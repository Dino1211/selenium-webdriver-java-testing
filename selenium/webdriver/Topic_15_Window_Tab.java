package webdriver;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_15_Window_Tab {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        switchToWindowByTitle("Google");

        driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Travel");
        sleepInSeconds(2);

        switchToWindowByTitle("Selenium WebDriver");
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(2);

        switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSeconds(1);

        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        closeAllWindowsWithoutParent(parentID);

    }

    @Test
    public void TC_02_Tech_Panda() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']//ul//li//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2//following-sibling::div[@class='actions']//ul//li//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product IPhone has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSeconds(2);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(), "COMPARE PRODUCTS");

        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "Are you sure you would like to remove all products from your comparison?");

        alert.accept();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The comparison list was cleared.");
    }


    @AfterClass
    public void afterClass() {

//        driver.quit();
    }

    public void switchToWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    public void sleepInSeconds(long timInSecond) {
        try {
            Thread.sleep(timInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
