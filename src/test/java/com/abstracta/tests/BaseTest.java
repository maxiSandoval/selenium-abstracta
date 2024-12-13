package com.abstracta.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.abstracta.WebDriverFactory;


public abstract class BaseTest {

    protected WebDriver driver;
    
    @BeforeTest
    public void setup() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver(browser);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
