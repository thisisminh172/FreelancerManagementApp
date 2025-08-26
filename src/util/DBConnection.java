package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String url = "jdbc:mysql://localhost:3306/freelancermanagementdb";
    private static String username = "root";
    private static String password = "admin";
    private static Connection conn = null;

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.err.println("Error connecting to the database: " + e.getMessage());
                conn.close();
            }
        }
        return conn;
    }
}
