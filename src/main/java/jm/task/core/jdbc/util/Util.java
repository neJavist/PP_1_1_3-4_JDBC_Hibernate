package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public Connection getJDBCConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/preproject", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public SessionFactory getHibernateConnection() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/preproject");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "admin");
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.HBM2DDL_AUTO, "create-drop");
        properties.put(Environment.SHOW_SQL, "true");


        return new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
