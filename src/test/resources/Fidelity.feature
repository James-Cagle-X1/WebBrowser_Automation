@Fidelity
Feature: Fidelity

  Scenario: Navigate to Fidelity website
    Given I open a Chrome browser
    When I navigate to "https://www.fidelity.com"
    Then the page title should contain "Fidelity"
    And I should see the login button
    #And I wait for 3 seconds

    #Scenario: Close the browser
    Then I close the browser
    #Then the browser should be closed

