package com.automation.reporting;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class AllureManager {

    // Start a new test
    public static void startTest(String testName) {
        Allure.getLifecycle().startTestCase(testName);
    }

    // Log success
    public static void logSuccess(String message) {
        Allure.getLifecycle().updateTestCase(testCase -> testCase.setStatus(Status.PASSED));
        Allure.addAttachment("Success", message);
    }

    // Log failure
    public static void logFailure(String message, Throwable throwable) {
        Allure.getLifecycle().updateTestCase(testCase -> testCase.setStatus(Status.FAILED));
        Allure.addAttachment("Failure", message);
        Allure.addAttachment("Stacktrace", throwable.toString());
    }

    // Log skipped
    public static void logSkipped(String message) {
        Allure.getLifecycle().updateTestCase(testCase -> testCase.setStatus(Status.SKIPPED));
        Allure.addAttachment("Skipped", message);
    }
}
