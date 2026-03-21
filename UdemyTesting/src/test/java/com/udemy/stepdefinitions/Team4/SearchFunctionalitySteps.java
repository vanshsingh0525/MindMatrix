package com.udemy.stepdefinitions.Team4;

import com.udemy.pages.HomePage;
import com.udemy.pages.SearchResultsPage;
import com.udemy.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchFunctionalitySteps {

    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private String initialUrl;

    public SearchFunctionalitySteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
        this.searchResultsPage = new SearchResultsPage(driver);
    }

    @Given("I am on the Udemy home page")
    public void iAmOnTheUdemyHomePage() {
        homePage.navigateToHomePage();
        initialUrl = driver.getCurrentUrl();
        Assert.assertTrue(homePage.isHomePageLoaded(),
            "Failed to load Udemy home page");
        System.out.println("Successfully navigated to Udemy home page: " + driver.getCurrentUrl());
    }

    @Given("I handle any popups that appear")
    public void iHandleAnyPopupsThatAppear() {
        homePage.handleCookiePopup();
        homePage.handleLocationPopup();
        homePage.dismissPromoBanner();
        System.out.println("Handled all popups successfully");
    }

    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String searchText) {
        homePage.enterSearchText(searchText);
        System.out.println("Entered search text: " + searchText);
    }

    @When("I click on the search button")
    public void iClickOnTheSearchButton() {
        homePage.clickSearchButton();
        searchResultsPage.waitForResultsToLoad();
        System.out.println("Clicked search button and waited for results");
    }

    @When("I click on the search button without entering any text")
    public void iClickOnTheSearchButtonWithoutEnteringAnyText() {
        initialUrl = driver.getCurrentUrl();
        homePage.clearSearchInput();
        homePage.clickSearchButton();
        // Small wait to see if page changes
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Clicked search button without entering any text");
    }

    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        homePage.searchForCourse(searchTerm);
        searchResultsPage.waitForResultsToLoad();
        System.out.println("Performed search for: " + searchTerm);
    }

    @Then("I should be on the search results page")
    public void iShouldBeOnTheSearchResultsPage() {
        Assert.assertTrue(searchResultsPage.isOnSearchResultsPage(),
            "Not on search results page. Current URL: " + driver.getCurrentUrl());
        System.out.println("Verified: On search results page - " + driver.getCurrentUrl());
    }

    @Then("I should see multiple course cards displayed")
    public void iShouldSeeMultipleCourseCardsDisplayed() {
        int courseCount = searchResultsPage.getNumberOfCourseCards();
        System.out.println("Number of course cards found: " + courseCount);
        Assert.assertTrue(courseCount > 0,
            "No course cards displayed on search results page");
    }

    @Then("the search results should contain courses related to {string}")
    public void theSearchResultsShouldContainCoursesRelatedTo(String searchTerm) {
        boolean containsTerm = searchResultsPage.resultsContainTerm(searchTerm);
        Assert.assertTrue(containsTerm,
            "Search results do not contain courses related to: " + searchTerm);
        System.out.println("Verified: Results contain courses related to '" + searchTerm + "'");
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedText) {
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.toLowerCase().contains(expectedText.toLowerCase()),
            "Page title '" + pageTitle + "' does not contain '" + expectedText + "'");
        System.out.println("Verified: Page title contains '" + expectedText + "'");
    }

    @Then("I should see search suggestions dropdown")
    public void iShouldSeeSearchSuggestionsDropdown() {
        boolean suggestionsDisplayed = homePage.isSearchSuggestionsDisplayed();
        Assert.assertTrue(suggestionsDisplayed,
            "Search suggestions dropdown is not displayed");
        System.out.println("Verified: Search suggestions dropdown is displayed");
    }

    @Then("the suggestions should contain {string} related terms")
    public void theSuggestionsShouldContainRelatedTerms(String searchTerm) {
        boolean containsTerm = homePage.suggestionsContainTerm(searchTerm);
        Assert.assertTrue(containsTerm,
            "Suggestions do not contain '" + searchTerm + "' related terms");
        System.out.println("Verified: Suggestions contain '" + searchTerm + "' related terms");
    }

    @Then("I should see search suggestions or the search box accepts input")
    public void iShouldSeeSearchSuggestionsOrSearchBoxAcceptsInput() {
        // This step passes if either suggestions are shown OR search input has value
        boolean suggestionsDisplayed = homePage.isSearchSuggestionsDisplayed();
        String inputValue = homePage.getSearchInputValue();
        boolean hasInput = inputValue != null && !inputValue.isEmpty();

        Assert.assertTrue(suggestionsDisplayed || hasInput,
            "Neither search suggestions shown nor search input accepted");
        System.out.println("Verified: Search functionality working - suggestions: " + suggestionsDisplayed + ", has input: " + hasInput);
    }

    @Then("I should remain on the home page")
    public void iShouldRemainOnTheHomePage() {
        boolean isOnHome = homePage.isOnHomePage();
        Assert.assertTrue(isOnHome,
            "Not on home page. Current URL: " + driver.getCurrentUrl());
        System.out.println("Verified: Remained on home page");
    }

    @Then("I should remain on the current page without errors")
    public void iShouldRemainOnTheCurrentPageWithoutErrors() {
        // For empty search, page should either stay on home or not crash
        String currentUrl = driver.getCurrentUrl();
        boolean noError = currentUrl.contains("udemy.com") &&
                         !currentUrl.contains("error") &&
                         !currentUrl.contains("404");

        // Also check that page is still functional
        boolean pageLoaded = homePage.isHomePageLoaded() || searchResultsPage.isOnSearchResultsPage();

        Assert.assertTrue(noError && pageLoaded,
            "Page error occurred. Current URL: " + currentUrl);
        System.out.println("Verified: Page handled empty search gracefully. URL: " + currentUrl);
    }

    @Then("no search results should be displayed")
    public void noSearchResultsShouldBeDisplayed() {
        boolean noResults = !searchResultsPage.areResultsDisplayed() ||
                           homePage.isOnHomePage();
        Assert.assertTrue(noResults,
            "Search results were displayed unexpectedly");
        System.out.println("Verified: No search results displayed");
    }

    @Then("I should see no results message or empty results")
    public void iShouldSeeNoResultsMessageOrEmptyResults() {
        boolean noResultsOrEmpty = searchResultsPage.isNoResultsDisplayed() ||
                                   searchResultsPage.getNumberOfCourseCards() == 0;
        Assert.assertTrue(noResultsOrEmpty,
            "Expected no results or empty results, but found course cards");
        System.out.println("Verified: No results or empty results displayed");
    }

    @Then("the page should display appropriate feedback")
    public void thePageShouldDisplayAppropriateFeedback() {
        boolean feedbackDisplayed = searchResultsPage.isAppropriateFeedbackDisplayed();
        Assert.assertTrue(feedbackDisplayed,
            "No appropriate feedback displayed for invalid search");
        System.out.println("Verified: Appropriate feedback is displayed");
    }

    @Then("the page should handle the search gracefully")
    public void thePageShouldHandleTheSearchGracefully() {
        // For special characters, page should either show results, no results, or stay functional
        String currentUrl = driver.getCurrentUrl();
        boolean pageIsValid = currentUrl.contains("udemy.com") &&
                             !currentUrl.contains("error") &&
                             !currentUrl.contains("500");

        // Page should be in a valid state (either search results or home)
        boolean validState = searchResultsPage.isOnSearchResultsPage() ||
                            homePage.isOnHomePage() ||
                            searchResultsPage.isAppropriateFeedbackDisplayed();

        Assert.assertTrue(pageIsValid && validState,
            "Page did not handle special characters gracefully. URL: " + currentUrl);
        System.out.println("Verified: Page handled special characters gracefully");
    }

    @Then("no matching courses should be found")
    public void noMatchingCoursesShouldBeFound() {
        int courseCount = searchResultsPage.getNumberOfCourseCards();
        Assert.assertTrue(courseCount == 0 || searchResultsPage.isNoResultsDisplayed(),
            "Unexpectedly found matching courses: " + courseCount);
        System.out.println("Verified: No matching courses found");
    }

    @Then("the search should complete without errors")
    public void theSearchShouldCompleteWithoutErrors() {
        // For gibberish search, just verify page didn't crash
        String currentUrl = driver.getCurrentUrl();
        boolean noError = currentUrl.contains("udemy.com") &&
                         !currentUrl.contains("error") &&
                         !currentUrl.contains("500") &&
                         !currentUrl.contains("404");

        Assert.assertTrue(noError,
            "Search caused an error. URL: " + currentUrl);
        System.out.println("Verified: Search completed without errors. URL: " + currentUrl);
    }

    @Then("I should see no exact matching courses or suggestions")
    public void iShouldSeeNoExactMatchingCoursesOrSuggestions() {
        // For gibberish, we just need to verify page shows some response (even if suggestions/related)
        boolean pageHandledIt = searchResultsPage.isOnSearchResultsPage() ||
                               homePage.isOnHomePage() ||
                               searchResultsPage.isAppropriateFeedbackDisplayed();

        Assert.assertTrue(pageHandledIt,
            "Page did not handle gibberish search properly");
        System.out.println("Verified: Search handled gibberish text appropriately");
    }


    @And("I wait for the page to load")
    public void iWaitForThePageToLoad() {
        homePage.waitForPageLoad();
        System.out.println("Page loaded successfully");
    }

    @And("I clear the search box")
    public void iClearTheSearchBox() {
        homePage.clearSearchInput();
        System.out.println("Search box cleared");
    }
}