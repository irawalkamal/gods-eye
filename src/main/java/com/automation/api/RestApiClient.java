package com.automation.api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

public class RestApiClient extends BaseApiClient {

    public RestApiClient(String baseUrl) {
        super(baseUrl);
    }

    // Method to send REST requests (GET, POST, PUT, DELETE)
    public String sendRestRequest(String method, String endpoint, String body, Map<String, String> headers, String contentType) throws Exception {
        // Establish connection
        HttpURLConnection connection = establishConnection(endpoint, method, null);

        // Set Content-Type if provided
        if (contentType != null) {
            setContentType(contentType);
        }

        // Set additional headers if provided
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        // Set request body if applicable (e.g., POST, PUT)
        if (body != null && !body.isEmpty()) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(body.getBytes());
                os.flush();
            }
        }

        // Get and return response
        return getResponse(connection);
    }
}
