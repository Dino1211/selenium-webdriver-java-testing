package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_WebElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/

    }

    @Test
    public void TC_01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.findElement(By.cssSelector("input#mail")).isDisplayed()){
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        } else {
            System.out.println("Email is not displayed");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isDisplayed()){
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 is displayed");
        } else {
            System.out.println("Under 18 is not displayed");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        } else {
            System.out.println("Education is not displayed");
        }

        if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            System.out.println("Name User 5 is displayed");
        } else {
            System.out.println("Name User 5 is not displayed");
        }



    }

    @Test
    public void TC_02_isEnabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");


        if(driver.findElement(By.cssSelector("input#mail")).isEnabled()){
            System.out.println("Email is enable");
        } else {
            System.out.println("Email is disbale");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isEnabled()){
            System.out.println("Under 18 is enable");
        } else {
            System.out.println("Under 18 is disbale");
        }

        if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()){
            System.out.println("Password is enable");
        } else {
            System.out.println("Password is disbale");
        }

        if(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()){
            System.out.println("Checkbox is enable");
        } else {
            System.out.println("Checkbox is disbale");
        }
    }

    @Test
    public void TC_03_isSelected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        if(driver.findElement(By.cssSelector("input#under_18")).isSelected()){
            System.out.println("Under 18 is selected");
        } else {
            System.out.println("Under 18 is de-selected");
        }

        if(driver.findElement(By.cssSelector("input#java")).isSelected()){
            System.out.println("Java language is selected");
        } else {
            System.out.println("Java language is de-selected");
        }

        driver.findElement(By.cssSelector("input#java")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());


    }

    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("test@gmail.com");

        //case 1 - Number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 2 - Lowercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("test");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 3 - Uppercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 4 - Special Character
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!@#");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 5 - Max Lenght
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345678");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());

        //case 6 - Valid
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Auto@123");

        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
    }


    @AfterClass
    public void afterClass() {
       // driver.quit();
    }

    public void sleepInSeconds(long timInSecond) {
        try {
            Thread.sleep(timInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
