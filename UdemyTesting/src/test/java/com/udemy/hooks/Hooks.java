package com.udemy.hooks;

import com.udemy.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(DriverManager::quitDriver));
    }

    
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("Tags: " + scenario.getSourceTagNames());
        System.out.println("========================================");
        // Ensure driver is created
        DriverManager.getDriver();
    }

    
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null && scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
                System.out.println("Screenshot captured for failed scenario: " + scenario.getName());
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        System.out.println("========================================");
        System.out.println("Completed Scenario: " + scenario.getName());
        System.out.println("Status: " + scenario.getStatus());
        System.out.println("========================================\n");

    }

  
    @SuppressWarnings("unused")
    private void resetBrowserStorage(WebDriver driver) {
        if (driver instanceof JavascriptExecutor jsExecutor) {
            try {
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl != null && currentUrl.startsWith("http")) {
                    jsExecutor.executeScript("window.sessionStorage.clear(); window.localStorage.clear();");
                }
            } catch (Exception ignored) {
                // Some browsers block storage on about:blank or data URLs
            }
        }
    }
}