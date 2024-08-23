package com.automation.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Open a URL
    public void openUrl(String url) {
        driver.get(url);
    }

    // Get the title of the page
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Find an element by its locator
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // Find multiple elements by their locator
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    // Wait for an element to be visible
    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for an element to be clickable
    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Click an element
    public void clickElement(By locator) {
        waitForElementToBeClickable(locator);
        findElement(locator).click();
    }

    // Enter text into an input field
    public void enterText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Get text from an element
    public String getText(By locator) {
        return findElement(locator).getText();
    }

    // Check if an element is displayed
    public boolean isElementDisplayed(By locator) {
        return findElement(locator).isDisplayed();
    }

    // Select an option from a dropdown
    public void selectFromDropdown(By locator, String visibleText) {
        WebElement dropdown = findElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // Handle alert pop-ups
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    // Scroll to an element
    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Take a screenshot (optional, requires implementation of screenshot capture)
    // public void takeScreenshot(String filePath) {
    //     File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    //     try {
    //         Files.copy(screenshot.toPath(), Paths.get(filePath));
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // Additional methods can be added as needed


    // Wait for element to be visible
    public WebElement waitForElementToBeVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    // Wait for element to be clickable
    public WebElement waitForElementToBeClickable(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    // Click on an element
    public void click(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    // Send keys to an input field
    public void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element).clear();
        element.sendKeys(text);
    }


    // Navigate to a URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Select an option from a dropdown by visible text
    public void selectByVisibleText(WebElement dropdown, String text) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    // Select an option from a dropdown by value
    public void selectByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // Get the text of an element
    public String getText(WebElement element) {
        return waitForElementToBeVisible(element).getText();
    }

    // Verify if an element is displayed
    public boolean isElementDisplayed(WebElement element) {
        try {
            return waitForElementToBeVisible(element).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Wait for all elements in a list to be visible
    public List<WebElement> waitForAllElementsToBeVisible(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // Scroll to an element
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Execute JavaScript on the page
    public void executeJavaScript(String script, Object... args) {
        ((JavascriptExecutor) driver).executeScript(script, args);
    }

    // Additional common methods can be added here
}
