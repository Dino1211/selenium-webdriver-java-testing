package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_05_Textbox_TextArea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/

    }

    @Test
    public void TC_01_Empty_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");

    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys("12341234@123.123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void TC_03_Password_Min_Characters() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");


    }

    @Test
    public void TC_04_Incorrect_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");

        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg")).getText(), "Invalid login or password.");


    }

    @Test
    public void TC_05_Incorrect_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
        sleepInSeconds(2);

        String firstName = "Hau", lastName = "Travel", emailAddress = getEmailAddress(), password = "123456";
        String fullName = firstName + " " + lastName;

        // Register
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.xpath("//button[@class='button']//span[text()='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg p")).getText(), "Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        //Logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSeconds(5);

        //Login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg p")).getText(), "Hello, " + fullName + "!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        //Verify Account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);

        driver.findElement(By.cssSelector("li.level0.nav-1.first")).click();
        sleepInSeconds(2);
        

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

    public String getEmailAddress() {
        Random rand = new Random();
        return "test" + rand.nextInt(9999) + "@gmail.net";
    }
}
