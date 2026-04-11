package org;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.StepDefinitions.MyStepdefs;

public class Hooks {
    private MyStepdefs stepDefs;

    @Before
    public void setUp() {
        // This will be called before each scenario
        // Step definitions will handle their own PageObjectManager initialization
    }

    @After
    public void tearDown() {
        // This will be called after each scenario
        // Clean up resources if needed
    }
}
