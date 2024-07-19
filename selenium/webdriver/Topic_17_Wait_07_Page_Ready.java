package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Topic_17_Wait_06_Fluentwait {
    WebDriver driver;
    FluentWait <WebDriver> fluentDriver;
    FluentWait <WebElement> fluentElement;
    FluentWait <String> fluentString;




    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countdount = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        fluentElement = new FluentWait<WebElement>(countdount);

        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println("Time = " + text);
                return text.endsWith("00");
            }
        });

    }
    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        fluentDriver = new FluentWait<WebDriver>(driver);

        fluentDriver.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                String text = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
                System.out.println("Get text: " + text);
                return text;
            }
        });


    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }
    }



