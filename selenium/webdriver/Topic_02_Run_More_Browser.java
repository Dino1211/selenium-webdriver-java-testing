package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Run_More_Browser {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");



    @Test
    public void TC_01_Run_On_Firefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        driver.quit();
    }



    @Test
    public void TC_02_Run_On_Edge() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        driver.quit();
    }



}