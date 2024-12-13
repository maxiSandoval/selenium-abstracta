package com.abstracta.pages.opencart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.abstracta.pages.BasePage;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//*[@id='content']//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']")
    private List<WebElement> resultItemsListElements;

    @FindBy(id = "content")
    private WebElement gridResultElement;


    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(gridResultElement));
        return gridResultElement.isDisplayed();
    }

    public void clickFirstItem() {
        resultItemsListElements.get(0).click();
    }

    public boolean isResultsEmpty() {
        return resultItemsListElements.isEmpty();
    }

    public String getTextEmptyResult() {
        WebElement emptyResult = driver.findElement(By.xpath("//*[@id='content']/p[2]"));
        return emptyResult.getText();
    }  

}
