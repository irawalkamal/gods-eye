package com.automation.api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

public class GraphqlApiClient extends BaseApiClient {

    public GraphqlApiClient(String baseUrl) {
        super(baseUrl);
    }

    // Method to send GraphQL requests
    public String sendGraphQLRequest(String endpoint, String query, Map<String, String> headers) throws Exception {
        // Establish connection
        HttpURLConnection connection = establishConnection(endpoint, "POST", null);

        // Set Content-Type for GraphQL
        setContentType("application/json");

        // Set additional headers if provided
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        // Set GraphQL query as request body
        if (query != null && !query.isEmpty()) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(query.getBytes());
                os.flush();
            }
        }

        // Get and return response
        return getResponse(connection);
    }
}
