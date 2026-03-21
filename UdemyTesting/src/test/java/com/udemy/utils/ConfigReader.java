package com.udemy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Centralized configuration management.
 * This class is COMMON for all team members. DO NOT MODIFY unless discussed with team.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE_PATH);
        }
    }

    /**
     * Get any property value by key.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get the base URL for Udemy.
     */
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    /**
     * Get the browser type (chrome, firefox, edge).
     */
    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    /**
     * Check if headless mode is enabled.
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    /**
     * Get implicit wait timeout in seconds.
     */
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    /**
     * Get explicit wait timeout in seconds.
     */
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    /**
     * Get page load timeout in seconds.
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

    /**
     * Get screenshot path.
     */
    public static String getScreenshotPath() {
        return properties.getProperty("screenshot.path");
    }

    /**
     * Get default search term for testing.
     */
    public static String getDefaultSearchTerm() {
        return properties.getProperty("default.search.term");
    }
}