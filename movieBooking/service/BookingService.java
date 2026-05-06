package movieBooking.service;

import movieBooking.model.Booking;
import movieBooking.util.FileHandler;
import movieBooking.util.CustomException;

public class BookingService {

    public void bookTicket(String movie, int seats, String user) throws CustomException {
        if (movie.isEmpty() || user.isEmpty()) {
            throw new CustomException("Fields cannot be empty!");
        }

        if (seats <= 0) {
            throw new CustomException("Invalid number of seats!");
        }

        Booking booking = new Booking(movie, seats, user);
        FileHandler.saveToFile(booking.toString());
    }
}
