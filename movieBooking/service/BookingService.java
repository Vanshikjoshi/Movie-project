package movieBooking.service;

import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

public class BookingService {

    private Connection getConnection() throws Exception {

        Properties props = new Properties();

        FileInputStream fis = new FileInputStream("db.properties");
        props.load(fis);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String pass = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, pass);
    }

    public void bookTicket(String movie, String user, String seatType,
                           int seatNumber, String payment) {

        String sql = "INSERT INTO bookings " +
                "(movie_name, user_name, seat_type, seat_number, payment_method) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, movie);
            ps.setString(2, user);
            ps.setString(3, seatType);
            ps.setInt(4, seatNumber);
            ps.setString(5, payment);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}