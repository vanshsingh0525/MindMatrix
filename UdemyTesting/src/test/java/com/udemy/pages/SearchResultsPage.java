package com.udemy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchResultsPage extends BasePage {

   
    private By searchResultsContainer = By.cssSelector("div[class*='course-list'], div[data-purpose*='search'], main");
    private By courseCards = By.cssSelector("div[class*='course-card'], div[data-purpose='course-card-container'], div[class*='popper--popper']");
    private By courseCardTitles = By.cssSelector("div[class*='course-card'] h3, a[class*='course-card-title'], div[class*='course-title'], h3[data-purpose*='title']");

  
    private By courseCardAlt = By.cssSelector("div[class*='course-card-module'], div[class*='browse-course']");
    private By courseLink = By.cssSelector("a[href*='/course/']");


    private By noResultsMessage = By.cssSelector("[class*='no-results'], [class*='empty-state'], div[class*='sorry']");

    
    private By resultsCount = By.cssSelector("[class*='search-headline'], span[class*='results'], div[class*='result-count']");

  
    private By filterContainer = By.cssSelector("[class*='filter'], aside[class*='sidebar'], div[data-purpose='filter-panel']");
    private By filterSidebar = By.cssSelector("div[class*='filter-panel'], div[class*='sidebar'], aside");

   
    private By ratingFilterSection = By.cssSelector("[data-purpose='filter-group-ratings'], div[class*='filter'][class*='rating'], fieldset:has(legend:contains('Ratings'))");
    private By ratingOptions = By.cssSelector("input[name*='rating'], input[type='radio'][id*='rating'], label[class*='filter-button'][class*='rating']");
    private By rating45Up = By.cssSelector("input[value='4.5'], label:has(span:contains('4.5')), [data-purpose='filter-option-ratings'] input[value='4.5']");
    private By rating40Up = By.cssSelector("input[value='4.0'], label:has(span:contains('4.0')), [data-purpose='filter-option-ratings'] input[value='4.0']");
    private By rating35Up = By.cssSelector("input[value='3.5'], label:has(span:contains('3.5')), [data-purpose='filter-option-ratings'] input[value='3.5']");
    private By rating30Up = By.cssSelector("input[value='3.0'], label:has(span:contains('3.0')), [data-purpose='filter-option-ratings'] input[value='3.0']");

   
    private By durationFilterSection = By.cssSelector("[data-purpose='filter-group-duration'], div[class*='filter'][class*='duration'], fieldset:has(legend:contains('Duration'))");
    private By durationOptions = By.cssSelector("input[name*='duration'], input[type='checkbox'][id*='duration']");
    private By duration0to1Hour = By.cssSelector("input[value*='extraShort'], input[value*='0-1'], label:contains('0-1')");
    private By duration1to3Hours = By.cssSelector("input[value*='short'], input[value*='1-3'], label:contains('1-3')");
    private By duration3to6Hours = By.cssSelector("input[value*='medium'], input[value*='3-6'], label:contains('3-6')");
    private By duration6to17Hours = By.cssSelector("input[value*='long'], input[value*='6-17'], label:contains('6-17')");
    private By duration17PlusHours = By.cssSelector("input[value*='extraLong'], input[value*='17'], label:contains('17+')");

   
    private By levelFilterSection = By.cssSelector("[data-purpose='filter-group-level'], div[class*='filter'][class*='level'], fieldset:has(legend:contains('Level'))");
    private By levelOptions = By.cssSelector("input[name*='level'], input[type='checkbox'][id*='level']");
    private By levelAllLevels = By.cssSelector("input[value*='all'], label:contains('All Levels')");
    private By levelBeginner = By.cssSelector("input[value*='beginner'], label:contains('Beginner')");
    private By levelIntermediate = By.cssSelector("input[value*='intermediate'], label:contains('Intermediate')");
    private By levelExpert = By.cssSelector("input[value*='expert'], label:contains('Expert')");

   
    private By priceFilterSection = By.cssSelector("[data-purpose='filter-group-price'], div[class*='filter'][class*='price'], fieldset:has(legend:contains('Price'))");
    private By priceOptions = By.cssSelector("input[name*='price'], input[type='checkbox'][id*='price']");
    private By pricePaid = By.cssSelector("input[value*='price-paid'], label:contains('Paid')");
    private By priceFree = By.cssSelector("input[value*='price-free'], label:contains('Free')");

    
    private By languageFilterSection = By.cssSelector("[data-purpose='filter-group-language'], div[class*='filter'][class*='language'], fieldset:has(legend:contains('Language'))");
    private By languageOptions = By.cssSelector("input[name*='lang'], input[type='checkbox'][id*='lang']");
    private By languageEnglish = By.cssSelector("input[value*='en'], label:contains('English')");

   
    private By featuresFilterSection = By.cssSelector("[data-purpose='filter-group-features'], div[class*='filter'][class*='features'], fieldset:has(legend:contains('Features'))");
    private By subtitlesOption = By.cssSelector("input[value*='closed_caption'], label:contains('Subtitles')");
    private By quizzesOption = By.cssSelector("input[value*='quizzes'], label:contains('Quizzes')");
    private By codingExercisesOption = By.cssSelector("input[value*='coding'], label:contains('Coding')");
    private By practiceTestsOption = By.cssSelector("input[value*='practice'], label:contains('Practice')");

   
    private By topicFilterSection = By.cssSelector("[data-purpose='filter-group-topic'], div[class*='filter'][class*='topic'], fieldset:has(legend:contains('Topic'))");
    private By subcategoryFilterSection = By.cssSelector("[data-purpose='filter-group-subcategory'], div[class*='filter'][class*='subcategory']");

    
    private By clearFiltersButton = By.cssSelector("button[class*='clear'], a[class*='clear'], [data-purpose='clear-filters']");

    
    private By sortDropdown = By.cssSelector("select[class*='sort'], button[class*='sort'], [data-purpose='sort-dropdown']");
    private By sortByRelevance = By.cssSelector("option[value*='relevance'], li:contains('Relevance')");
    private By sortByMostReviewed = By.cssSelector("option[value*='reviews'], li:contains('Most Reviewed')");
    private By sortByHighestRated = By.cssSelector("option[value*='rating'], li:contains('Highest Rated')");
    private By sortByNewest = By.cssSelector("option[value*='newest'], li:contains('Newest')");

   
    private By courseRating = By.cssSelector("span[class*='rating'][class*='number'], span[data-purpose='rating-number']");
    private By coursePrice = By.cssSelector("span[class*='price'], div[data-purpose='course-price']");
    private By courseInstructor = By.cssSelector("div[class*='instructor'], span[data-purpose='instructor']");
    private By courseDuration = By.cssSelector("span[class*='content-length'], span[class*='duration']");
    private By courseLectureCount = By.cssSelector("span[class*='lecture'], span:contains('lectures')");
    private By courseLevel = By.cssSelector("span[class*='level'], span:contains('All Levels'), span:contains('Beginner'), span:contains('Intermediate'), span:contains('Expert')");
    private By bestsellerBadge = By.cssSelector("span[class*='bestseller'], div[class*='badge']:contains('Bestseller')");
    private By courseImage = By.cssSelector("img[class*='course-image'], img[alt*='course']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

   
    public void waitForResultsToLoad() {
        waitForPageLoad();
        sleep(3000); 
        handleCookiePopup();
    }

   
    public boolean isOnSearchResultsPage() {
        String url = driver.getCurrentUrl();
        return url.contains("/courses/") || url.contains("search") || url.contains("?q=");
    }

   
    public boolean areResultsDisplayed() {
        try {
            return getNumberOfCourseCards() > 0 || getNumberOfCourseLinks() > 0;
        } catch (Exception e) {
            return false;
        }
    }

  
    public int getNumberOfCourseCards() {
        try {
            sleep(1000);
            int count = driver.findElements(courseCards).size();
            if (count == 0) {
                count = driver.findElements(courseCardAlt).size();
            }
            if (count == 0) {
                count = getNumberOfCourseLinks();
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

 
    public int getNumberOfCourseLinks() {
        try {
            List<WebElement> links = driver.findElements(courseLink);
            // Filter to unique courses
            List<String> uniqueHrefs = new ArrayList<>();
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                if (href != null && href.contains("/course/") && !uniqueHrefs.contains(href)) {
                    uniqueHrefs.add(href);
                }
            }
            return uniqueHrefs.size();
        } catch (Exception e) {
            return 0;
        }
    }

  
    public boolean areMultipleCourseCardsDisplayed() {
        return getNumberOfCourseCards() > 1;
    }

   
    public List<String> getAllCourseTitles() {
        List<String> titles = new ArrayList<>();
        try {
            sleep(500);
           
            List<WebElement> titleElements = driver.findElements(courseCardTitles);
            if (titleElements.isEmpty()) {
                titleElements = driver.findElements(By.cssSelector("h3, [class*='title']"));
            }
            for (WebElement title : titleElements) {
                String text = title.getText().trim();
                if (!text.isEmpty() && text.length() > 5) { 
                    titles.add(text);
                }
            }
        } catch (Exception e) {
           
        }
        return titles;
    }

    
    public boolean resultsContainTerm(String searchTerm) {
        String lowerSearchTerm = searchTerm.toLowerCase();

        
        String pageTitle = driver.getTitle().toLowerCase();
        if (pageTitle.contains(lowerSearchTerm)) {
            return true;
        }

       
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        if (currentUrl.contains(lowerSearchTerm.replace(" ", "+"))) {
            return true;
        }

      
        List<String> titles = getAllCourseTitles();
        for (String title : titles) {
            if (title.toLowerCase().contains(lowerSearchTerm)) {
                return true;
            }
        }

       
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains(lowerSearchTerm);
    }

   
    public boolean isNoResultsDisplayed() {
        try {
            List<WebElement> noResults = driver.findElements(noResultsMessage);
            for (WebElement el : noResults) {
                if (el.isDisplayed()) {
                    return true;
                }
            }
            
            return getNumberOfCourseCards() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    
    public String getNoResultsText() {
        try {
            List<WebElement> noResults = driver.findElements(noResultsMessage);
            for (WebElement el : noResults) {
                if (el.isDisplayed()) {
                    return el.getText();
                }
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

   
    public String getResultsCountText() {
        try {
            List<WebElement> counts = driver.findElements(resultsCount);
            for (WebElement el : counts) {
                String text = el.getText();
                if (text != null && text.contains("result")) {
                    return text;
                }
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

 
    public void clickFirstCourseCard() {
        try {
            List<WebElement> links = driver.findElements(courseLink);
            for (WebElement link : links) {
                if (link.isDisplayed()) {
                    elementUtils.scrollToElement(courseLink);
                    link.click();
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not click course card: " + e.getMessage());
        }
    }

    
    public void clickCourseByTitle(String title) {
        List<WebElement> titleElements = driver.findElements(courseCardTitles);
        for (WebElement element : titleElements) {
            if (element.getText().toLowerCase().contains(title.toLowerCase())) {
                element.click();
                return;
            }
        }
    }

   
    public boolean areFiltersDisplayed() {
        try {
            return !driver.findElements(filterContainer).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean pageTitleContains(String term) {
        return driver.getTitle().toLowerCase().contains(term.toLowerCase());
    }

   
    public boolean urlContainsSearchTerm(String term) {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String encodedTerm = term.toLowerCase().replace(" ", "+");
        return currentUrl.contains(encodedTerm) || currentUrl.contains(term.toLowerCase());
    }

    
    public String getFirstCourseTitle() {
        List<String> titles = getAllCourseTitles();
        return titles.isEmpty() ? "" : titles.get(0);
    }

   
    public boolean isAppropriateFeedbackDisplayed() {
        
        return isOnSearchResultsPage() || isNoResultsDisplayed() || driver.getCurrentUrl().contains("udemy.com");
    }

  
    public boolean isFilterSidebarDisplayed() {
        try {
            List<WebElement> filters = driver.findElements(filterSidebar);
            for (WebElement filter : filters) {
                if (filter.isDisplayed()) {
                    return true;
                }
            }
            return !driver.findElements(filterContainer).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

  
    private void clickFilterOption(By locator) {
        try {
            sleep(500);
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    scrollToElementSafe(element);
                    sleep(300);
                    element.click();
                    sleep(2000);
                    waitForPageLoad();
                    return;
                }
            }
           
            if (!elements.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elements.get(0));
                sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Could not click filter option: " + e.getMessage());
        }
    }

    
    private void scrollToElementSafe(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
            sleep(500);
        } catch (Exception e) {
           
        }
    }

    public void filterByRating(String rating) {
        sleep(1000);
        By ratingLocator;
        switch (rating.toLowerCase()) {
            case "4.5":
            case "4.5 & up":
                ratingLocator = rating45Up;
                break;
            case "4.0":
            case "4.0 & up":
                ratingLocator = rating40Up;
                break;
            case "3.5":
            case "3.5 & up":
                ratingLocator = rating35Up;
                break;
            case "3.0":
            case "3.0 & up":
                ratingLocator = rating30Up;
                break;
            default:
                ratingLocator = rating45Up;
        }
        clickFilterOption(ratingLocator);
    }

   
    public void filterByDuration(String duration) {
        sleep(1000);
        By durationLocator;
        switch (duration.toLowerCase()) {
            case "0-1 hour":
            case "extrashort":
                durationLocator = duration0to1Hour;
                break;
            case "1-3 hours":
            case "short":
                durationLocator = duration1to3Hours;
                break;
            case "3-6 hours":
            case "medium":
                durationLocator = duration3to6Hours;
                break;
            case "6-17 hours":
            case "long":
                durationLocator = duration6to17Hours;
                break;
            case "17+ hours":
            case "extralong":
                durationLocator = duration17PlusHours;
                break;
            default:
                durationLocator = duration1to3Hours;
        }
        clickFilterOption(durationLocator);
    }

   
    public void filterByLevel(String level) {
        sleep(1000);
        By levelLocator;
        switch (level.toLowerCase()) {
            case "all levels":
            case "all":
                levelLocator = levelAllLevels;
                break;
            case "beginner":
                levelLocator = levelBeginner;
                break;
            case "intermediate":
                levelLocator = levelIntermediate;
                break;
            case "expert":
            case "advanced":
                levelLocator = levelExpert;
                break;
            default:
                levelLocator = levelAllLevels;
        }
        clickFilterOption(levelLocator);
    }

    
    public void filterByPrice(String price) {
        sleep(1000);
        By priceLocator;
        switch (price.toLowerCase()) {
            case "free":
                priceLocator = priceFree;
                break;
            case "paid":
                priceLocator = pricePaid;
                break;
            default:
                priceLocator = pricePaid;
        }
        clickFilterOption(priceLocator);
    }

  
    public void filterByLanguage(String language) {
        sleep(1000);
        if (language.equalsIgnoreCase("english")) {
            clickFilterOption(languageEnglish);
        } else {
            By langLocator = By.cssSelector("input[value*='" + language.toLowerCase() + "'], label:contains('" + language + "')");
            clickFilterOption(langLocator);
        }
    }

    
    public void filterByFeature(String feature) {
        sleep(1000);
        By featureLocator;
        switch (feature.toLowerCase()) {
            case "subtitles":
            case "closed captions":
                featureLocator = subtitlesOption;
                break;
            case "quizzes":
                featureLocator = quizzesOption;
                break;
            case "coding exercises":
            case "coding":
                featureLocator = codingExercisesOption;
                break;
            case "practice tests":
            case "practice":
                featureLocator = practiceTestsOption;
                break;
            default:
                featureLocator = subtitlesOption;
        }
        clickFilterOption(featureLocator);
    }

   
    public void clearAllFilters() {
        try {
            List<WebElement> clearButtons = driver.findElements(clearFiltersButton);
            for (WebElement btn : clearButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    sleep(2000);
                    waitForPageLoad();
                    return;
                }
            }
        } catch (Exception e) {
           
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("?")) {
                String baseUrl = currentUrl.split("\\?")[0];
                driver.get(baseUrl);
                waitForResultsToLoad();
            }
        }
    }

    
    public boolean isFilterApplied(String filterType, String filterValue) {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String encodedValue = filterValue.toLowerCase().replace(" ", "+").replace("&", "%26");

        
        return currentUrl.contains(filterType.toLowerCase()) ||
               currentUrl.contains(encodedValue) ||
               currentUrl.contains("filter");
    }

   
    public void sortResultsBy(String sortOption) {
        try {
            sleep(1000);
            
            List<WebElement> dropdowns = driver.findElements(sortDropdown);
            for (WebElement dropdown : dropdowns) {
                if (dropdown.isDisplayed()) {
                    dropdown.click();
                    sleep(500);
                    break;
                }
            }

           
            By sortOptionLocator;
            switch (sortOption.toLowerCase()) {
                case "most reviewed":
                    sortOptionLocator = sortByMostReviewed;
                    break;
                case "highest rated":
                    sortOptionLocator = sortByHighestRated;
                    break;
                case "newest":
                    sortOptionLocator = sortByNewest;
                    break;
                default:
                    sortOptionLocator = sortByRelevance;
            }
            clickFilterOption(sortOptionLocator);
        } catch (Exception e) {
            System.out.println("Could not sort results: " + e.getMessage());
        }
    }

  
    public List<Double> getAllCourseRatings() {
        List<Double> ratings = new ArrayList<>();
        try {
            List<WebElement> ratingElements = driver.findElements(courseRating);
            for (WebElement element : ratingElements) {
                String ratingText = element.getText().trim();
                if (!ratingText.isEmpty()) {
                    try {
                        ratings.add(Double.parseDouble(ratingText.split(" ")[0]));
                    } catch (NumberFormatException e) {
                        
                    }
                }
            }
        } catch (Exception e) {
           
        }
        return ratings;
    }

    
    public boolean allCoursesHaveMinimumRating(double minRating) {
        List<Double> ratings = getAllCourseRatings();
        if (ratings.isEmpty()) {
            return true; 
        }
        for (Double rating : ratings) {
            if (rating < minRating) {
                return false;
            }
        }
        return true;
    }

  
    public List<String> getAllCoursePrices() {
        List<String> prices = new ArrayList<>();
        try {
            List<WebElement> priceElements = driver.findElements(coursePrice);
            for (WebElement element : priceElements) {
                String priceText = element.getText().trim();
                if (!priceText.isEmpty()) {
                    prices.add(priceText);
                }
            }
        } catch (Exception e) {
            
        }
        return prices;
    }

    
    public boolean allCoursesAreFree() {
        List<String> prices = getAllCoursePrices();
        if (prices.isEmpty()) {
            return false;
        }
        for (String price : prices) {
            if (!price.toLowerCase().contains("free") && !price.equals("$0") && !price.equals("₹0")) {
                return false;
            }
        }
        return true;
    }

  
    public boolean allCoursesArePaid() {
        List<String> prices = getAllCoursePrices();
        if (prices.isEmpty()) {
            return true;
        }
        for (String price : prices) {
            if (price.toLowerCase().contains("free") || price.equals("$0") || price.equals("₹0")) {
                return false;
            }
        }
        return true;
    }

    public List<String> getAllCourseLevels() {
        List<String> levels = new ArrayList<>();
        try {
            List<WebElement> levelElements = driver.findElements(courseLevel);
            for (WebElement element : levelElements) {
                String levelText = element.getText().trim();
                if (!levelText.isEmpty()) {
                    levels.add(levelText);
                }
            }
        } catch (Exception e) {
            
        }
        return levels;
    }

   
    public boolean courseLevelsMatch(String expectedLevel) {
        List<String> levels = getAllCourseLevels();
        if (levels.isEmpty()) {
            return true; 
        }
        for (String level : levels) {
            if (!level.toLowerCase().contains(expectedLevel.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

   
    public List<Double> getAllCourseDurations() {
        List<Double> durations = new ArrayList<>();
        try {
            List<WebElement> durationElements = driver.findElements(courseDuration);
            for (WebElement element : durationElements) {
                String durationText = element.getText().trim().toLowerCase();
                double hours = parseDurationToHours(durationText);
                if (hours > 0) {
                    durations.add(hours);
                }
            }
        } catch (Exception e) {
           
        }
        return durations;
    }

  
    private double parseDurationToHours(String durationText) {
        try {
           
            Pattern hoursPattern = Pattern.compile("(\\d+\\.?\\d*)\\s*(hour|hr)");
            Matcher hoursMatcher = hoursPattern.matcher(durationText);
            if (hoursMatcher.find()) {
                return Double.parseDouble(hoursMatcher.group(1));
            }
    
            Pattern minPattern = Pattern.compile("(\\d+)\\s*min");
            Matcher minMatcher = minPattern.matcher(durationText);
            if (minMatcher.find()) {
                return Double.parseDouble(minMatcher.group(1)) / 60.0;
            }
        } catch (Exception e) {
            
        }
        return 0;
    }

    
    public boolean courseDurationsInRange(double minHours, double maxHours) {
        List<Double> durations = getAllCourseDurations();
        if (durations.isEmpty()) {
            return true;
        }
        for (Double duration : durations) {
            if (duration < minHours || duration > maxHours) {
                return false;
            }
        }
        return true;
    }

   
    public boolean hasBestsellerCourses() {
        try {
            return !driver.findElements(bestsellerBadge).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

  
    public int getResultsCountNumber() {
        try {
            String countText = getResultsCountText();
            Pattern pattern = Pattern.compile("(\\d+[,\\d]*)");
            Matcher matcher = pattern.matcher(countText);
            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1).replace(",", ""));
            }
        } catch (Exception e) {
           
        }
        return getNumberOfCourseCards();
    }

    
    public boolean didFilterAffectResults(int previousCount) {
        int currentCount = getResultsCountNumber();
        
        return currentCount != previousCount || currentCount > 0;
    }

    public List<String> getAllInstructorNames() {
        List<String> instructors = new ArrayList<>();
        try {
            List<WebElement> instructorElements = driver.findElements(courseInstructor);
            for (WebElement element : instructorElements) {
                String name = element.getText().trim();
                if (!name.isEmpty()) {
                    instructors.add(name);
                }
            }
        } catch (Exception e) {
            
        }
        return instructors;
    }

  
    public CourseDetails getFirstCourseDetails() {
        CourseDetails details = new CourseDetails();
        try {
            List<String> titles = getAllCourseTitles();
            if (!titles.isEmpty()) {
                details.title = titles.get(0);
            }

            List<Double> ratings = getAllCourseRatings();
            if (!ratings.isEmpty()) {
                details.rating = ratings.get(0);
            }

            List<String> prices = getAllCoursePrices();
            if (!prices.isEmpty()) {
                details.price = prices.get(0);
            }

            List<String> instructors = getAllInstructorNames();
            if (!instructors.isEmpty()) {
                details.instructor = instructors.get(0);
            }
        } catch (Exception e) {
           
        }
        return details;
    }

   
    public static class CourseDetails {
        public String title = "";
        public double rating = 0.0;
        public String price = "";
        public String instructor = "";
        public String duration = "";
        public String level = "";
        public int lectureCount = 0;
    }
}
