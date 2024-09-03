@tag
Feature: Purchase product from Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the oder
    Given Logged in with Username <name> and Password <password>
    When I add <ProductName> to the cart
    And Checkout <ProductName> and Submit the Order
    Then "Thankyou for the order." message is displayed as Confirmation Page


    Examples:
      | name              | password    | ProductName   |
      | anshika@gmail.com | Iamking@000 | IPHONE 13 PRO |
