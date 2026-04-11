package org.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage extends BasePage {
    
    @FindBy(id = "search-input")
    private WebElement searchInput;
    
    @FindBy(className = "search-results")
    private WebElement searchResultsContainer;
    
    @FindBy(className = "result-item")
    private List<WebElement> resultItems;
    
    @FindBy(className = "no-results")
    private WebElement noResultsMessage;
    
    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void enterSearchTerm(String term) {
        sendTextToElement(searchInput, term);
    }
    
    public boolean areSearchResultsDisplayed() {
        return isElementDisplayed(searchResultsContainer);
    }
    
    public int getSearchResultsCount() {
        return resultItems.size();
    }
    
    public boolean isNoResultsMessageDisplayed() {
        return isElementDisplayed(noResultsMessage);
    }
    
    public String getNoResultsMessage() {
        return getElementText(noResultsMessage);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
}
