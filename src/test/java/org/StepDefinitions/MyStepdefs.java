package org.StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.ExtentReportManager;
import org.PageObjectManager;
import org.Pages.HomePage;
import org.Pages.LoginPage;
import org.Pages.SearchPage;

public class MyStepdefs {
    private PageObjectManager pageObjectManager;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;

    @Before
    public void setUp(Scenario scenario) {
        ExtentReportManager.setupReport();
        ExtentReportManager.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.logFail("Scenario Failed: " + scenario.getName());
            ExtentReportManager.addScreenshot("Failed_Scenario_" + scenario.getName());
        } else {
            ExtentReportManager.logPass("Scenario Passed: " + scenario.getName());
        }
        
        if (pageObjectManager != null) {
            pageObjectManager.quitDriver();
        }
        ExtentReportManager.flushReport();
    }

    @Given("^I open a Chrome browser$")
    public void iOpenAChromeBrowser() {
        ExtentReportManager.logInfo("Opening Chrome browser");
        pageObjectManager = new PageObjectManager();
        homePage = pageObjectManager.getHomePage();
        ExtentReportManager.setDriver(pageObjectManager.getDriver());
        ExtentReportManager.logPass("Chrome browser opened successfully");
    }

    @When("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String url) {
        ExtentReportManager.logInfo("Navigating to: " + url);
        pageObjectManager.getDriver().get(url);
        ExtentReportManager.logPass("Successfully navigated to: " + url);
    }

    @Then("^the page title should contain \"([^\"]*)\"$")
    public void thePageTitleShouldContain(String titleText) {
        ExtentReportManager.logInfo("Checking if page title contains: " + titleText);
        String actualTitle = homePage.getPageTitle();
        System.out.println(actualTitle);
        if (!actualTitle.contains(titleText)) {
            ExtentReportManager.logFail("Expected title to contain: " + titleText + " but was: " + actualTitle);
            throw new AssertionError("Expected title to contain: " + titleText + " but was: " + actualTitle);
        }
        ExtentReportManager.logPass("Page title verification passed: " + actualTitle);
    }

    @Then("^I should see the login button$")
    public void iShouldSeeTheLoginButton() throws InterruptedException {
        ExtentReportManager.logInfo("Checking if login button is displayed");
        if (!homePage.isLoginButtonDisplayed()) {
            ExtentReportManager.logFail("Login button is not displayed on the page");
            throw new AssertionError("Login button is not displayed on the page");
        }
        ExtentReportManager.logPass("Login button is displayed on the page");
    }

    @Then("^I wait for (\\d+) seconds?$")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        ExtentReportManager.logInfo("Waiting for " + seconds + " seconds");
        Thread.sleep(seconds * 1000);
        ExtentReportManager.logPass("Wait completed");
    }

    @Given("^I am on the Fidelity homepage$")
    public void iAmOnTheFidelityHomepage() {
        ExtentReportManager.logInfo("Navigating to Fidelity homepage");
        pageObjectManager.getDriver().get("https://www.fidelity.com");
        ExtentReportManager.logPass("Successfully navigated to Fidelity homepage");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchTerm) {
        ExtentReportManager.logInfo("Searching for: " + searchTerm);
        homePage.enterSearchText(searchTerm);
        homePage.clickSearchButton();
        ExtentReportManager.logPass("Search completed for: " + searchTerm);
    }

    @Then("^I should see search results$")
    public void iShouldSeeSearchResults() {
        ExtentReportManager.logInfo("Checking if search results are displayed");
        if (!searchPage.areSearchResultsDisplayed()) {
            ExtentReportManager.logFail("Search results are not displayed");
            throw new AssertionError("Search results are not displayed");
        }
        ExtentReportManager.logPass("Search results are displayed");
    }

    @When("^I close the browser$")
    public void iCloseTheBrowser() {
        ExtentReportManager.logInfo("Closing browser");
        pageObjectManager.quitDriver();
        ExtentReportManager.logPass("Browser closed successfully");
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
