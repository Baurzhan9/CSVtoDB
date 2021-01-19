package main.java;

import java.sql.*;
import java.util.List;

public class DatabaseConnection {
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    public String getDbUrl() {
        return dbUrl;
    }
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
    public String getDbUsername() {
        return dbUsername;
    }
    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }
    public String getDbPassword() {
        return dbPassword;
    }
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void init() throws SQLException {
        this.setConnection();
    }

    public void setConnection() throws SQLException {
        connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        statement = connection.createStatement();
    }

    public void createTable(String sql) throws SQLException, NullPointerException {
        statement.execute(sql);
    }

    public void updateData(String TableName, String column, String array) throws SQLException {
        statement.execute("INSERT INTO " + TableName + "(" + column + ")" + " VALUES " + "(" +  array + ")" + ";");
    }

    public void destroy() throws SQLException {
        this.closeConnections();
    }

    public void closeConnections() throws SQLException {
        rs.close();
        connection.close();
    }

}

