package com.abstracta.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.abstracta.WebDriverFactory;
import com.abstracta.listener.TestListener;
import com.abstracta.utils.Constants;

@Listeners({ TestListener.class })
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup(ITestContext ctx) {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver(browser);
        ctx.setAttribute(Constants.DRIVER, driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
