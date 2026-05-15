package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;

import movieBooking.model.Payment;
import movieBooking.service.BookingService;
import movieBooking.util.CustomException;

public class BookingForm extends Frame implements ActionListener {

    MainFrame mainFrame;

    Label heading, movieLabel, seatsLabel, userLabel,
            paymentLabel, message;

    Choice movieChoice, paymentChoice;

    TextField seatsField, userField;

    Button bookBtn, cancelBtn;

    public BookingForm(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setTitle("Movie Ticket Booking");

        setSize(500, 420);

        setLayout(null);

        setBackground(new Color(15, 25, 55));

        // HEADING

        heading = new Label("BOOK MOVIE TICKET");

        heading.setBounds(110, 50, 300, 40);

        heading.setForeground(Color.WHITE);

        heading.setFont(new Font("Arial", Font.BOLD, 24));

        add(heading);

        // MOVIE

        movieLabel = new Label("Select Movie:");

        movieLabel.setBounds(60, 120, 120, 30);

        movieLabel.setForeground(Color.WHITE);

        movieLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(movieLabel);

        movieChoice = new Choice();

        movieChoice.add("Avengers");
        movieChoice.add("Interstellar");
        movieChoice.add("Pushpa 2");
        movieChoice.add("KGF 2");
        movieChoice.add("Jawan");

        movieChoice.setBounds(220, 120, 180, 30);

        add(movieChoice);

        // SEATS

        seatsLabel = new Label("Number of Seats:");

        seatsLabel.setBounds(60, 170, 140, 30);

        seatsLabel.setForeground(Color.WHITE);

        seatsLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(seatsLabel);

        seatsField = new TextField();

        seatsField.setBounds(220, 170, 180, 30);

        add(seatsField);

        // USERNAME

        userLabel = new Label("Username:");

        userLabel.setBounds(60, 220, 120, 30);

        userLabel.setForeground(Color.WHITE);

        userLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(userLabel);

        userField = new TextField();

        userField.setBounds(220, 220, 180, 30);

        add(userField);

        // PAYMENT

        paymentLabel = new Label("Payment:");

        paymentLabel.setBounds(60, 270, 120, 30);

        paymentLabel.setForeground(Color.WHITE);

        paymentLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(paymentLabel);

        paymentChoice = new Choice();

        paymentChoice.add("UPI");
        paymentChoice.add("Card");
        paymentChoice.add("Cash");

        paymentChoice.setBounds(220, 270, 180, 30);

        add(paymentChoice);

        // BOOK BUTTON

        bookBtn = new Button("Confirm Booking");

        bookBtn.setBounds(100, 330, 140, 40);

        bookBtn.setBackground(new Color(0, 140, 255));

        bookBtn.setForeground(Color.WHITE);

        bookBtn.setFont(new Font("Arial", Font.BOLD, 14));

        bookBtn.addActionListener(this);

        add(bookBtn);

        // CANCEL BUTTON

        cancelBtn = new Button("Cancel");

        cancelBtn.setBounds(270, 330, 100, 40);

        cancelBtn.setBackground(new Color(180, 50, 50));

        cancelBtn.setForeground(Color.WHITE);

        cancelBtn.setFont(new Font("Arial", Font.BOLD, 14));

        cancelBtn.addActionListener(this);

        add(cancelBtn);

        // MESSAGE LABEL

        message = new Label("");

        message.setBounds(120, 380, 300, 30);

        message.setForeground(Color.YELLOW);

        message.setFont(new Font("Arial", Font.BOLD, 13));

        add(message);

        // WINDOW CLOSE

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {

                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // BOOK BUTTON

        if (e.getSource() == bookBtn) {

            try {

                String movie = movieChoice.getSelectedItem();

                int seats = Integer.parseInt(
                        seatsField.getText());

                String user = userField.getText().trim();

                String paymentMethod = paymentChoice.getSelectedItem();

                Payment payment = new Payment(paymentMethod, seats);

                double total = payment.calculateTotal();

                BookingService service = new BookingService();

                service.bookTicket(movie, seats, user);

                Dialog successDialog = new Dialog(this,
                        "Booking Successful",
                        true);

                successDialog.setSize(350, 220);

                successDialog.setLayout(null);

                successDialog.setBackground(
                        new Color(20, 40, 90));

                Label successMsg = new Label(
                        "Ticket Booked Successfully!");

                successMsg.setBounds(60, 60, 240, 30);

                successMsg.setForeground(Color.WHITE);

                successMsg.setFont(
                        new Font("Arial",
                                Font.BOLD,
                                16));

                successDialog.add(successMsg);

                Label billMsg = new Label(
                        "Total Bill: ₹" + total);

                billMsg.setBounds(95, 100, 180, 30);

                billMsg.setForeground(Color.YELLOW);

                billMsg.setFont(
                        new Font("Arial",
                                Font.BOLD,
                                15));

                successDialog.add(billMsg);

                Button okBtn = new Button("OK");

                okBtn.setBounds(130, 150, 80, 30);

                successDialog.add(okBtn);

                okBtn.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        successDialog.dispose();

                        mainFrame.dispose();

                        dispose();
                    }
                });

                successDialog.setVisible(true);

            } catch (NumberFormatException ex) {

                message.setText(
                        "Enter Valid Number!");

            } catch (CustomException ex) {

                message.setText(
                        ex.getMessage());

            } catch (Exception ex) {

                message.setText(
                        "Booking Failed!");
            }
        }

        // CANCEL BUTTON

        if (e.getSource() == cancelBtn) {

            dispose();
        }
    }
}