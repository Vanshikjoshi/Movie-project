package movieBooking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import movieBooking.model.Booking;
import movieBooking.ui.DBConnection;
import movieBooking.util.CustomException;

public class BookingService {

    public void bookTicket(
            String movie,
            int seats,
            String user)
            throws CustomException {

        if (movie.trim().equals("")
                || user.trim().equals("")) {

            throw new CustomException(
                    "Fields Cannot Be Empty!");
        }

        if (seats <= 0) {

            throw new CustomException(
                    "Invalid Number Of Seats!");
        }

        Connection con = null;

        PreparedStatement pst = null;

        try {

            con =
                    DBConnection.getConnection();

            if (con == null) {

                throw new CustomException(
                        "Database Connection Failed!");
            }

            Booking booking =
                    new Booking(
                            movie,
                            seats,
                            user);

            String query =
                    "INSERT INTO bookings(movie_name, seats, user_name) "
                            + "VALUES(?,?,?)";

            pst =
                    con.prepareStatement(query);

            pst.setString(
                    1,
                    booking.getMovieName());

            pst.setInt(
                    2,
                    booking.getSeats());

            pst.setString(
                    3,
                    booking.getUserName());

            pst.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (pst != null)
                    pst.close();

                if (con != null)
                    con.close();

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}