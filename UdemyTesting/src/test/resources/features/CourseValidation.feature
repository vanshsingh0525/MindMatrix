@CourseValidation @Regression
Feature: Udemy Course Validation on Results Page
  As a user on Udemy platform
  I want to verify course information displayed on search results
  So that I can make informed decisions about which courses to enroll in

  Background:
    Given I am on the Udemy home page
    And I handle any popups that appear
    And I search for "Java" courses

  # =============================================================
  # POSITIVE TEST CASES (3 Test Cases)
  # =============================================================

  @Positive @Smoke @TC013
  Scenario: TC013 - Verify course cards display essential information
    Then I should see course cards with the following details:
      | detail      |
      | title       |
      | instructor  |
      | rating      |
      | price       |
    And each course card should have an image

  @Positive @TC014
  Scenario: TC014 - Verify course ratings are within valid range
    Then all course ratings should be between 0 and 5
    And courses should display number of reviews

  @Positive @TC015
  Scenario: TC015 - Verify course prices are displayed correctly
    Then course prices should be displayed
    And prices should show currency symbol

  # =============================================================
  # NEGATIVE TEST CASES (3 Test Cases)
  # =============================================================

  @Negative @TC016
  Scenario: TC016 - Verify page handles course cards without complete information
    Given I search for a less popular topic "obscure programming language xyz"
    Then the page should display gracefully even if some course details are missing

  @Negative @TC017
  Scenario: TC017 - Verify sorting maintains course card integrity
    When I sort results by "Highest Rated"
    Then course cards should still display all required information
    And the page should not show any broken elements

  @Negative @TC018
  Scenario: TC018 - Verify pagination or scroll does not break course display
    When I scroll down to load more courses
    Then newly loaded courses should display properly
    And no duplicate courses should appear in the visible area
