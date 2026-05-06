package movieBooking.model;

public class Booking {
    private String movieName;
    private int seats;
    private String userName;

    public Booking(String movieName, int seats, String userName) {
        this.movieName = movieName;
        this.seats = seats;
        this.userName = userName;
    }

    public String toString() {
        return "Movie: " + movieName + " | Seats: " + seats + " | User: " + userName;
    }
}
