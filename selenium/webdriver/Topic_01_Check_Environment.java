package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
           System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }


        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Url() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2F");
    }

    @Test
    public void TC_02_Logo() {
        Assert.assertTrue(driver.findElement(By.cssSelector("img._97vu")).isDisplayed());
    }

    @Test
    public void TC_03_Form() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='_4-u2 _1w1t _4-u8 _52jv']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}