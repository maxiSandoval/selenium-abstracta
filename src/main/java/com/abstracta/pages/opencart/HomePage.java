package com.abstracta.pages.opencart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.abstracta.pages.BasePage;

public class HomePage extends BasePage{

    @FindBy(xpath = "//*[@id='search']/input")
    private WebElement inputSearchProduct; 

    @FindBy(xpath = "//*[@id='search']/span/button")
    private WebElement buttonSearchProduct;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(inputSearchProduct));
        return inputSearchProduct.isDisplayed();
    }
    
    public void searchProduct(String productName){
        inputSearchProduct.sendKeys(productName);
        buttonSearchProduct.click();
    }




}
