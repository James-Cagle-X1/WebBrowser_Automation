package org;

import io.cucumber.core.cli.Main;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:Fidelity.feature",
    glue = "org.StepDefinitions",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunnerMain {
    
    public static void main(String[] args) {
        try {
            System.out.println("Running Cucumber tests from main method...");
            
            // Use JUnit runner to execute tests
            org.junit.runner.JUnitCore.main("org.TestRunnerMain");
            
        } catch (Exception e) {
            System.err.println("Error running tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // You can add headless configuration here
    // Uncomment the following lines to run in headless mode:
    /*
    static {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
    }
    */
}
