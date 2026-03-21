@FilterValidation @Regression
Feature: Udemy Course Filter Functionality
  As a user on Udemy platform
  I want to filter search results using various filter options
  So that I can find courses that match my specific requirements

  Background:
    Given I am on the Udemy home page
    And I handle any popups that appear
    And I search for "Python" courses

  # =============================================================
  # POSITIVE TEST CASES (3 Test Cases)
  # =============================================================

  @Positive @Smoke @TC007
  Scenario: TC007 - Filter courses by rating 4.5 and above
    When I apply the rating filter "4.5 & up"
    Then the filter should be applied successfully
    And I should see courses with ratings 4.5 or above

  @Positive @TC008
  Scenario: TC008 - Filter courses by price (Free courses)
    When I apply the price filter "Free"
    Then the filter should be applied successfully
    And the displayed courses should be free

  @Positive @TC009
  Scenario Outline: TC009 - Filter courses by skill level
    When I apply the level filter "<level>"
    Then the filter should be applied successfully
    And the results should be filtered accordingly

    Examples:
      | level        |
      | Beginner     |
      | Intermediate |
      | Expert       |

  # =============================================================
  # NEGATIVE TEST CASES (3 Test Cases)
  # =============================================================

  @Negative @TC010
  Scenario: TC010 - Apply multiple conflicting filters should show reduced results
    When I apply the rating filter "4.5 & up"
    And I apply the price filter "Free"
    And I apply the level filter "Expert"
    Then the filter should be applied successfully
    And the page should handle the filters without errors

  @Negative @TC011
  Scenario: TC011 - Clear all filters should restore original results
    When I apply the rating filter "4.5 & up"
    And I apply the price filter "Paid"
    And I clear all filters
    Then the filters should be cleared
    And I should see the original unfiltered results

  @Negative @TC012
  Scenario: TC012 - Apply filter on search with minimal results
    Given I search for "xyzrandomcourse123" courses
    When I attempt to apply filters
    Then the page should handle filters gracefully even with no results
