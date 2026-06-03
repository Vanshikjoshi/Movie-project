package movieBooking.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MovieTicketLogin extends JFrame
        implements ActionListener {

    JTextField userField;

    JPasswordField passField;

    JButton loginButton, signupButton;

    JLabel message;

    public MovieTicketLogin() {

        setTitle("Movie Booking Login");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        mainPanel.setBackground(
                new Color(10, 15, 30));

        mainPanel.setLayout(
                new GridBagLayout());

        add(mainPanel);

        JPanel card = new JPanel();

        card.setPreferredSize(
                new Dimension(400, 420));

        card.setBackground(
                new Color(25, 25, 40));

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS));

        card.setBorder(
                new EmptyBorder(
                        35,
                        35,
                        35,
                        35));

        JLabel title =
                new JLabel("MOVIE BOOKING");

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        JLabel sub =
                new JLabel("Login to continue");

        sub.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        sub.setForeground(
                Color.LIGHT_GRAY);

        sub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        card.add(title);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        card.add(sub);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 40)));

        userField = new JTextField();

        userField.setMaximumSize(
                new Dimension(320, 45));

        userField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16));

        userField.setBorder(
                BorderFactory.createTitledBorder(
                        "Username"));

        card.add(userField);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 20)));

        passField =
                new JPasswordField();

        passField.setMaximumSize(
                new Dimension(320, 45));

        passField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16));

        passField.setBorder(
                BorderFactory.createTitledBorder(
                        "Password"));

        card.add(passField);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 30)));

        loginButton =
                new JButton("Login");

        styleButton(
                loginButton,
                new Color(0, 140, 255));

        loginButton.addActionListener(this);

        card.add(loginButton);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 15)));

        signupButton =
                new JButton("Create Account");

        styleButton(
                signupButton,
                new Color(80, 80, 80));

        signupButton.addActionListener(this);

        card.add(signupButton);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 15)));

        message = new JLabel("");

        message.setForeground(Color.YELLOW);

        message.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        card.add(message);

        mainPanel.add(card);

        setVisible(true);
    }

    private void styleButton(
            JButton btn,
            Color color) {

        btn.setBackground(color);

        btn.setForeground(Color.WHITE);

        btn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16));

        btn.setFocusPainted(false);

        btn.setMaximumSize(
                new Dimension(320, 45));
    }

    public void actionPerformed(
            ActionEvent e) {

        if (e.getSource()
                == loginButton) {

            String user =
                    userField.getText().trim();

            String pass =
                    String.valueOf(
                            passField.getPassword());

            if (user.equals("")
                    || pass.equals("")) {

                message.setText(
                        "Fields Cannot Be Empty!");

                return;
            }

            try {

                Connection con =
                        DBConnection.getConnection();

                String query =
                        "SELECT * FROM users "
                                + "WHERE username=? "
                                + "AND user_password=?";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setString(1, user);

                pst.setString(2, pass);

                ResultSet rs =
                        pst.executeQuery();

                if (rs.next()) {

                    new MainFrame();

                    dispose();

                } else {

                    message.setText(
                            "Invalid Username or Password");
                }

                rs.close();

                pst.close();

                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

                message.setText(
                        "Database Error");
            }
        }

        if (e.getSource()
                == signupButton) {

            new SignupPage();

            dispose();
        }
    }

}