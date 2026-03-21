package com.udemy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CourseDetailsPage - Udemy Course Details Page Object.
 *
 * This page represents the individual course detail page on Udemy.
 * Based on actual Udemy page structure observed from screenshots.
 *
 * Key Elements Identified:
 * - Course Title: Main h1 heading at the top
 * - Rating: Numeric rating (e.g., "4.7") with star icons
 * - Instructor: "Created by [Instructor Name]" link
 * - Badges: Bestseller, Hot & New, etc.
 * - Price, Duration, Last Updated info
 */
public class CourseDetailsPage extends BasePage {

    // =====================================================================
    // COURSE TITLE LOCATORS
    // XPath: Targets the main h1 heading containing course title
    // Example: "100 Days of Code™: The Complete Python Pro Bootcamp"
    // =====================================================================
    private By courseTitleHeading = By.xpath("//h1[@data-purpose='lead-title']");
    private By courseTitleAlt = By.xpath("//div[contains(@class,'clp-lead')]//h1");
    private By courseTitleCss = By.cssSelector("h1[data-purpose='lead-title'], div.clp-lead__title h1, h1.clp-lead__title");

    // =====================================================================
    // COURSE RATING LOCATORS
    // XPath: Targets the numeric rating value (e.g., "4.7")
    // Located in the course info section with star icons
    // =====================================================================
    private By courseRatingNumber = By.xpath("//span[@data-purpose='rating-number']");
    private By courseRatingAlt = By.xpath("//div[contains(@class,'star-rating')]//span[contains(text(),'.')]");
    private By courseRatingCss = By.cssSelector("span[data-purpose='rating-number'], span.star-rating-numeric");

    // Star rating container
    private By starRatingContainer = By.xpath("//div[contains(@class,'star-rating-module')]");
    private By starRatingCss = By.cssSelector("div[class*='star-rating'], span[class*='star-rating']");

    // Rating count (e.g., "416,906 ratings")
    private By ratingCountText = By.xpath("//span[contains(text(),'ratings') or contains(text(),'rating')]");
    private By ratingCountLink = By.xpath("//a[contains(text(),'ratings')]");

    // =====================================================================
    // INSTRUCTOR NAME LOCATORS
    // XPath: Targets the instructor name link in "Created by" section
    // Example: "Dr. Angela Yu, Developer and Lead Instructor"
    // =====================================================================
    private By instructorNameLink = By.xpath("//div[contains(text(),'Created by')]/following-sibling::div//a | //span[contains(text(),'Created by')]/following-sibling::a");
    private By instructorNameAlt = By.xpath("//a[contains(@href,'/user/') and @data-purpose='instructor-name']");
    private By instructorNameCss = By.cssSelector("a[data-purpose='instructor-name'], a[href*='/user/'][class*='instructor']");

    // "Created by" section container
    private By createdBySection = By.xpath("//div[contains(text(),'Created by')] | //span[contains(text(),'Created by')]");

    // =====================================================================
    // COURSE BADGES LOCATORS
    // XPath: Targets badges like "Bestseller", "Hot & New", "Highest Rated"
    // =====================================================================
    private By bestsellerBadge = By.xpath("//div[contains(@class,'badge-bestseller')] | //span[contains(text(),'Bestseller')]");
    private By bestsellerBadgeCss = By.cssSelector("div[class*='bestseller'], span[class*='bestseller'], div[data-purpose='badge-bestseller']");

    private By hotAndNewBadge = By.xpath("//div[contains(@class,'badge-hot')] | //span[contains(text(),'Hot')]");
    private By highestRatedBadge = By.xpath("//div[contains(@class,'highest-rated')] | //span[contains(text(),'Highest Rated')]");

    // =====================================================================
    // COURSE METADATA LOCATORS
    // XPath: Last updated, Language, Subtitles info
    // =====================================================================
    private By lastUpdatedText = By.xpath("//span[contains(text(),'Last updated')] | //div[contains(text(),'Last updated')]");
    private By courseLanguage = By.xpath("//span[contains(@data-purpose,'language')] | //div[contains(@class,'clp-lead__element-item')][contains(.,'English')]");
    private By subtitlesInfo = By.xpath("//span[contains(text(),'Auto')] | //div[contains(text(),'subtitles')]");

