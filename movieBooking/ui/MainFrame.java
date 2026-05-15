package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends Frame implements ActionListener {

    Label heading, subHeading;

    Button bookButton, exitButton;

    public MainFrame() {

        setTitle("Movie Booking System");

        setSize(500, 400);

        setLayout(null);

        setBackground(new Color(15, 25, 55));

        // HEADING

        heading = new Label("MOVIE BOOKING SYSTEM");

        heading.setBounds(80, 70, 350, 40);

        heading.setForeground(Color.WHITE);

        heading.setFont(new Font("Arial", Font.BOLD, 28));

        add(heading);

        // SUB HEADING

        subHeading = new Label("Book Your Favourite Movie Tickets");

        subHeading.setBounds(110, 120, 280, 30);

        subHeading.setForeground(Color.LIGHT_GRAY);

        subHeading.setFont(new Font("Arial", Font.PLAIN, 16));

        add(subHeading);

        // BOOK BUTTON

        bookButton = new Button("Book Ticket");

        bookButton.setBounds(160, 190, 180, 50);

        bookButton.setBackground(new Color(0, 140, 255));

        bookButton.setForeground(Color.WHITE);

        bookButton.setFont(new Font("Arial", Font.BOLD, 16));

        bookButton.addActionListener(this);

        add(bookButton);

        // EXIT BUTTON

        exitButton = new Button("Exit");

        exitButton.setBounds(190, 270, 120, 40);

        exitButton.setBackground(new Color(180, 50, 50));

        exitButton.setForeground(Color.WHITE);

        exitButton.setFont(new Font("Arial", Font.BOLD, 14));

        exitButton.addActionListener(this);

        add(exitButton);

        // WINDOW CLOSE

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {

                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // OPEN BOOKING FORM

        if (e.getSource() == bookButton) {

            new BookingForm(this);
        }

        // EXIT

        if (e.getSource() == exitButton) {

            dispose();
        }
    }
}