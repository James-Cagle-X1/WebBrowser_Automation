@Fidelity
Feature: Fidelity

  Background:
    Given I have a Chrome browser instance

  Scenario: Navigate to Fidelity website and perform basic actions
    Given I open a Chrome browser
    When I navigate to "https://www.fidelity.com"
    Then the page title should contain "Fidelity"
    And I should see the login button
    And I wait for 3 seconds

