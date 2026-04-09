package org.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Actions {
    public static void openChromeAndGoToURL(String url) {
        /// Automatically setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        // Initialize a new ChromeDriver instance
        WebDriver driver = new ChromeDriver();
        // Navigate to URL
        driver.get(url);
    }
}
