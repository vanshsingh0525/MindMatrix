package com.udemy.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;


public class ElementUtils {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        this.actions = new Actions(driver);
    }

    
    public void click(By locator) {
        WebElement elem = waitForElementToBeClickable(locator);
        actions.moveToElement(elem).click().perform();
    }

    
    public void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    
    public void doubleClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        actions.doubleClick(element).perform();
    }

    
    public void rightClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        actions.contextClick(element).perform();
    }

    
    public void sendKeys(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    
    public void sendKeys(By locator, Keys key) {
        WebElement element = waitForElementToBeVisible(locator);
        element.sendKeys(key);
    }

    public void clearField(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    
    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    
    public String getAttribute(By locator, String attribute) {
        return waitForElementToBeVisible(locator).getAttribute(attribute);
    }

    
    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    
    public boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    
    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    
    public boolean waitForTextToBePresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    
    public boolean waitForUrlToContain(String urlPart) {
        return wait.until(ExpectedConditions.urlContains(urlPart));
    }

    
    public WebElement waitForElementWithTimeout(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

  
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

   
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public void scrollByPixels(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
    }

    
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    
    public void highlightElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].style.border='3px solid red'", element);
    }

    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(waitForElementToBeVisible(locator));
        select.selectByVisibleText(text);
    }

   
    public void selectByValue(By locator, String value) {
        Select select = new Select(waitForElementToBeVisible(locator));
        select.selectByValue(value);
    }

    
    public void selectByIndex(By locator, int index) {
        Select select = new Select(waitForElementToBeVisible(locator));
        select.selectByIndex(index);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }

    public void hoverOver(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        actions.moveToElement(element).perform();
    }

    public String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = ConfigReader.getScreenshotPath() + screenshotName;

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            destination.getParentFile().mkdirs();
            FileHandler.copy(source, destination);
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void switchToFrame(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }


    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    
    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

   
    public void sendTextToAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }

    public void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    
    public void closeCurrentWindow(String mainWindowHandle) {
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }
}