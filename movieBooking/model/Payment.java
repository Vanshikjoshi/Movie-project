package movieBooking.model;

public class Payment {

    private String paymentMethod; 
    private int seats;
    private double pricePerSeat = 150; 

    public Payment(String paymentMethod, int seats) {
        this.paymentMethod = paymentMethod;
        this.seats = seats;
    }

    // calculate total bill
    public double calculateTotal() {
        return seats * pricePerSeat;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // method overriding example (optional use)
    public String toString() {
        return "Payment Method: " + paymentMethod +
               " | Total Bill: ₹" + calculateTotal();
    }
}