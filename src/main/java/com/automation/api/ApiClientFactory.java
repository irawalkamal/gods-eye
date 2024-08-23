package com.automation.api;

public class ApiClientFactory {

    // Factory method to create an instance of RestApiClient
    public static RestApiClient createRestClient(String baseUrl) {
        return new RestApiClient(baseUrl);
    }

    // Factory method to create an instance of SoapApiClient
    public static SoapApiClient createSoapClient(String baseUrl) {
        return new SoapApiClient(baseUrl);
    }

    // Factory method to create an instance of GraphQLApiClient
    public static GraphqlApiClient createGraphQLClient(String baseUrl) {
        return new GraphqlApiClient(baseUrl);
    }
}
