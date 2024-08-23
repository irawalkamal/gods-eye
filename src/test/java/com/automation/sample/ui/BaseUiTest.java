package com.automation.sample.ui;

import com.automation.config.ConfigManager;
import com.automation.core.TestEngine;
import com.automation.ui.BasePage;
import com.automation.ui.UiUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseUiTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected UiUtils uiUtils;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Example configuration, replace with your actual configuration
        String browser = ConfigManager.getProperty("browser"); // Replace with dynamic value or configuration
        boolean isRemote = false;  // Replace with dynamic value or configuration
        String gridUrl = "";        // Replace with actual Grid URL if isRemote is true

        // Initialize WebDriver using TestEngine
        driver = TestEngine.getDriver(browser, isRemote, gridUrl);

        // Initialize BasePage and UIUtils
        basePage = new BasePage(driver);
        uiUtils = new UiUtils(driver);

        // Optionally maximize the browser window
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Quit the WebDriver instance
        TestEngine.quitDriver();
    }
}
