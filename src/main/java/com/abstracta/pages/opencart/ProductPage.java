package com.abstracta.pages.opencart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.abstracta.pages.BasePage;

public class ProductPage extends BasePage {

    @FindBy(id = "button-cart")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "//*[@id='product-product']/div[@class='alert alert-success alert-dismissible']")
    private WebElement successMessageElement;

    @FindBy(id = "cart-total")
    private WebElement buttonCartTotal;

    @FindBy(xpath = "//*[@id=\"cart\"]/ul//p//i[@class='fa fa-shopping-cart']")
    private WebElement buttonViewCart;

    @FindBy(xpath = "//*[@id='content']/div[1]/div[2]/h1")
    private WebElement productNameElement;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(buttonAddToCart));
        return buttonAddToCart.isDisplayed();
    }

    public void clickAddToCart(){
        buttonAddToCart.click();
    }

    public String getTextSuccessMessage(){
        wait.until(ExpectedConditions.visibilityOf(successMessageElement));
        return successMessageElement.getText();
    }

    public void clickCartTotal() {
        buttonCartTotal.click();
    }

    public void clickViewCart() {
        wait.until(ExpectedConditions.visibilityOf(buttonViewCart));
        buttonViewCart.click();
    }

    public String getTextProductName() {
        return productNameElement.getText();
    }
}
