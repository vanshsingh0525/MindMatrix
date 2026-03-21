package com.udemy.pages;

import com.udemy.utils.ConfigReader;
import com.udemy.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public abstract class BasePage {

    protected WebDriver driver;
    protected ElementUtils elementUtils;
    protected WebDriverWait wait;

    protected By udemyLogo = By.cssSelector("[data-testid='header-logo'], .udemy-logo, a[href='/'] img");
    protected By searchInput = By.cssSelector("input[name='q'], input[placeholder*='Search'], [data-testid='search-input']");
    protected By searchButton = By.cssSelector("button[type='submit'], [data-testid='search-submit']");
    protected By cartIcon = By.cssSelector("[data-testid='cart'], a[href*='cart']");
    protected By loginButton = By.cssSelector("[data-testid='header-login'], a[href*='login']");
    protected By signupButton = By.cssSelector("[data-testid='header-signup'], a[href*='join']");
    protected By categoriesMenu = By.cssSelector("[data-testid='categories-header'], .categories-menu");

    protected By cookieAcceptButton = By.cssSelector("button[id*='accept'], .cookie-banner button, [data-purpose='accept-cookies']");
    protected By cookiePopup = By.cssSelector(".cookie-banner, [data-purpose='cookie-consent']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    
    public void handleCookiePopup() {
        try {
            Thread.sleep(1000); // Brief wait for popup to appear
            if (elementUtils.isDisplayed(cookieAcceptButton)) {
                elementUtils.click(cookieAcceptButton);
                System.out.println("Cookie popup accepted.");
            }
        } catch (Exception e) {
        }
    }

   
    public void handleLocationPopup() {
        try {
            By dismissButton = By.cssSelector("[data-purpose='dismiss'], .notification-dismiss, button[aria-label='Close']");
            if (elementUtils.isDisplayed(dismissButton)) {
                elementUtils.click(dismissButton);
                System.out.println("Location/notification popup dismissed.");
            }
        } catch (Exception e) {
        }
    }

    public void clickSignupButton() {
        elementUtils.click(signupButton);
    }

    
    public String getPageTitle() {
        return driver.getTitle();
    }

   
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    
    public void waitForPageLoad() {
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("return document.readyState").equals("complete"));
    }

    
    public void scrollToTop() {
        elementUtils.scrollToTop();
    }

   
    public void scrollToBottom() {
        elementUtils.scrollToBottom();
    }
}