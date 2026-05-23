package movieBooking.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame
        implements ActionListener {

    JButton bookButton, exitButton;

    public MainFrame() {

        setTitle("Movie Booking Dashboard");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel();

        main.setBackground(new Color(10, 15, 30));

        main.setLayout(new BorderLayout());

        add(main);

        JLabel heading =
                new JLabel(
                        "MOVIE BOOKING SYSTEM",
                        JLabel.CENTER);

        heading.setForeground(Color.WHITE);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36));

        heading.setBorder(
                BorderFactory.createEmptyBorder(
                        50,
                        0,
                        20,
                        0));

        main.add(heading, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();

        centerPanel.setBackground(
                new Color(10, 15, 30));

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS));

        JLabel sub =
                new JLabel(
                        "Book Your Favourite Movies");

        sub.setForeground(Color.LIGHT_GRAY);

        sub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        20));

        sub.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        centerPanel.add(sub);

        centerPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 50)));

        bookButton =
                new JButton("Book Ticket");

        styleButton(
                bookButton,
                new Color(0, 140, 255));

        centerPanel.add(bookButton);

        centerPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)));

        exitButton =
                new JButton("Exit");

        styleButton(
                exitButton,
                new Color(220, 70, 70));

        centerPanel.add(exitButton);

        main.add(centerPanel,
                BorderLayout.CENTER);

        bookButton.addActionListener(this);

        exitButton.addActionListener(this);

        setVisible(true);
    }

    private void styleButton(
            JButton btn,
            Color color) {

        btn.setBackground(color);

        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        btn.setMaximumSize(
                new Dimension(250, 50));

        btn.setAlignmentX(
                Component.CENTER_ALIGNMENT);
    }

    public void actionPerformed(
            ActionEvent e) {

        if (e.getSource()
                == bookButton) {

            bookButton.setEnabled(false);

            BookingForm form =
                    new BookingForm(this);

            form.addWindowListener(
                    new WindowAdapter() {

                public void windowClosed(
                        WindowEvent e) {

                    bookButton.setEnabled(true);
                }
            });
        }

        if (e.getSource()
                == exitButton) {

            System.exit(0);
        }
    }
}