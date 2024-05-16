package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Default_Dropdown {
    WebDriver driver;
    String firstName = "Hau", lastName = "Travel", password = "123456", emaiAdress = getEmailAddress(), companyName = "Dinotrek";
    String day = "12", month = "November", year = "2000";

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        sleepInSeconds(2);


        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);

        //Day,Month, Year- dropdown
        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Select monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Select yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));

        //Chọn ngày
        dayDropdown.selectByVisibleText(day);
        monthDropdown.selectByVisibleText(month);
        yearDropdown.selectByVisibleText(year);


        //Ktra dropdown là signle ( No multiple )
        Assert.assertFalse(dayDropdown.isMultiple());

        //Verify Dropdown ( Day, Month, Year )
        Assert.assertEquals(dayDropdown.getOptions().size(), 32);
        Assert.assertEquals(monthDropdown.getOptions().size(), 13);
        Assert.assertEquals(yearDropdown.getOptions().size(), 112);


        driver.findElement(By.cssSelector("input#Email")).sendKeys(emaiAdress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

        driver.findElement(By.cssSelector("a.ico-logout")).click();
        sleepInSeconds(3);


    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");

        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emaiAdress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(2);


        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emaiAdress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);



    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
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
