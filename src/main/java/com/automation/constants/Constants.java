package com.automation.constants;

public final class Constants {
    // Prevent instantiation
    private Constants() {}

    // Base URLs
    public static final String BASE_API_URL = "http://api.example.com";
    public static final String BASE_UI_URL = "http://ui.example.com";

    // Timeout values (in milliseconds)
    public static final int DEFAULT_TIMEOUT = 30000;
    public static final int SHORT_TIMEOUT = 10000;

    // File paths
    public static final String CONFIG_FILE_PATH = "resources/config/config.properties";
    public static final String TEST_DATA_PATH = "data/testdata.xlsx";

    // HTTP Methods
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_DELETE = "DELETE";

    // Browser types
    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FIREFOX = "firefox";
    public static final String BROWSER_EDGE = "edge";
}
