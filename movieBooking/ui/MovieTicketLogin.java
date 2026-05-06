package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;

import movieBooking.ui.MainFrame;

public class MovieTicketLogin extends Frame implements ActionListener {

    // Login Components
    Label loginTitle, emailPhoneLabel, loginPasswordLabel;
    TextField emailPhoneField, loginPasswordField;
    Button loginButton, signupButton;

    // Signup Components
    Label signupTitle, signupUsernameLabel, signupPasswordLabel;
    TextField signupUsernameField, signupPasswordField;
    Button createAccountButton;

    public MovieTicketLogin() {

        setTitle("Movie Ticket Booking System");
        setSize(500, 500);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        // ---------- LOGIN SECTION ----------

        loginTitle = new Label("LOGIN");
        loginTitle.setBounds(220, 50, 100, 30);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 18));

        emailPhoneLabel = new Label("Email / Phone:");
        emailPhoneLabel.setBounds(70, 100, 120, 30);

        emailPhoneField = new TextField();
        emailPhoneField.setBounds(200, 100, 180, 30);

        loginPasswordLabel = new Label("Password:");
        loginPasswordLabel.setBounds(70, 150, 120, 30);

        loginPasswordField = new TextField();
        loginPasswordField.setEchoChar('*');
        loginPasswordField.setBounds(200, 150, 180, 30);

        loginButton = new Button("Login");
        loginButton.setBounds(150, 210, 80, 35);

        signupButton = new Button("Go to Signup");
        signupButton.setBounds(250, 210, 100, 35);

        // ---------- SIGNUP SECTION ----------

        signupTitle = new Label("SIGN UP");
        signupTitle.setBounds(210, 280, 100, 30);
        signupTitle.setFont(new Font("Arial", Font.BOLD, 18));

        signupUsernameLabel = new Label("Username:");
        signupUsernameLabel.setBounds(70, 330, 120, 30);

        signupUsernameField = new TextField();
        signupUsernameField.setBounds(200, 330, 180, 30);

        signupPasswordLabel = new Label("Password:");
        signupPasswordLabel.setBounds(70, 370, 120, 30);

        signupPasswordField = new TextField();
        signupPasswordField.setEchoChar('*');
        signupPasswordField.setBounds(200, 370, 180, 30);

        createAccountButton = new Button("Create Account");
        createAccountButton.setBounds(180, 420, 120, 30);

        // Add listeners
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
        createAccountButton.addActionListener(this);

        // Add Components
        add(loginTitle);
        add(emailPhoneLabel);
        add(emailPhoneField);
        add(loginPasswordLabel);
        add(loginPasswordField);
        add(loginButton);
        add(signupButton);

        add(signupTitle);
        add(signupUsernameLabel);
        add(signupUsernameField);
        add(signupPasswordLabel);
        add(signupPasswordField);
        add(createAccountButton);

        // Close Window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    // Button Actions
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            System.out.println("Login Successful");
            new MainFrame(); // go to booking system
            dispose();
        }

        else if (e.getSource() == createAccountButton) {

            String username = signupUsernameField.getText();
            String password = signupPasswordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Fields cannot be empty!");
                return;
            }

            System.out.println("Account Created Successfully!");

            // Clear signup fields
            signupUsernameField.setText("");
            signupPasswordField.setText("");

            // Stay on same page (simulate going back to login)
        }

        else if (e.getSource() == signupButton) {
            System.out.println("Signup Section");
        }
    }
}