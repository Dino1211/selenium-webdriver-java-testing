package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_13_Shadow_Dom {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
    }

    @Test
    public void TC_01_Shadow_Dom() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        sleepInSeconds(3);

        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("span#shadow_content")).getText(), "some text");

        WebElement checkboxShadow = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkboxShadow.isSelected());

        shadowHost = shadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        shadowRoot = shadowHost.getShadowRoot();

        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");


    }

    @Test
    public void TC_02_ShadowDom_Shoppe() {
        driver.get("https://shopee.vn/");
        sleepInSeconds(5);

        WebElement shadowHostElement = driver.findElement(By.cssSelector("div.home-page>shopee-banner-popup-stateful"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();
        sleepInSeconds(3);

        Assert.assertTrue(shadowRootContext.findElement(By.cssSelector("div.home-popup__content")).isDisplayed());
        shadowRootContext.findElement(By.cssSelector("svg.home-popup__close-button")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Iphone 15 promax");
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();

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
