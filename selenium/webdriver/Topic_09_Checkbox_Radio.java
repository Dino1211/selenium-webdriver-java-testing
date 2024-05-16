package webdriver;


import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_Checkbox_Radio {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
    }

    @Test
    public void TC_01_Default_Telerik_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By rearCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        checkToElement(dualZoneCheckbox);
        checkToElement(rearCheckbox);

        Assert.assertTrue(driver.findElement(rearCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        uncheckToElement(dualZoneCheckbox);
        uncheckToElement(rearCheckbox);

        Assert.assertFalse(driver.findElement(rearCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

    }

    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By twoPertrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        By twoDeiselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

        checkToElement(twoPertrolRadio);
        Assert.assertTrue(driver.findElement(twoPertrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDeiselRadio).isSelected());

        checkToElement(twoDeiselRadio);
        Assert.assertFalse(driver.findElement(twoPertrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDeiselRadio).isSelected());

    }

    @Test
    public void TC_03_Default_Material_Radio() {
        driver.get("https://material.angular.io/components/radio/examples");
        By summerRadio = By.xpath("//input[@value='Summer']/parent::div");
        By winterRadio = By.xpath("//input[@value='Winter']/parent::div");

        checkToElement(summerRadio);
        Assert.assertFalse(driver.findElement(summerRadio).isSelected());
        Assert.assertFalse(driver.findElement(winterRadio).isSelected());

       /* checkToElement(winterRadio);
        Assert.assertFalse(driver.findElement(summerRadio).isSelected());
        Assert.assertTrue(driver.findElement(winterRadio).isSelected());*/

    }

    @Test
    public void TC_04_SelectALL_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allItemcheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //Chọn tất cả các checkbox
        for (WebElement checkbox : allItemcheckbox) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Verify tất cả các checkbox đã được chọn
        for (WebElement checkbox : allItemcheckbox) {
            Assert.assertTrue(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allItemcheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //Chọn 1 checkbox/Radio
        for (WebElement checkbox : allItemcheckbox) {
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
                sleepInSeconds(1);
            }
        }

        //Verify tất cả
        for (WebElement checkbox : allItemcheckbox) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }
    @Test
    public void TC_05_Default_Login_Radio() {
        driver.get("https://login.ubuntu.com/");

        By radioButton = By.cssSelector("input[value='login']");
        By checkboxButton = By.cssSelector("input[type='checkbox']");

        checkToElement(radioButton);
        Assert.assertTrue(driver.findElement(radioButton).isSelected());
//        Assert.assertTrue(driver.findElement(checkboxButton).isDisplayed());

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

    // Nếu như element chưa được chọn thì click vào cho n chọn
    public void checkToElement(By byXpath) {
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }

    // Nếu như element được chọn rồi thì click vào lần nữa cho bỏ chọn
    public void uncheckToElement(By byXpath) {
        if (driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }


}
