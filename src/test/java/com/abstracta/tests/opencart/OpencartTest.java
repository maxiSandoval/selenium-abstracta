package com.abstracta.tests.opencart;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.abstracta.pages.opencart.HomePage;
import com.abstracta.pages.opencart.ProductPage;
import com.abstracta.pages.opencart.SearchResultPage;
import com.abstracta.pages.opencart.ViewCartPage;
import com.abstracta.tests.BaseTest;
import com.abstracta.utils.Constants;

public class OpencartTest extends BaseTest {

    private HomePage homePage;
    private SearchResultPage searchResultPage;
    private ProductPage productPage;
    private ViewCartPage viewCartPage;

    @BeforeClass
    public void setupTest() {
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
        productPage = new ProductPage(driver);
        viewCartPage = new ViewCartPage(driver);
    }

    @DataProvider(name = "searchData")
    public Object[][] searchData() {
        return new Object[][] { { "iphone" }, { "monitors" }, { "mac" } };
    }

    @Test(dataProvider = "searchData")
    public void searchProductTest(String productName) {
        homePage.goTo(Constants.BASE_URL);
        Assert.assertTrue(homePage.isAt(), "Home page is not loaded correctly.");
        homePage.searchProduct(productName);

        Assert.assertTrue(searchResultPage.isAt(), "Search results page is not loaded correctly.");
        if (searchResultPage.isResultsEmpty()) {
            System.out.println("No products found for the search: " + productName);
            Assert.assertEquals(searchResultPage.getTextEmptyResult(), "Your shopping cart is empty!");
            return;
        }
        searchResultPage.clickFirstItem();

        Assert.assertTrue(productPage.isAt(), "Product page is not loaded correctly.");
        String titleProductName = productPage.getTextProductName();
        productPage.clickAddToCart();
        String successMessage = productPage.getTextSuccessMessage()
                .substring(0, productPage.getTextSuccessMessage().length() - 1).trim();
        Assert.assertEquals(successMessage, "Success: You have added " + titleProductName + " to your shopping cart!");
        productPage.clickCartTotal();
        productPage.clickViewCart();

        Assert.assertTrue(viewCartPage.isAt(), "View cart page is not loaded correctly.");
        Assert.assertTrue(viewCartPage.isProductInCart(titleProductName),
                "The product: " + titleProductName + " was not found");
        viewCartPage.clickRemoveProduct(titleProductName);
        Assert.assertFalse(viewCartPage.isProductInCart(titleProductName),
                "The product: " + titleProductName + " is present");

    }

}
