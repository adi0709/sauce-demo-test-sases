Feature: Swaglabs Inventory
  As a user
  I want to test login to Swaglabs
  So that I can successfully add products to carts

  Scenario: Login to swaglabs
    Given I am on the swaglabs home page
    When I enter correct username and password
    And I click on Login button
    Then the inventory page is loaded

  Scenario: Add a single product to the cart
    Given I am on the inventory page
    When I choose correct product name Sauce Labs Backpack
    And I click Add To Cart button for Sauce Labs Backpack
    Then product is added to the cart

  Scenario: Add multiple products to the cart
    Given I am on the inventory page
    When I choose few products
    And I add all the products
    Then 3 products are added to the cart