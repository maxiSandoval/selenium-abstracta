package com.abstracta;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class WebDriverFactory {

    public Map<String, Supplier<WebDriver>> driverMap = new HashMap<>();

    public WebDriverFactory() {
        driverMap.put("chrome", this::createChromeDriver);
        driverMap.put("firefox", this::createFirefoxDriver);
        driverMap.put("edge", this::createEdgeDriver);
    }

    public WebDriver getDriver(String browserName) {
        return driverMap.getOrDefault(browserName.toLowerCase().trim(), () -> {
            Reporter.log("Browser not found: " + browserName);
            throw new RuntimeException("Browser not found...");
        }).get();
    }

    private WebDriver createChromeDriver() {
        return new ChromeDriver();
    }

    private WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }

    private WebDriver createEdgeDriver() {
        return new EdgeDriver();
    }
}
