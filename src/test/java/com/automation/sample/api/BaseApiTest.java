package com.automation.sample.api;

import com.automation.api.RestApiClient;
import com.automation.config.ConfigManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseApiTest {

    protected RestApiClient apiClient;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Load base URL from configuration
        String baseUrl = ConfigManager.getProperty("api.base.url");

        // Initialize the RestApiClient with the base URL
        apiClient = new RestApiClient(baseUrl);

        // Optionally set up authentication headers if required
        String authToken = ConfigManager.getProperty("api.auth.token");
        if (authToken != null && !authToken.isEmpty()) {
            apiClient.setRequestHeader("Authorization", "Bearer " + authToken);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Any clean-up logic if necessary
    }
}
