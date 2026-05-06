package movieBooking.model;

public class Movie {
    private String name;
    private int availableSeats;

    public Movie(String name, int seats) {
        this.name = name;
        this.availableSeats = seats;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int seats) {
        this.availableSeats = seats;
    }
}