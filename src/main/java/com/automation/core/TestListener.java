package com.automation.core;

import com.automation.reporting.AllureManager;
import com.automation.reporting.ExtentManager;
import com.automation.utils.CommonUtils;
import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Initialize ExtentReports
        ExtentManager.startTest(result.getMethod().getMethodName());

        // Initialize Allure reporting
        AllureManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success to ExtentReports
        ExtentManager.logSuccess(result.getMethod().getMethodName() + " - Passed");

        // Log success to Allure
        AllureManager.logSuccess(result.getMethod().getMethodName() + " - Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure to ExtentReports
        String failureMessage = result.getMethod().getMethodName() + " - Failed\n" + result.getThrowable().toString();
        ExtentManager.logFailure(failureMessage);

        // Capture and attach screenshot for failure
        String screenshotPath = CommonUtils.captureScreenshot(result.getMethod().getMethodName());
        ExtentManager.attachScreenshot(screenshotPath);

        // Log failure to Allure
        AllureManager.logFailure(result.getMethod().getMethodName() + " - Failed", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skipped test to ExtentReports
        ExtentManager.logSkipped(result.getMethod().getMethodName() + " - Skipped");

        // Log skipped test to Allure
        AllureManager.logSkipped(result.getMethod().getMethodName() + " - Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        // Optionally initialize any required reporting systems
        // For example, initializing global settings or configurations

        // Initialize ExtentReports
        ExtentManager.getInstance();

        // Initialize Allure (this is not strictly required but can be used for setup)
        Allure.getLifecycle();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Finalize ExtentReports
        ExtentManager.getInstance().flush();

        // Generate Allure report
        //generateAllureReport();
    }

    private void generateAllureReport() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "allure", "generate", "allure-results", "--clean", "-o", "test-output/allure-report"
            );
            processBuilder.inheritIO().start().waitFor();
            System.out.println("Allure report generated at: allure-report");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate Allure report");
        }
    }
}