package com.automation.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverManager {

    private static final Properties configProperties = DriverConfig.loadConfig();

    public static WebDriver createDriver(String browser, boolean isRemote) {
        WebDriver driver;

        if (isRemote) {
            driver = createRemoteDriver(browser);
        } else {
            driver = createLocalDriver(browser);
        }

        return driver;
    }

    private static WebDriver createLocalDriver(String browser) {

        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new org.openqa.selenium.chrome.ChromeDriver(getChromeOptions());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new org.openqa.selenium.firefox.FirefoxDriver(getFirefoxOptions());
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new org.openqa.selenium.edge.EdgeDriver(getEdgeOptions());
            }
            case "safari" -> new org.openqa.selenium.safari.SafariDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver createRemoteDriver(String browser) {
        WebDriver driver = null;
        URL gridUrl;

        try {
            gridUrl = new URL(configProperties.getProperty("grid.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Grid URL", e);
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);

        switch (browser.toLowerCase()) {
            case "chrome":
                capabilities.merge(getChromeOptions());
                break;

            case "firefox":
                capabilities.merge(getFirefoxOptions());
                break;

            case "edge":
                capabilities.merge(getEdgeOptions());
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser for remote execution: " + browser);
        }

        driver = new RemoteWebDriver(gridUrl, capabilities);

        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        return options;
    }
}
