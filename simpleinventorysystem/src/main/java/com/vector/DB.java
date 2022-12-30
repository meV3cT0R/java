package com.vector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String driverClassName = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/vector";
        String username = "vector";
        String password = "123456789";
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, username, password);
    }
}
