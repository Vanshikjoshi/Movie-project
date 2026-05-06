package movieBooking.threads;

public class BookingThread extends Thread {

    public void run() {
        try {
            System.out.println("Processing booking...");
            Thread.sleep(1000);
            System.out.println("Booking completed!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
