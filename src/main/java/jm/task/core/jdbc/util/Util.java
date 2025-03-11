package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/preproject", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
