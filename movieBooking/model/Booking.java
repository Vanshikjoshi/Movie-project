package movieBooking.model;

public class Booking {

    private String movieName;

    private int seats;

    private String userName;

    public Booking(
            String movieName,
            int seats,
            String userName) {

        this.movieName = movieName;

        this.seats = seats;

        this.userName = userName;
    }

    public String getMovieName() {

        return movieName;
    }

    public int getSeats() {

        return seats;
    }

    public String getUserName() {

        return userName;
    }
}