package com.automation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private ExtentManager() {}

    // Initialize ExtentReports
    public static ExtentReports getInstance() {
        if (extentReports == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/extent-report/extent-report.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
        }
        return extentReports;
    }

    // Start a new test
    public static void startTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
    }

    // Log success
    public static void logSuccess(String message) {
        test.get().pass(message);
    }

    // Log failure
    public static void logFailure(String message) {
        test.get().fail(message);
    }

    // Log skipped
    public static void logSkipped(String message) {
        test.get().skip(message);
    }

    // Attach screenshot
    public static void attachScreenshot(String screenshotPath) {
        test.get().addScreenCaptureFromPath(screenshotPath);
    }

    // Finalize the report
    public static void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
