package org;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.Pages.HomePage;
import org.Pages.LoginPage;
import org.Pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageObjectManager {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Page Objects (add more as you create them)
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    
    public PageObjectManager() {
        initializeDriver();
    }
    
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    private void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public WebDriverWait getWait() {
        return wait;
    }
    
    // Page Object getters
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }
    
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
    
    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage(driver);
        }
        return searchPage;
    }
    
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    public boolean isBrowserOpen() {
        if (driver == null) {
            return false;
        }
        try {
            driver.getCurrentUrl();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
