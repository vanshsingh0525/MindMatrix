package com.udemy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

  
    public static String getBrowser() {
        return properties.getProperty("browser");
    }

   
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

   
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

 
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

  
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

   
    public static String getScreenshotPath() {
        return properties.getProperty("screenshot.path");
    }

   
    public static String getDefaultSearchTerm() {
        return properties.getProperty("default.search.term");
    }
}
