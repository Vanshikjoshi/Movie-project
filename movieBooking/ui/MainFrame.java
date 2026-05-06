package movieBooking.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Movie Booking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton bookButton = new JButton("Book Ticket");

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookingForm();
            }
        });

        add(bookButton);
        setLayout(null);
        bookButton.setBounds(120, 100, 150, 40);

        setVisible(true);
    }
}
