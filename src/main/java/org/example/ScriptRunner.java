package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.Actions.*;

public class ScriptRunner {

    public static void main(String[] args) {
        openChromeAndGoToGoogle();
    }

}