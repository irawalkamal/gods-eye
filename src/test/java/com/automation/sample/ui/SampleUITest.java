package com.automation.sample.ui;

import com.automation.config.ConfigManager;
import com.automation.ui.BasePage;
import com.automation.ui.UiUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleUITest extends BaseUiTest {

    private BasePage basePage;
    private UiUtils UiUtils;

    @BeforeMethod
    public void setUp() {
        super.setUp(); // Call BaseUITest setup
        basePage = new BasePage(driver); // Initialize your BasePage
        uiUtils = new UiUtils(driver);   // Initialize your UIUtils
    }

    @Test
    public void testPageTitle() {
        basePage.openUrl(ConfigManager.getProperty("baseUrl"));
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "Google", "Page title should match the expected value");
    }

    @Test
    public void testElementInteraction() {
        basePage.openUrl(ConfigManager.getProperty("baseUrl"));
        WebElement element = basePage.findElement(By.id("APjFqb"));
        basePage.sendKeys(element,"hello");

    }
}
