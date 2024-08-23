package com.automation.sample.db;

import com.automation.db.DatabaseConnection;
import com.automation.db.QueryExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDBTest {

    protected Connection connection;
    protected QueryExecutor queryExecutor;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws SQLException {
        // Initialize the database connection
        connection = DatabaseConnection.getConnection();

        // Initialize the QueryExecutor
        queryExecutor = new QueryExecutor(connection);

        // Additional setup, like preparing data or setting transaction modes
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close the database connection
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
