package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_08_Button {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
    }

    @Test
    public void TC_01_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        Assert.assertFalse(loginButton.isEnabled());

        //System.out.println(loginButton.getCssValue("background-color"));
        //Lấy ra mã màu nền Button
        //String loginButtonBackgroundColorRGB = loginButton.getCssValue("backgound-color");
        //Convert String từ RGB sang Color
        //Color loginButtonBackgroundColor = Color.fromString(loginButtonBackgroundColorRGB);
        //Covert qua Hexa ( và viết viết hoa hoặc viết thường )
        //String loginButtonBackgroundHexa = loginButtonBackgroundColor.asHex().toLowerCase();

        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("test123@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");

    }
    @Test
    public void TC_02_Goconsenus_Button() {
        driver.get("https://play.goconsensus.com/u5d5156df");
        sleepInSeconds(2);
        WebElement continueButton = driver.findElement(By.cssSelector("div.src-app-providers-contact-form-ui-lead-form-ui--footer-top-ysopV button.src-shared-ui-button--button-XQoY9"));
        Assert.assertFalse(continueButton.isEnabled());
        Assert.assertEquals(Color.fromString(continueButton.getCssValue("background-color")).asHex().toLowerCase(), "#673ab7");

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
