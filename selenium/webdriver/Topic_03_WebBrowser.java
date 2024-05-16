package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_03_WebBrowser {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/

    }

    @Test
    public void TC_01_Page_URL() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Page_Title() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_03_Navigate_Function() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_04_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

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
