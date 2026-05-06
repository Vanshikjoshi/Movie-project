import java.util.Scanner;
import movie.Movie;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Movie[] movies = {
            new Movie("Inception", 2010, 8.8),
            new Movie("Interstellar", 2014, 8.6),
            new Movie("Avengers", 2012, 8.0)
        };

        Movie selectedMovie = null;

        System.out.println("----------Welcome to the Movie Booking Project----------");

        int choice = 0;

        while (choice != 5) {
            System.out.println();
            System.out.println("1- View movies");
            System.out.println("2- Select movie");
            System.out.println("3- Book seats");
            System.out.println("4- Payment");
            System.out.println("5- Exit");

            System.out.print("Enter a number between 1-5: ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Viewing movies...");
                    for (int i = 0; i < movies.length; i++) {
                        System.out.println((i + 1) + ". " + 
                            movies[i].getName() + " (" + 
                            movies[i].getYear() + ") Rating: " + 
                            movies[i].getRating());
                    }
                    break;

                case 2:
                    System.out.print("Enter movie number: ");
                    int mChoice = sc.nextInt();

                    if (mChoice >= 1 && mChoice <= movies.length) {
                        selectedMovie = movies[mChoice - 1];
                        System.out.println("You selected: " + selectedMovie.getName());
                    } else {
                        System.out.println("Invalid movie!");
                    }
                    break;

                case 3:
                    if (selectedMovie == null) {
                        System.out.println("Please select a movie first!");
                        break;
                    }
                    System.out.println("Booking seats for " + selectedMovie.getName());
                    break;

                case 4:
                    if (selectedMovie == null) {
                        System.out.println("No booking found!");
                        break;
                    }
                    System.out.println("Processing payment for " + selectedMovie.getName());
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}