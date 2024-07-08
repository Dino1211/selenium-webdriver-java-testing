package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Topic_16_UploadFiles {
    WebDriver driver;
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

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
    }

    @Test
    public void TC_01_Sigle_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadFileBy = By.xpath("//input[@name='files[]']");

        driver.findElement(uploadFileBy).sendKeys(dogfilePath);
        driver.findElement(uploadFileBy).sendKeys(catfilePath);
        driver.findElement(uploadFileBy).sendKeys(birdfilePath);
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ dogName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ catName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ birdName + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td button.start"));
        for (WebElement start : startButtons){
            start.click();
            sleepInSeconds(3);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ dogName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ catName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ birdName + "']")).isDisplayed());

    }
    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadFileBy = By.xpath("//input[@name='files[]']");

        driver.findElement(uploadFileBy).sendKeys(dogfilePath + "\n" + catfilePath + "\n" + birdfilePath);
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ dogName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ catName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ birdName + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td button.start"));
        for (WebElement start : startButtons){
            start.click();
            sleepInSeconds(3);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ dogName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ catName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ birdName + "']")).isDisplayed());



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
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nhập số (tối đa 9 ký tự): ");
            String input = scanner.nextLine().trim();

            // Định dạng số với 2 số sau dấu chấm
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(java.math.RoundingMode.HALF_UP);

            try {
                double number = Double.parseDouble(input);

                // Đổi định dạng số về chuỗi với 2 số sau dấu chấm
                String formatted = df.format(number);

                // In ra số sau khi làm tròn
                System.out.println("Số sau khi làm tròn: " + formatted);

            } catch (NumberFormatException e) {
                System.out.println("Đầu vào không hợp lệ. Vui lòng nhập lại.");
            }

            scanner.close();
        }
    }

}
