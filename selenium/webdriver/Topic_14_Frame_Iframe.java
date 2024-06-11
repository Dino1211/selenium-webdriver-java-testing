package webdriver;


import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
        driver.manage().window().maximize();
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
//        Assert.assertTrue(driver.findElement(By.cssSelector("div#imageTemplateContainer")).isDisplayed());
        driver.findElement(By.cssSelector("div#imageTemplateContainer img")).click();
        sleepInSeconds(2);

        WebElement fromIframe = driver.findElement(By.cssSelector("div#formTemplateContainer iframe"));
        Assert.assertTrue(fromIframe.isDisplayed());
        driver.switchTo().frame(fromIframe);

        new Select(driver.findElement(By.cssSelector("div.highlight select"))).selectByVisibleText("Freshman");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("North Dorm");
        Assert.assertTrue(driver.findElement(By.cssSelector("input#RESULT_RadioButton-4_0")).isSelected());

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");




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
