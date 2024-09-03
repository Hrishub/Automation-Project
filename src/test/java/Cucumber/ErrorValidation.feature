@tag
Feature: Verify Error Message on Login Page

  @ErrorValidation
  Scenario Outline: User to verify Error message on invalid credentials while logging
    Given I landed on Ecommerce Page
    When Logged in with Username <name> and Password <password>
    Then "Incorrect email or password." message is displayed on Login Page.




    Examples:
      | name              | password    |
      | anshika@gmail.com | Imking@000 |