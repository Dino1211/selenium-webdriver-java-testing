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

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Topic_00_Template {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
    }

    @Test
    public void TC_01() {

    }
    @Test
    public void TC_02() {

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
