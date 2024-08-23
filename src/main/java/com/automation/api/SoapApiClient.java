package com.automation.api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

public class SoapApiClient extends BaseApiClient {

    public SoapApiClient(String baseUrl) {
        super(baseUrl);
    }

    // Method to send SOAP requests
    public String sendSoapRequest(String endpoint, String soapEnvelope, Map<String, String> headers) throws Exception {
        // Establish connection
        HttpURLConnection connection = establishConnection(endpoint, "POST", null);

        // Set Content-Type for SOAP
        setContentType("text/xml; charset=utf-8");

        // Set additional headers if provided
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        // Set SOAP envelope as request body
        if (soapEnvelope != null && !soapEnvelope.isEmpty()) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(soapEnvelope.getBytes());
                os.flush();
            }
        }

        // Get and return response
        return getResponse(connection);
    }
}
