package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Actions {
    public static void openChromeAndGoToGoogle() {
        /// Automatically setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Initialize a new ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        // Navigate to Google
        driver.get("https://www.google.com");
    }
}
