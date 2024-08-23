package com.automation.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseApiClient {
    protected String baseUrl;
    protected Map<String, String> headers = new HashMap<>();

    public BaseApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    // Method to add a header
    public void setRequestHeader(String key, String value) {
        headers.put(key, value);
    }

    // Method to set a bearer token
    public void setBearerToken(String token) {
        setRequestHeader("Authorization", "Bearer " + token);
    }

    // Method to set basic auth
    public void setBasicAuth(String username, String password) {
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        setRequestHeader("Authorization", "Basic " + encodedAuth);
    }

    // Method to set API key
    public void setApiKey(String apiKey) {
        setRequestHeader("x-api-key", apiKey);
    }

    // Reset headers
    public void resetHeaders() {
        headers.clear();
    }

    // Method to handle the response
    protected String getResponse(HttpURLConnection connection) throws Exception {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (IOException e) {
            throw new Exception("Error reading response: " + e.getMessage(), e);
        }
    }

    // Method to set the content type for the request
    public void setContentType(String contentType) {
        setRequestHeader("Content-Type", contentType);
    }

    // Method to build a full URL with query parameters
    protected String buildUrlWithParams(String endpoint, Map<String, String> queryParams) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl + endpoint);
        if (queryParams != null && !queryParams.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            urlBuilder.setLength(urlBuilder.length() - 1); // Remove trailing "&"
        }
        return urlBuilder.toString();
    }

    // Method to establish a connection
    protected HttpURLConnection establishConnection(String endpoint, String method, String queryParams) throws Exception {
        try {
            URL url = new URL(baseUrl + endpoint + (queryParams != null ? "?" + queryParams : ""));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(5000); // Set connection timeout
            connection.setReadTimeout(5000); // Set read timeout
            return connection;
        } catch (MalformedURLException e) {
            throw new Exception("Invalid URL: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new Exception("Error establishing connection: " + e.getMessage(), e);
        }
    }

}
