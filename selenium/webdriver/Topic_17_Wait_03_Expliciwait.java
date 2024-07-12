package webdriver;


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
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_03_Expliciwait {
    WebDriver driver;
    WebDriverWait expliciwait;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
//      expliciwait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("https://automationfc.github.io/dynamic-loading/");
//        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Equal_5s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        expliciwait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();

        expliciwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading>img")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_02_Less_5s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        expliciwait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.findElement(By.cssSelector("div#start>button")).click();

        expliciwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading>img")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_03_Greater_5s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        expliciwait = new WebDriverWait(driver, Duration.ofSeconds(50));

        driver.findElement(By.cssSelector("div#start>button")).click();

        expliciwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading>img")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }

}
