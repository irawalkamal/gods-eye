package com.automation.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find db.properties file");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load db.properties file", e);
        }

        String dbType = properties.getProperty("db.type");
        String url = switch (dbType.toLowerCase()) {
            case "mysql" ->
                    "jdbc:mysql://" + properties.getProperty("db.host") + ":" + properties.getProperty("db.port") + "/" + properties.getProperty("db.name");
            case "postgres" ->
                    "jdbc:postgresql://" + properties.getProperty("db.host") + ":" + properties.getProperty("db.port") + "/" + properties.getProperty("db.name");
            case "oracle" ->
                    "jdbc:oracle:thin:@" + properties.getProperty("db.host") + ":" + properties.getProperty("db.port") + ":" + properties.getProperty("db.name");
            default -> throw new IllegalArgumentException("Unsupported database type: " + dbType);
        };

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, properties.getProperty("db.user"), properties.getProperty("db.password"));
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
