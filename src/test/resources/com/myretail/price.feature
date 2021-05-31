Feature: Test price API
  Scenario: Create and get price
    When I create price with currency INR and value 1000
    Then the price should have currency INR and value 1000