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

/**
 * ElementUtils - Common utility methods for WebElement interactions.
 * This class is COMMON for all team members. DO NOT MODIFY unless discussed with team.
 *
 * Usage: Create instance with driver -> new ElementUtils(driver)
 */
public class ElementUtils {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        this.actions = new Actions(driver);
    }

    // ==================== CLICK OPERATIONS ====================

    /**
     * Click on element after waiting for it to be clickable.
     */
    public void click(By locator) {
        WebElement elem = waitForElementToBeClickable(locator);
        actions.moveToElement(elem).click().perform();
    }

    /**
     * Click using JavaScript (useful for hidden or overlapped elements).
     */
    public void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Double click on element.
     */
    public void doubleClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        actions.doubleClick(element).perform();
    }

    /**
     * Right click on element.
     */
    public void rightClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        actions.contextClick(element).perform();
    }

    // ==================== INPUT OPERATIONS ====================

    /**
     * Clear field and send keys.
     */
    public void sendKeys(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Send keys with keyboard actions (for special keys).
     */
    public void sendKeys(By locator, Keys key) {
        WebElement element = waitForElementToBeVisible(locator);
        element.sendKeys(key);
    }

    /**
     * Clear field using keyboard shortcuts.
     */
    public void clearField(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    // ==================== TEXT OPERATIONS ====================

    /**
     * Get visible text from element.
     */
    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    /**
     * Get attribute value from element.
     */
    public String getAttribute(By locator, String attribute) {
        return waitForElementToBeVisible(locator).getAttribute(attribute);
    }

    // ==================== ELEMENT STATE CHECKS ====================

    /**
     * Check if element is displayed.
     */
    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check if element is enabled.
     */
    public boolean isEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    /**
     * Check if element is selected (for checkboxes/radio buttons).
     */
    public boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    /**
     * Check if element exists in DOM.
     */
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // ==================== WAIT OPERATIONS ====================

    /**
     * Wait for element to be visible.
     */
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable.
     */
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to disappear.
     */
    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element.
     */
    public boolean waitForTextToBePresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait for URL to contain specific text.
     */
    public boolean waitForUrlToContain(String urlPart) {
        return wait.until(ExpectedConditions.urlContains(urlPart));
    }

    /**
     * Custom wait with specified timeout.
     */
    public WebElement waitForElementWithTimeout(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ==================== SCROLL OPERATIONS ====================

    /**
     * Scroll to element.
     */
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scroll to bottom of page.
     */
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scroll to top of page.
     */
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Scroll by pixels.
     */
    public void scrollByPixels(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
    }

    // ==================== JAVASCRIPT OPERATIONS ====================

    /**
     * Execute custom JavaScript.
     */
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    /**
     * Highlight element for debugging.
     */
    public void highlightElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].style.border='3px solid red'", element);
    }

    // ==================== DROPDOWN OPERATIONS ====================

    /**
     * Select dropdown option by visible text.
     */
    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(waitForElementToBeVisible(locator));
        select.selectByVisibleText(text);
    }

    /**
     * Select dropdown option by value.
     */
    public void selectByValue(By locator, String value) {
        Select select = new Select(waitForElementToBeVisible(locator));
        select.selectByValue(value);
    }

    /**
     * Select dropdown option by index.
     */
    public void selectByIndex(By locator, int index) {
        Select select = new Select(waitForElementToBeVisible(locator));
        select.selectByIndex(index);
    }

    // ==================== LIST OPERATIONS ====================

    /**
     * Get all elements matching locator.
     */
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Get count of elements matching locator.
     */
    public int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }

    // ==================== HOVER OPERATIONS ====================

    /**
     * Hover over element.
     */
    public void hoverOver(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        actions.moveToElement(element).perform();
    }

    // ==================== SCREENSHOT OPERATIONS ====================

    /**
     * Take screenshot and save to configured path.
     */
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

    // ==================== FRAME OPERATIONS ====================

    /**
     * Switch to frame by locator.
     */
    public void switchToFrame(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }

    /**
     * Switch to frame by index.
     */
    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    /**
     * Switch to default content (out of frame).
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // ==================== ALERT OPERATIONS ====================

    /**
     * Accept alert.
     */
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /**
     * Dismiss alert.
     */
    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    /**
     * Get alert text.
     */
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    /**
     * Send text to alert prompt.
     */
    public void sendTextToAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }

    // ==================== WINDOW OPERATIONS ====================

    /**
     * Switch to new window/tab.
     */
    public void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    /**
     * Close current window and switch back to main.
     */
    public void closeCurrentWindow(String mainWindowHandle) {
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }
}
