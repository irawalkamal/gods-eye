package com.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestEngine {
    private static WebDriver driver;

    // Initialize WebDriver based on the browser type
    public static WebDriver getDriver(String browser, boolean isRemote, String gridUrl) {
        if (isRemote) {
            try {
                // Set up remote WebDriver based on browser type
                DesiredCapabilities capabilities = new DesiredCapabilities();
                switch (browser.toLowerCase()) {
                    case "chrome":
                        capabilities.setBrowserName("chrome");
                        break;
                    case "firefox":
                        capabilities.setBrowserName("firefox");
                        break;
                    case "edge":
                        capabilities.setBrowserName("MicrosoftEdge");
                        break;
                    default:
                        throw new IllegalArgumentException("Browser not supported: " + browser);
                }
                driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Invalid Grid URL: " + gridUrl);
            }
        } else {
            // Initialize local WebDriver based on browser type
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
        }

        // Configure WebDriver options (e.g., implicit wait)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    // Close and quit the WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
