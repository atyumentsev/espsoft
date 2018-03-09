package com.espsoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/espsoft?user=espsoft&password=123456");
        }
        return connection;
    }
}
