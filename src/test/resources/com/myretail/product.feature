Feature: Test product API
  Scenario: Create, update and get product
    When I create product with name Oneplus phone, currency USD, value 100
    Then the product should have name Oneplus phone, currency USD, value 100
    When I update product to have currency INR and value 11230.56
    Then the product should have name Oneplus phone, currency INR, value 11230.56

  Scenario: Get non existent product
    When I get a product that doesn't exist, it should thrown an exception

  Scenario: Create a product with invalid payload
    When I create a product with invalid payload, it should thrown an exception