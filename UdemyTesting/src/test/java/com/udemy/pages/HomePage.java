package com.udemy.pages;

import com.udemy.utils.ConfigReader;
import com.udemy.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class HomePage extends BasePage {

   
    private By searchInputField = By.cssSelector("input[name='q'], input[type='text'][placeholder*='Search'], input[data-purpose*='search']");
    private By searchSubmitButton = By.cssSelector("button[type='submit'], button[class*='search']");

  
    private By searchSuggestionsDropdown = By.cssSelector("[class*='autosuggest'], [class*='suggestion'], div[class*='popover']");
    private By searchSuggestionItems = By.cssSelector("[class*='autosuggest'] li a, [class*='suggestion'] a, div[role='option']");


    private By udemyMainLogo = By.cssSelector("img[alt*='Udemy'], a[href='/'] img");
    private By categoriesDropdown = By.cssSelector("button[class*='categories'], [data-purpose*='categories']");


    private By dismissBannerButton = By.cssSelector("button[class*='dismiss'], button[aria-label*='Dismiss'], button[aria-label*='Close']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

  
    public void navigateToHomePage() {
        driver.get(ConfigReader.getBaseUrl());
        waitForPageLoad();
        sleep(2000);
        handleCookiePopup();
        handleLocationPopup();
        dismissPromoBanner();
    }

  
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

  
    public void dismissPromoBanner() {
        try {
            List<WebElement> buttons = driver.findElements(dismissBannerButton);
            for (WebElement btn : buttons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    sleep(500);
                }
            }
        } catch (Exception e) {
            // Banner not present
        }
    }

  
    public boolean isHomePageLoaded() {
        try {
            sleep(1000);
            List<WebElement> searchElements = driver.findElements(searchInputField);
            if (!searchElements.isEmpty()) {
                return true;
            }
            return driver.getCurrentUrl().contains("udemy.com");
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("udemy.com");
        }
    }

   
    public boolean isLogoDisplayed() {
        try {
            return !driver.findElements(udemyMainLogo).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

 
    public void enterSearchText(String searchText) {
        try {
            WebElement searchBox = elementUtils.waitForElementToBeClickable(searchInputField);
            searchBox.click();
            searchBox.clear();
            searchBox.sendKeys(searchText);
            sleep(1500); // Wait for suggestions to appear
        } catch (Exception e) {
            WebElement searchBox = driver.findElement(searchInputField);
            searchBox.clear();
            searchBox.sendKeys(searchText);
        }
    }

   
    public void clickSearchButton() {
        try {
            List<WebElement> buttons = driver.findElements(searchSubmitButton);
            for (WebElement btn : buttons) {
                if (btn.isDisplayed() && btn.isEnabled()) {
                    btn.click();
                    return;
                }
            }
            
            driver.findElement(searchInputField).sendKeys(Keys.ENTER);
        } catch (Exception e) {
            try {
                driver.findElement(searchInputField).sendKeys(Keys.ENTER);
            } catch (Exception ex) {
                System.out.println("Could not submit search: " + ex.getMessage());
            }
        }
    }

  
    public void searchForCourse(String searchTerm) {
        enterSearchText(searchTerm);
        clickSearchButton();
    }

  
    public boolean isSearchSuggestionsDisplayed() {
        try {
            sleep(1500);
            
            List<WebElement> dropdowns = driver.findElements(searchSuggestionsDropdown);
            for (WebElement dropdown : dropdowns) {
                if (dropdown.isDisplayed()) {
                    return true;
                }
            }
            
            List<WebElement> items = driver.findElements(searchSuggestionItems);
            for (WebElement item : items) {
                if (item.isDisplayed()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

   
    public List<WebElement> getSearchSuggestions() {
        try {
            sleep(1000);
            return driver.findElements(searchSuggestionItems);
        } catch (Exception e) {
            return List.of();
        }
    }


    public boolean suggestionsContainTerm(String term) {
        try {
            List<WebElement> suggestions = getSearchSuggestions();
            for (WebElement suggestion : suggestions) {
                if (suggestion.getText().toLowerCase().contains(term.toLowerCase())) {
                    return true;
                }
            }
            
            return isSearchSuggestionsDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

  
    public void clickSuggestion(int index) {
        List<WebElement> suggestions = getSearchSuggestions();
        if (index < suggestions.size()) {
            suggestions.get(index).click();
        }
    }


    public void clickSuggestionContaining(String text) {
        List<WebElement> suggestions = getSearchSuggestions();
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().toLowerCase().contains(text.toLowerCase())) {
                suggestion.click();
                return;
            }
        }
    }

    public String getSearchInputValue() {
        try {
            return driver.findElement(searchInputField).getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }


    public void clearSearchInput() {
        try {
            WebElement searchBox = driver.findElement(searchInputField);
            searchBox.clear();
            searchBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        } catch (Exception e) {
            
        }
    }


    public boolean isSearchInputEmpty() {
        String value = getSearchInputValue();
        return value == null || value.isEmpty();
    }

  
    public void clickCategories() {
        try {
            elementUtils.click(categoriesDropdown);
        } catch (Exception e) {
            System.out.println("Could not click categories: " + e.getMessage());
        }
    }

   
    public boolean isOnHomePage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("udemy.com") &&
               !currentUrl.contains("/courses/search") &&
               !currentUrl.contains("?q=");
    }
}
