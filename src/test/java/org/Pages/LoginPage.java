package org.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-submit")
    private WebElement loginSubmitButton;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void enterUsername(String username) {
        sendTextToElement(usernameField, username);
    }
    
    public void enterPassword(String password) {
        sendTextToElement(passwordField, password);
    }
    
    public void clickLoginSubmit() {
        clickElement(loginSubmitButton);
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginSubmit();
    }
    
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    public String getErrorMessage() {
        return getElementText(errorMessage);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
}
