package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_17_Wait_04_Expliciwait {
    WebDriver driver;
    WebDriverWait explicitwait;
    String projectPath = System.getProperty("user.dir");

    String dogName = "dog.jpg";
    String catName = "cat.jpg";
    String birdName = "bird.jpg";

    String dogfilePath = projectPath + File.separator + "uploadFiles" + File.separator + dogName;
    String catfilePath = projectPath + File.separator + "uploadFiles" + File.separator + catName;
    String birdfilePath = projectPath + File.separator + "uploadFiles" + File.separator + birdName;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitwait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        By selectedByDate = By.cssSelector("div.RadAjaxPanel>span");

        Assert.assertEquals(driver.findElement(selectedByDate).getText(), "No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='12']")).click();

        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

        Assert.assertEquals(driver.findElement(selectedByDate).getText(), "Friday, July 12, 2024");

    }

    @Test
    public void TC_02_Upload_File() {
        driver.get("https://gofile.io/welcome");

        Assert.assertTrue(explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        explicitwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();

        Assert.assertTrue(explicitwait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(dogfilePath + "\n" + catfilePath + "\n" + birdfilePath);

        Assert.assertTrue(explicitwait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar")))));

        explicitwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        Assert.assertTrue(explicitwait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        Assert.assertTrue(explicitwait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + dogName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitwait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + catName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitwait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + birdName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

        Assert.assertTrue(explicitwait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + dogName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitwait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + catName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitwait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + birdName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }

}