    // =====================================================================
    // LEARNERS COUNT LOCATORS
    // XPath: Number of enrolled students (e.g., "1,754,758 learners")
    // =====================================================================
    private By learnersCount = By.xpath("//div[contains(text(),'learners')] | //span[contains(text(),'students')]");
    private By learnersCountCss = By.cssSelector("div[data-purpose='enrollment'], span[class*='enrollment']");

    // =====================================================================
    // COURSE DESCRIPTION/SUBTITLE LOCATORS
    // XPath: Course subtitle/description below the main title
    // =====================================================================
    private By courseSubtitle = By.xpath("//div[@data-purpose='lead-headline'] | //p[contains(@class,'clp-lead__headline')]");
    private By courseSubtitleCss = By.cssSelector("div[data-purpose='lead-headline'], p.clp-lead__headline");

    // =====================================================================
    // PRICE LOCATORS
    // =====================================================================
    private By coursePrice = By.xpath("//div[@data-purpose='course-price']//span | //span[contains(@class,'price')]");
    private By discountedPrice = By.xpath("//div[contains(@class,'discount')] | //span[contains(@class,'original-price')]");

    // =====================================================================
    // ACTION BUTTONS
    // =====================================================================
    private By addToCartButton = By.xpath("//button[@data-purpose='add-to-cart'] | //button[contains(text(),'Add to cart')]");
    private By buyNowButton = By.xpath("//button[@data-purpose='buy-this-course-button'] | //button[contains(text(),'Buy now')]");
    private By startSubscriptionButton = By.xpath("//button[contains(text(),'Start subscription')]");

    // =====================================================================
    // COURSE CONTENT SECTIONS
    // =====================================================================
    private By whatYoullLearnSection = By.xpath("//h2[contains(text(),\"What you'll learn\")] | //div[@data-purpose='what-you-ll-learn']");
    private By courseContentSection = By.xpath("//h2[contains(text(),'Course content')] | //div[@data-purpose='curriculum']");
    private By requirementsSection = By.xpath("//h2[contains(text(),'Requirements')] | //div[@data-purpose='requirements']");

    public CourseDetailsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Helper method to pause execution briefly.
     * Useful for waiting for dynamic content to load.
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Wait for course details page to fully load.
     * Waits for page load and gives time for dynamic content.
     */
    public void waitForPageToLoad() {
        waitForPageLoad();
        sleep(3000); // Wait for dynamic content
        handleCookiePopup();
        handleLocationPopup();
    }

