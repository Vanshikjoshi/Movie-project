package movieBooking.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignupPage extends JFrame
        implements ActionListener {

    JTextField userField;

    JPasswordField passField;

    JButton createButton, backButton;

    JLabel message;

    public SignupPage() {

        setTitle("Create Account");

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
                new Dimension(400, 430));

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
                new JLabel("CREATE ACCOUNT");

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26));

        JLabel sub =
                new JLabel("Signup to continue");

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

        createButton =
                new JButton("Create Account");

        styleButton(
                createButton,
                new Color(0, 140, 255));

        createButton.addActionListener(this);

        card.add(createButton);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 15)));

        backButton =
                new JButton("Back");

        styleButton(
                backButton,
                new Color(90, 90, 90));

        backButton.addActionListener(this);

        card.add(backButton);

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
                == createButton) {

            String username =
                    userField.getText().trim();

            String password =
                    String.valueOf(
                            passField.getPassword());

            if (username.equals("")
                    || password.equals("")) {

                message.setText(
                        "Fields Cannot Be Empty!");

                return;
            }

            try {

                Connection con =
                        DBConnection.getConnection();

                String query =
                        "INSERT INTO users(username, user_password) "
                                + "VALUES(?, ?)";

                PreparedStatement pst =
                        con.prepareStatement(query);

                pst.setString(1, username);

                pst.setString(2, password);

                int rows =
                        pst.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Account Created Successfully!");

                    new MainFrame();

                    dispose();
                }

                pst.close();

                con.close();

            } catch (
                    SQLIntegrityConstraintViolationException ex) {

                message.setText(
                        "Username Already Exists!");

            } catch (Exception ex) {

                ex.printStackTrace();

                message.setText(
                        "Database Error");
            }
        }

        if (e.getSource()
                == backButton) {

            new MovieTicketLogin();

            dispose();
        }
    }
}