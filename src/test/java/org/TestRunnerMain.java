package org;

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
