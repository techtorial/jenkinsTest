@etsy
Feature: Etsy search functionality

  Scenario: Correct price verification
    Given user is on etsy home page
    When user searches for "jewelry"
    Then the result page has total price more than 0