package org.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyStepdefs {
    private WebDriver driver;

    @Given("^I have a Chrome browser instance$")
    public void iHaveAChromeBrowserInstance() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("^I open a Chrome browser$")
    public void iOpenAChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @When("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String url) {
        driver.get(url);
    }

    @Then("^the page title should contain \"([^\"]*)\"$")
    public void thePageTitleShouldContain(String titleText) {
        String actualTitle = driver.getTitle();
        if (!actualTitle.contains(titleText)) {
            throw new AssertionError("Expected title to contain: " + titleText + " but was: " + actualTitle);
        }
    }

    @Then("^I should see the login button$")
    public void iShouldSeeTheLoginButton() {
        // Implementation would depend on the actual page structure
        // This is a placeholder for the actual implementation
    }

    @Then("^I wait for (\\d+) seconds?$")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Given("^I am on the Fidelity homepage$")
    public void iAmOnTheFidelityHomepage() {
        driver.get("https://www.fidelity.com");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchTerm) {
        // Implementation would depend on the actual search element
        // This is a placeholder for the actual implementation
    }

    @Then("^I should see search results$")
    public void iShouldSeeSearchResults() {
        // Implementation would depend on the actual search results
        // This is a placeholder for the actual implementation
    }
}
