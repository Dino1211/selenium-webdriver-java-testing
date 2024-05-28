package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_11_Actions {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor javascriptExecutor;


    @BeforeClass
    public void beforeClass() {

//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_To_Elemnet_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement agetoElement = driver.findElement(By.cssSelector("input#age"));

        actions.moveToElement(agetoElement).perform();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_To_Elemnet_Myntra() {
        driver.get("https://www.myntra.com/");

        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        sleepInSeconds(1);

        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Kids Home Bath' and @class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_Menu_Fahasa() {
        driver.get("https://www.fahasa.com/");
        sleepInSeconds(2);

        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSeconds(2);

        actions.moveToElement(driver.findElement(By.xpath("//span[@class='menu-title' and text()='Đồ Chơi']"))).perform();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Ô Tô']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Ô Tô - Xe Buýt']")).isDisplayed());
    }

    @Test
    public void TC_04_Click_and_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);
        actions.clickAndHold(allNumbers.get(0))
                .moveToElement(allNumbers.get(10))
                .release().perform();
        sleepInSeconds(2);
        List<String> allNumberTextExpected = new ArrayList<String>();
        allNumberTextExpected.add("1");
        allNumberTextExpected.add("2");
        allNumberTextExpected.add("3");
        allNumberTextExpected.add("5");
        allNumberTextExpected.add("6");
        allNumberTextExpected.add("7");
        allNumberTextExpected.add("9");
        allNumberTextExpected.add("10");
        allNumberTextExpected.add("11");
        // Tổng các số đã chọn
        List<WebElement> allNumbersClickandHold = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersClickandHold.size(), 9);
        List<String> allNumbersTextActual = new ArrayList<String>();
        for (WebElement element : allNumbersClickandHold) {
            allNumbersTextActual.add(element.getText());
        }
        Assert.assertEquals(allNumberTextExpected, allNumbersTextActual);
    }

    @Test
    public void TC_05_Click_and_Select() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> selectNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(selectNumbers.size(), 20);
        actions.keyDown(Keys.CONTROL).perform();
        actions.click(selectNumbers.get(0))
                .click(selectNumbers.get(2))
                .click(selectNumbers.get(4))
                .click(selectNumbers.get(6))
                .keyUp(Keys.CONTROL).perform();
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 4);
    }

    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
        // Scroll tới element mới click được - Áp dụng với Firefox
        if (driver.toString().contains("firefox")) {
            //scrollIntoView(True): Kéo tới mép trên của element
            //scrollIntoView(False): Kéo tới mép dưới của element
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
            sleepInSeconds(2);
        }
        actions.doubleClick(doubleClickButton).perform();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-copy")).isDisplayed());
        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSeconds(1);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-copy")).isDisplayed());
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-copy"))).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-copy.context-menu-hover.context-menu-visible")).isDisplayed());
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-copy"))).perform();

        driver.switchTo().alert().accept();
        sleepInSeconds(3);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-copy")).isDisplayed());
    }

    @Test
    public void TC_08_Drag_and_Drop_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String columnA = "div#column-a";
        String columnB = "div#column-b";

        String projectPath = System.getProperty("user.dir");

        String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";

        String jsContentFile = getContentFile(dragAndDropFilePath);

        jsContentFile = jsContentFile + "$('" + columnA + "').simulateDragDrop({ dropTarget: '" + columnB + "'});";

        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
    }

    @Test
    public void TC_09_Drag_and_Drop_Xpath() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
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
