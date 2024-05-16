package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_Custom_Dropdown {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/
    }

    @Test
    public void TC_01_Jquery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Fast");
        sleepInSeconds(2);

        selectItemInDropdown("span#files-button", "ul#files-menu div", "jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"jQuery.js");
        sleepInSeconds(2);

        selectItemInDropdown("span#number-button", "ul#number-menu div", "3");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"3");
        sleepInSeconds(2);

        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Dr.");
        sleepInSeconds(2);
        
    }

    @Test
    public void TC_02_ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div#root", "div.visible span", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider ")).getText(),"Christian");
        sleepInSeconds(2);
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
        sleepInSeconds(2);
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemEditableDropdown("input.search", "div.item span", "Angola");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
        sleepInSeconds(2);

        selectItemEditableDropdown("input.search", "div.item span", "Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
        sleepInSeconds(2);

        selectItemEditableDropdown("input.search", "div.item span", "Benin");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");
        sleepInSeconds(2);

    }

    @Test
    public void TC_05_Nopcommerce() {
        driver.get("https://demo.nopcommerce.com/register");

        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "14");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='14']")).isSelected());
        sleepInSeconds(1);

        selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "May");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='5']")).isSelected());
        sleepInSeconds(1);

        selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "2024");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='2024']")).isSelected());
        sleepInSeconds(1);

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
    public void selectItemInDropdown(String parentCss, String childItemCss, String ItemExpected){
        driver.findElement(By.cssSelector(parentCss)).click(); //"span#number-button"
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); //"ul#number-menu div"

        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for(WebElement item : allItems){
            if(item.getText().equals(ItemExpected)){ // Giá trị mong muốn
                item.click();
                break;
            }
        }
    }
    public void selectItemEditableDropdown(String parentCss, String childItemCss, String ItemExpected){
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(ItemExpected);
        //"span#number-button"
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); //"ul#number-menu div"

        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for(WebElement item : allItems){
            if(item.getText().equals(ItemExpected)){ // Giá trị mong muốn
                item.click();
                break;
            }
        }
    }

}
