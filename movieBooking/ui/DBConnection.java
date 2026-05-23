package movieBooking.ui;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;

import java.io.FileInputStream;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {

            Properties props =
                    new Properties();

            FileInputStream fis =
                    new FileInputStream(
                            "db.properties");

            props.load(fis);

            String url =
                    props.getProperty(
                            "db.url");

            String username =
                    props.getProperty(
                            "db.username");

            String password =
                    props.getProperty(
                            "db.password");

            Class.forName(
                    "com.mysql.cj.jdbc.Driver");

            con =
                    DriverManager.getConnection(
                            url,
                            username,
                            password);

            System.out.println(
                    "Database Connected");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return con;
    }
}