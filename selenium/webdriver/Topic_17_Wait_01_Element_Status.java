package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitwait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitwait = new WebDriverWait(driver, Duration.ofSeconds(30));

//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("hautravel@email.com");
        sleepInSeconds(2);

        explicitwait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_InDom() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(2);

        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_Not_InDom() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

    }

    @Test
    public void TC_03_Presence() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("hautravel@email.com");
        sleepInSeconds(2);

        explicitwait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(2);

        explicitwait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        explicitwait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
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
