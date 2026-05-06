package movieBooking.ui;

import javax.swing.*;

import movieBooking.model.Payment;
import movieBooking.service.BookingService;
import movieBooking.util.CustomException;
import movieBooking.threads.BookingThread;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingForm extends JFrame {

    JTextField movieField, seatsField, userField;
    JButton bookBtn;

    public BookingForm() {
        setTitle("Book Ticket");
        setSize(400, 300);
        setLayout(null);

        JLabel movieLabel = new JLabel("Movie:");
        movieLabel.setBounds(30, 30, 100, 30);
        add(movieLabel);

        movieField = new JTextField();
        movieField.setBounds(150, 30, 150, 30);
        add(movieField);

        JLabel seatsLabel = new JLabel("Seats:");
        seatsLabel.setBounds(30, 80, 100, 30);
        add(seatsLabel);

        seatsField = new JTextField();
        seatsField.setBounds(150, 80, 150, 30);
        add(seatsField);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(30, 130, 100, 30);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(150, 130, 150, 30);
        add(userField);

        bookBtn = new JButton("Confirm Booking");
        bookBtn.setBounds(120, 180, 150, 30);
        add(bookBtn);

        bookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String movie = movieField.getText();
                    int seats = Integer.parseInt(seatsField.getText());
                    String user = userField.getText();

                    String paymentMethod = JOptionPane.showInputDialog(
                            "Enter Payment Method (Card / Cash / UPI):");

                    Payment payment = new Payment(paymentMethod, seats);
                    double total = payment.calculateTotal();

                    BookingService service = new BookingService();
                    service.bookTicket(movie, seats, user);

                    BookingThread t = new BookingThread();
                    t.start();

                    JOptionPane.showMessageDialog(null,
                            "Booking Successful!\nTotal Bill: ₹" + total +
                                    "\nPayment: " + paymentMethod);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Enter valid number!");
                } catch (CustomException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        setVisible(true);
    }
}