    /**
     * Verify if we are on a course details page.
     * Checks URL pattern for "/course/" path.
     *
     * @return true if URL contains "/course/"
     */
    public boolean isOnCourseDetailsPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("/course/");
    }

    // =====================================================================
    // COURSE TITLE METHODS
    // =====================================================================

    /**
     * Get the course title text.
     * Tries multiple locator strategies for robustness.
     *
     * @return Course title string, or empty string if not found
     */
    public String getCourseTitle() {
        try {
            // Try primary XPath locator
            List<WebElement> titles = driver.findElements(courseTitleHeading);
            if (!titles.isEmpty() && titles.get(0).isDisplayed()) {
                return titles.get(0).getText().trim();
            }

            // Try alternative XPath
            titles = driver.findElements(courseTitleAlt);
            if (!titles.isEmpty() && titles.get(0).isDisplayed()) {
                return titles.get(0).getText().trim();
            }

            // Try CSS selector
            titles = driver.findElements(courseTitleCss);
            if (!titles.isEmpty() && titles.get(0).isDisplayed()) {
                return titles.get(0).getText().trim();
            }

            return "";
        } catch (Exception e) {
            System.out.println("Error getting course title: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if course title is displayed on the page.
     *
     * @return true if title element exists and is visible
     */
    public boolean isCourseTitleDisplayed() {
        try {
            String title = getCourseTitle();
            return title != null && !title.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // =====================================================================
    // COURSE RATING METHODS
    // =====================================================================

    /**
     * Get the course rating value (e.g., "4.7").
     *
     * @return Rating as string, or empty string if not found
     */
    public String getCourseRating() {
        try {
            // Try primary XPath
            List<WebElement> ratings = driver.findElements(courseRatingNumber);
            if (!ratings.isEmpty()) {
                String rating = ratings.get(0).getText().trim();
                if (!rating.isEmpty()) return rating;
            }

            // Try alternative XPath
            ratings = driver.findElements(courseRatingAlt);
            if (!ratings.isEmpty()) {
                return ratings.get(0).getText().trim();
            }

            // Try CSS selector
            ratings = driver.findElements(courseRatingCss);
            if (!ratings.isEmpty()) {
                return ratings.get(0).getText().trim();
            }

            // Try to find any element with rating pattern (e.g., "4.7")
            List<WebElement> allSpans = driver.findElements(By.xpath("//span[contains(text(),'.') and string-length(text()) <= 3]"));
            for (WebElement span : allSpans) {
                String text = span.getText().trim();
                if (text.matches("\\d\\.\\d")) {
                    return text;
                }
            }

            return "";
        } catch (Exception e) {
            System.out.println("Error getting course rating: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if course rating is displayed.
     *
     * @return true if rating element exists and has valid rating value
     */
    public boolean isRatingDisplayed() {
        try {
            String rating = getCourseRating();
            if (rating != null && !rating.isEmpty()) {
                return true;
            }

            // Also check for star rating container
            List<WebElement> stars = driver.findElements(starRatingCss);
            return !stars.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the total number of ratings (e.g., "416,906").
     *
     * @return Number of ratings as string
     */
    public String getRatingCount() {
        try {
            List<WebElement> counts = driver.findElements(ratingCountText);
            if (!counts.isEmpty()) {
                return counts.get(0).getText().trim();
            }

            counts = driver.findElements(ratingCountLink);
            if (!counts.isEmpty()) {
                return counts.get(0).getText().trim();
            }

            return "";
        } catch (Exception e) {
            return "";
        }
    }

    // =====================================================================
    // INSTRUCTOR NAME METHODS
    // =====================================================================

    /**
     * Get the instructor name.
     * Looks for the name in "Created by" section.
     *
     * @return Instructor name string, or empty string if not found
     */
    public String getInstructorName() {
        try {
            // Try primary XPath - instructor link
            List<WebElement> instructors = driver.findElements(instructorNameLink);
            if (!instructors.isEmpty()) {
                String name = instructors.get(0).getText().trim();
                if (!name.isEmpty()) return name;
            }

            // Try alternative XPath
            instructors = driver.findElements(instructorNameAlt);
            if (!instructors.isEmpty()) {
                return instructors.get(0).getText().trim();
            }

            // Try CSS selector
            instructors = driver.findElements(instructorNameCss);
            if (!instructors.isEmpty()) {
                return instructors.get(0).getText().trim();
            }

            // Try to find any link in the instructor area
            List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'/user/')]"));
            for (WebElement link : links) {
                String text = link.getText().trim();
                if (!text.isEmpty() && !text.contains("Udemy")) {
                    return text;
                }
            }

            return "";
        } catch (Exception e) {
            System.out.println("Error getting instructor name: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if instructor name is displayed.
     *
     * @return true if instructor name element exists and is visible
     */
    public boolean isInstructorDisplayed() {
        try {
            String instructor = getInstructorName();
            return instructor != null && !instructor.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // =====================================================================
    // BADGE METHODS
    // =====================================================================

    /**
     * Check if course has Bestseller badge.
     *
     * @return true if Bestseller badge is displayed
     */
    public boolean hasBestsellerBadge() {
        try {
            List<WebElement> badges = driver.findElements(bestsellerBadge);
            if (!badges.isEmpty()) {
                return badges.get(0).isDisplayed();
            }

            badges = driver.findElements(bestsellerBadgeCss);
            return !badges.isEmpty() && badges.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if course has Hot & New badge.
     *
     * @return true if Hot & New badge is displayed
     */
    public boolean hasHotAndNewBadge() {
        try {
            List<WebElement> badges = driver.findElements(hotAndNewBadge);
            return !badges.isEmpty() && badges.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // =====================================================================
    // ADDITIONAL INFO METHODS
    // =====================================================================

    /**
     * Get number of enrolled learners/students.
     *
     * @return Learners count string
     */
    public String getLearnersCount() {
        try {
            List<WebElement> counts = driver.findElements(learnersCount);
            if (!counts.isEmpty()) {
                return counts.get(0).getText().trim();
            }

            counts = driver.findElements(learnersCountCss);
            if (!counts.isEmpty()) {
                return counts.get(0).getText().trim();
            }

            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get course subtitle/description.
     *
     * @return Course subtitle string
     */
    public String getCourseSubtitle() {
        try {
            List<WebElement> subtitles = driver.findElements(courseSubtitle);
            if (!subtitles.isEmpty()) {
                return subtitles.get(0).getText().trim();
            }

            subtitles = driver.findElements(courseSubtitleCss);
            if (!subtitles.isEmpty()) {
                return subtitles.get(0).getText().trim();
            }

            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get course price.
     *
     * @return Course price string
     */
    public String getCoursePrice() {
        try {
            List<WebElement> prices = driver.findElements(coursePrice);
            if (!prices.isEmpty()) {
                return prices.get(0).getText().trim();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    // =====================================================================
    // VALIDATION METHODS
    // =====================================================================

    /**
     * Validate all key course details are displayed.
     * Checks: Title, Rating, and Instructor Name
     *
     * @return true if all three key elements are displayed
     */
    public boolean areAllKeyDetailsDisplayed() {
        boolean titleDisplayed = isCourseTitleDisplayed();
        boolean ratingDisplayed = isRatingDisplayed();
        boolean instructorDisplayed = isInstructorDisplayed();

        System.out.println("Course Title Displayed: " + titleDisplayed);
        System.out.println("Rating Displayed: " + ratingDisplayed);
        System.out.println("Instructor Displayed: " + instructorDisplayed);

        return titleDisplayed && ratingDisplayed && instructorDisplayed;
    }

    /**
     * Get a summary of all course details.
     * Useful for debugging and logging.
     *
     * @return Formatted string with all course details
     */
    public String getCourseDetailsSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("\n========== COURSE DETAILS ==========\n");
        summary.append("Title: ").append(getCourseTitle()).append("\n");
        summary.append("Rating: ").append(getCourseRating()).append("\n");
        summary.append("Rating Count: ").append(getRatingCount()).append("\n");
        summary.append("Instructor: ").append(getInstructorName()).append("\n");
        summary.append("Learners: ").append(getLearnersCount()).append("\n");
        summary.append("Subtitle: ").append(getCourseSubtitle()).append("\n");
        summary.append("Bestseller: ").append(hasBestsellerBadge()).append("\n");
        summary.append("Price: ").append(getCoursePrice()).append("\n");
        summary.append("====================================\n");
        return summary.toString();
    }

    /**
     * Verify course title contains expected text.
     *
     * @param expectedText Text expected in title
     * @return true if title contains expected text (case-insensitive)
     */
    public boolean courseTitleContains(String expectedText) {
        String title = getCourseTitle().toLowerCase();
        return title.contains(expectedText.toLowerCase());
    }

    /**
     * Verify instructor name contains expected text.
     *
     * @param expectedText Text expected in instructor name
     * @return true if instructor name contains expected text
     */
    public boolean instructorNameContains(String expectedText) {
        String instructor = getInstructorName().toLowerCase();
        return instructor.contains(expectedText.toLowerCase());
    }
}
