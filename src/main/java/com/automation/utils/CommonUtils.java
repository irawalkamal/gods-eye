package com.automation.utils;

import lombok.Setter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    // Set the WebDriver instance
    @Setter
    private static WebDriver driver;

    // Capture screenshot and return the file path
    public static String captureScreenshot(String methodName) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver instance is not set.");
        }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = "screenshots/" + methodName + "_" + getTimeStamp() + ".png";

        try {
            FileHandler.copy(screenshotFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save screenshot: " + e.getMessage());
        }

        return filePath;
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

}