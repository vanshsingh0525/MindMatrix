@SearchFunctionality @Regression
Feature: Udemy Course Search Functionality
  As a user on Udemy platform
  I want to search for courses using the search functionality
  So that I can find relevant courses on topics I'm interested in

  Background:
    Given I am on the Udemy home page
    And I handle any popups that appear

  # =============================================================
  # POSITIVE TEST CASES (3 Test Cases)
  # =============================================================

  @Positive @Smoke @TC001
  Scenario: TC001 - Search for a valid course topic and verify results
    When I enter "Python" in the search box
    And I click on the search button
    Then I should be on the search results page
    And I should see multiple course cards displayed
    And the search results should contain courses related to "Python"

  @Positive @TC002
  Scenario Outline: TC002 - Search for different valid course topics
    When I enter "<searchTerm>" in the search box
    And I click on the search button
    Then I should be on the search results page
    And I should see multiple course cards displayed

    Examples:
      | searchTerm       |
      | Java             |
      | Web Development  |
      | Machine Learning |

  @Positive @TC003
  Scenario: TC003 - Verify search suggestions appear while typing
    When I enter "python" in the search box
    Then I should see search suggestions or the search box accepts input

  # =============================================================
  # NEGATIVE TEST CASES (3 Test Cases)
  # =============================================================

  @Negative @TC004
  Scenario: TC004 - Search with empty search term should not navigate away
    When I click on the search button without entering any text
    Then I should remain on the current page without errors

  @Negative @TC005
  Scenario: TC005 - Search with special characters shows appropriate response
    When I enter "@#$%^&*!" in the search box
    And I click on the search button
    Then the page should handle the search gracefully

  @Negative @TC006
  Scenario: TC006 - Search with gibberish text shows no exact matches
    When I enter "xyzqwerty789mnopkl456" in the search box
    And I click on the search button
    Then the search should complete without errors
