package org.Pages;

import org.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    
    @FindBy(id = "search")
    private WebElement searchBox;
    
    @FindBy(className = "search-button")
    private WebElement searchButton;
    
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void openChromeBrowser() {
        // This method is for reference - actual browser creation should be handled by PageObjectManager
        System.out.println("Chrome browser should be opened via PageObjectManager");
    }
    
    public void clickLoginButton() {
        WebElement loginElement = findLoginButton();
        if (loginElement != null) {
            clickElement(loginElement);
        } else {
            throw new RuntimeException("Login button not found with any locator");
        }
    }
    
    public boolean isLoginButtonDisplayed() {
        boolean isDisplayed = findLoginButton() != null;
        
        // Take screenshot for execution report
        try {
            if (driver != null) {
                // Add screenshot to Extent Report
                ExtentReportManager.addScreenshot("Login-Button-Check");
                
                // Also save to target directory for backup
                org.openqa.selenium.TakesScreenshot screenshot = (org.openqa.selenium.TakesScreenshot) driver;
                java.io.File screenshotFile = screenshot.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
                
                // Create screenshots directory if it doesn't exist
                java.io.File renamedFile = new java.io.File("target/screenshots/login-button-check-" + System.currentTimeMillis() + ".png");
                renamedFile.getParentFile().mkdirs();
                
                // Rename file to include timestamp
                screenshotFile.renameTo(renamedFile);
                
                System.out.println("Screenshot saved: " + renamedFile.getAbsolutePath());
                System.out.println("Login button displayed: " + isDisplayed);
            }
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
        
        return isDisplayed;
    }
    
    private WebElement findLoginButton() {
        // Only use LinkText: Log in locator
        org.openqa.selenium.By linkTextLocator = org.openqa.selenium.By.linkText("Log in");
        
        try {
            WebElement element = findElementWithWait(linkTextLocator);
            if (element != null && element.isDisplayed()) {
                System.out.println("Login button found using LinkText: Log in");
                return element;
            }
        } catch (Exception e) {
            System.out.println("Failed to find login button with LinkText: Log in");
        }
        
        System.out.println("Login button not found on the page");
        return null;
    }
    
        
    public void enterSearchText(String searchText) {
        sendTextToElement(searchBox, searchText);
    }
    
    public void clickSearchButton() {
        clickElement(searchButton);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
}
