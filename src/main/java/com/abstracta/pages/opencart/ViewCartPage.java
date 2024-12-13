package com.abstracta.pages.opencart;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.abstracta.pages.BasePage;

public class ViewCartPage extends BasePage {

    @FindBy(xpath = "//*[@id='content']//table/tbody/tr/td[2]/a")
    private List<WebElement> productListCartElements;

    public ViewCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(productListCartElements.get(0)));
        return productListCartElements.get(0).isDisplayed();
    }

    public boolean isProductInCart(String productName) {
        return productListCartElements.stream()
                .map(WebElement::getText)
                .anyMatch(product -> product.equals(productName));
    }

    public void clickRemoveProduct(String productName) {
        List<String> productNames = productListCartElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        int index = productNames.indexOf(productName);

        if (index != -1) {
            WebElement removeButton = driver
                    .findElements(By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[4]/div/span/button[2]"))
                    .get(index);
            removeButton.click();
            wait.until(_ -> {
                boolean isProductRemoved = productListCartElements.stream()
                        .map(WebElement::getText)
                        .noneMatch(name -> name.equals(productName));
                return isProductRemoved;
            });
        }
    }
}
