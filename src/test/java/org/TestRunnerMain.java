package org;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:Fidelity.feature",
    glue = "org.StepDefinitions",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunnerMain {
}
