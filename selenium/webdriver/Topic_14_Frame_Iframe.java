package webdriver;


import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_14_Frame_Iframe {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
    }

    @Test
    public void TC_01_Toidicodedao() {
        driver.get("https://toidicodedao.com/");

        Assert.assertTrue(driver.findElement(By.cssSelector("div.fb-page")).isDisplayed());

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page span iframe")));



    }
    @Test
    public void TC_02_Fromsite_Iframe() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#imageTemplateContainer")).isDisplayed());
        driver.findElement(By.cssSelector("div#imageTemplateContainer")).click();


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
