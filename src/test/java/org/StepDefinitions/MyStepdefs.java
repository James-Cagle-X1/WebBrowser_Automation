package org.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.PageObjectManager;
import org.Pages.HomePage;
import org.Pages.LoginPage;
import org.Pages.SearchPage;

public class MyStepdefs {
    private PageObjectManager pageObjectManager;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;

    @Given("^I open a Chrome browser$")
    public void iOpenAChromeBrowser() {
        pageObjectManager = new PageObjectManager();
        homePage = pageObjectManager.getHomePage();
    }

    @When("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String url) {
        pageObjectManager.getDriver().get(url);
    }

    @Then("^the page title should contain \"([^\"]*)\"$")
    public void thePageTitleShouldContain(String titleText) {
        String actualTitle = homePage.getPageTitle();
        System.out.println(actualTitle);
        if (!actualTitle.contains(titleText)) {
            throw new AssertionError("Expected title to contain: " + titleText + " but was: " + actualTitle);
        }
    }

    @Then("^I should see the login button$")
    public void iShouldSeeTheLoginButton() throws InterruptedException {
        if (!homePage.isLoginButtonDisplayed()) {
            throw new AssertionError("Login button is not displayed on the page");
        }
    }

    @Then("^I wait for (\\d+) seconds?$")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Given("^I am on the Fidelity homepage$")
    public void iAmOnTheFidelityHomepage() {
        pageObjectManager.getDriver().get("https://www.fidelity.com");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchTerm) {
        homePage.enterSearchText(searchTerm);
        homePage.clickSearchButton();
    }

    @Then("^I should see search results$")
    public void iShouldSeeSearchResults() {
        if (!searchPage.areSearchResultsDisplayed()) {
            throw new AssertionError("Search results are not displayed");
        }
    }

    @When("^I close the browser$")
    public void iCloseTheBrowser() {
        pageObjectManager.quitDriver();
    }

    @Then("^the browser should be closed$")
    public void theBrowserShouldBeClosed() {
        if (pageObjectManager != null && pageObjectManager.isBrowserOpen()) {
            throw new AssertionError("Browser is still open");
        } else {
            System.out.println("Browser successfully closed");
        }
    }
}
