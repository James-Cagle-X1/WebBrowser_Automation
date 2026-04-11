package org.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }
    
    protected void sendTextToElement(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    
    protected String getElementText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }
    
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Frame handling methods
    protected void switchToFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }
    
    protected void switchToFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }
    
    protected void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }
    
    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    
    protected boolean switchToFrameContainingElement(WebElement element) {
        List<WebElement> frames = driver.findElements(org.openqa.selenium.By.tagName("iframe"));
        
        for (WebElement frame : frames) {
            try {
                driver.switchTo().frame(frame);
                if (isElementDisplayed(element)) {
                    return true;
                }
                driver.switchTo().defaultContent();
            } catch (Exception e) {
                driver.switchTo().defaultContent();
                continue;
            }
        }
        return false;
    }
    
    protected void findAndSwitchToFrameWithElement(WebElement element) {
        if (!switchToFrameContainingElement(element)) {
            throw new RuntimeException("Element not found in any frame");
        }
    }
    
    // Dynamic waiting methods
    protected void waitForElementToBePresent(org.openqa.selenium.By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    protected void waitForElementToBeVisible(org.openqa.selenium.By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected WebElement findElementWithWait(org.openqa.selenium.By locator) {
        try {
            waitForElementToBePresent(locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("Element not found with locator: " + locator, e);
        }
    }
    
    protected boolean isElementPresent(org.openqa.selenium.By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    protected boolean isElementVisible(org.openqa.selenium.By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
