package com.automation.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {

    private  Connection connection;

    public QueryExecutor(Connection connection) {
        this.connection = connection;
    }
    /**
     * Executes a SELECT query and returns the ResultSet.
     *
     * @param query the SQL query to execute
     * @return ResultSet the result of the query execution
     * @throws SQLException if a database access error occurs
     */
    public static ResultSet executeQuery(String query) throws SQLException {
        // Get a connection from the DatabaseConnection class
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    /**
     * Executes an UPDATE, INSERT, or DELETE query.
     *
     * @param query the SQL query to execute
     * @return int the number of rows affected by the query
     * @throws SQLException if a database access error occurs
     */
    public static int executeUpdate(String query) throws SQLException {
        // Get a connection from the DatabaseConnection class
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();
    }

    /**
     * Executes a query with parameters and returns the ResultSet.
     *
     * @param query the SQL query to execute
     * @param params the parameters to set in the query
     * @return ResultSet the result of the query execution
     * @throws SQLException if a database access error occurs
     */
    public static ResultSet executeQueryWithParams(String query, Object[] params) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Set parameters
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        return preparedStatement.executeQuery();
    }

    /**
     * Executes an update with parameters.
     *
     * @param query the SQL query to execute
     * @param params the parameters to set in the query
     * @return int the number of rows affected by the query
     * @throws SQLException if a database access error occurs
     */
    public static int executeUpdateWithParams(String query, Object[] params) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Set parameters
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        return preparedStatement.executeUpdate();
    }
}
