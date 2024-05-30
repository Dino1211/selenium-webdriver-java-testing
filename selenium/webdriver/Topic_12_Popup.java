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

public class Topic_12_Popup {
    WebDriver driver;
    String username = "automation@email.com", password = "123456";


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Fixed_Popup_InDom() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.modal-content")).isDisplayed());
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys(username);
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.modal-content")).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_Popup_InDom() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login div.modal-content")).isDisplayed());
        driver.findElement(By.cssSelector("div#k-popup-account-login input#user-login")).sendKeys(username);
        driver.findElement(By.cssSelector("div#k-popup-account-login input#user-password")).sendKeys(password);
        driver.findElement(By.cssSelector("div#k-popup-account-login button#btn-submit-login")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#k-popup-account-login div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Fixed_Popup_NotInDom1() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content>div")).isDisplayed());
        driver.findElement(By.cssSelector("div.ReactModal__Content p.login-with-email")).click();
        sleepInSeconds(2);

//        Assert.assertTrue(driver.findElement(By.cssSelector("div#VIP_BUNDLE")).isDisplayed());
//        driver.findElement(By.cssSelector("div#VIP_BUNDLE img.hbqSye")).click();

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        //input[@type='email']/parent::div/following-sibling::span[1]
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[2]")).getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content>div")).size(), 0);
    }

    @Test
    public void TC_04_Fixed_Popup_NotInDom2() {
        driver.get("https://www.facebook.com/");
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);
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
