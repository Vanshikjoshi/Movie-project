package movieBooking.model;

public class User {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    public void displayRole() {
        System.out.println("Regular User");
    }

    public String getName() {
        return name;
    }
}