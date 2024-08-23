package com.automation.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor
    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Common method to click on a component element
    protected void clickComponent(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Common method to get text from a component element
    protected String getComponentText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    // Common method to check if a component element is visible
    protected boolean isComponentVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    // Additional common component methods can be added here
}
