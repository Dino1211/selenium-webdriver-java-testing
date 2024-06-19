package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_15_Window_Tab {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Basic_Form() {

    }

    @Test
    public void TC_02() {

    }

    @Test
    public void TC_03_Cambridage_Selenium4() {
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.cssSelector("span.cdo-login-button span")).click();
        sleepInSeconds(2);

        driver.switchTo().newWindow(WindowType.WINDOW).get("https://dictionary.cambridge.org/auth/signin?rid=amp-d5TCK8BnrOjzAfdevJs1Iw&return=https%3A%2F%2Fcdn.ampproject.org%2Fv0%2Famp-login-done-0.1.html%3Furl%3Dhttps%253A%252F%252Fdictionary.cambridge.org%252F");

        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-textbox']//span[text()='This field is required']")).getText(), "This field is required");

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-password']//span[text()='This field is required']")).getText(), "This field is required");

        switchWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#searchword")).sendKeys("Test");

        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        sleepInSeconds(2);

    }

    @Test
    public void TC_04_Havard_Selenium4() {
        driver.get("https://courses.dce.harvard.edu/");

        driver.findElement(By.cssSelector("a.anon-only")).click();
        sleepInSeconds(2);

        driver.switchTo().newWindow(WindowType.WINDOW).get("https://login.dce.harvard.edu/u/login?state=hKFo2SBRNlNYMTFyRzFlR1VvV1ZieUxpZ3JDUE1pM2N3X3A0ZqFur3VuaXZlcnNhbC1sb2dpbqN0aWTZIC1VRThzZ3JKWUpOWEg1SjVmZjUwLVFxLUZiMzZPc0tKo2NpZNkgNXIxczhEMVh4SHJFVE5yRTEzWUw5SnA1aTRHZFJTc24");
        sleepInSeconds(2);

//        Assert.assertTrue(driver.findElement(By.cssSelector("main.login section")).isDisplayed());

        switchWindowByTitle("DCE Course Search");

        Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());

        driver.findElement(By.cssSelector("button.sam-wait__close")).click();

        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Hautravel");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2024");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Full Term");
        driver.findElement(By.cssSelector("button#search-button")).click();

    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }

    public void switchWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWindows = driver.getTitle();
            if (currentWindows.equals(title)) {
                break;
            }
        }
    }

    public void sleepInSeconds(long timInSecond) {
        try {
            Thread.sleep(timInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
