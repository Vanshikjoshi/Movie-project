package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;

// ---------------- LOGIN PAGE ----------------

public class MovieTicketLogin extends Frame implements ActionListener {

    Label title, userLabel, passLabel, signupMsg, message;
    TextField userField, passField;
    Button loginButton, signupButton;

    // Shared account details
    static String savedUser = "";
    static String savedPass = "";

    public MovieTicketLogin() {

        setTitle("Login Page");
        setSize(450, 400);
        setLayout(null);

        // Background Color
        setBackground(new Color(20, 40, 90));

        // Title
        title = new Label("LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(170, 50, 150, 40);
        add(title);

        // Username
        userLabel = new Label("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(70, 120, 100, 30);
        add(userLabel);

        userField = new TextField();
        userField.setBounds(180, 120, 180, 35);
        userField.setBackground(new Color(230, 240, 255));
        add(userField);

        // Password
        passLabel = new Label("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(70, 180, 100, 30);
        add(passLabel);

        passField = new TextField();
        passField.setEchoChar('*');
        passField.setBounds(180, 180, 180, 35);
        passField.setBackground(new Color(230, 240, 255));
        add(passField);

        // Login Button
        loginButton = new Button("Login");
        loginButton.setBounds(170, 250, 110, 40);
        loginButton.setBackground(new Color(0, 120, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(this);
        add(loginButton);

        // Bottom Message
        signupMsg = new Label("Don't have an account?");
        signupMsg.setForeground(Color.WHITE);
        signupMsg.setBounds(110, 320, 150, 30);
        add(signupMsg);

        // Signup Button
        signupButton = new Button("Sign Up");
        signupButton.setBounds(260, 320, 90, 30);
        signupButton.setBackground(new Color(0, 150, 255));
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(this);
        add(signupButton);

        // Message Label
        message = new Label("");
        message.setForeground(Color.YELLOW);
        message.setFont(new Font("Arial", Font.BOLD, 13));
        message.setBounds(140, 290, 200, 20);
        add(message);

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // LOGIN
        if (e.getSource() == loginButton) {

            String user = userField.getText();
            String pass = passField.getText();

            if (user.equals(savedUser) && pass.equals(savedPass)
                    && !savedUser.equals("")) {

                message.setText("Login Successful!");

                new MainFrame();
                dispose();

            } else {

                message.setText("Login Failed!");
            }
        }

        // OPEN SIGNUP PAGE
        if (e.getSource() == signupButton) {

            new SignupPage();
            dispose();
        }
    }

    public static void main(String[] args) {
        new MovieTicketLogin();
    }
}

// ---------------- SIGNUP PAGE ----------------

class SignupPage extends Frame implements ActionListener {

    Label title, userLabel, passLabel, message;
    TextField userField, passField;
    Button createButton, backButton;

    SignupPage() {

        setTitle("Signup Page");
        setSize(450, 400);
        setLayout(null);

        // Background Color
        setBackground(new Color(10, 60, 120));

        // Title
        title = new Label("SIGN UP");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(160, 50, 150, 40);
        add(title);

        // Username
        userLabel = new Label("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(70, 120, 100, 30);
        add(userLabel);

        userField = new TextField();
        userField.setBounds(180, 120, 180, 35);
        userField.setBackground(new Color(230, 240, 255));
        add(userField);

        // Password
        passLabel = new Label("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(70, 180, 100, 30);
        add(passLabel);

        passField = new TextField();
        passField.setEchoChar('*');
        passField.setBounds(180, 180, 180, 35);
        passField.setBackground(new Color(230, 240, 255));
        add(passField);

        // Create Account Button
        createButton = new Button("Create Account");
        createButton.setBounds(120, 250, 130, 40);
        createButton.setBackground(new Color(0, 120, 255));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 13));
        createButton.addActionListener(this);
        add(createButton);

        // Back Button
        backButton = new Button("Back");
        backButton.setBounds(270, 250, 80, 40);
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        // Message
        message = new Label("");
        message.setForeground(Color.YELLOW);
        message.setFont(new Font("Arial", Font.BOLD, 13));
        message.setBounds(110, 310, 250, 30);
        add(message);

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Create Account
        if (e.getSource() == createButton) {

            String username = userField.getText();
            String password = passField.getText();

            if (username.equals("") || password.equals("")) {

                message.setText("Fields Cannot Be Empty!");
            }

            else {

                MovieTicketLogin.savedUser = username;
                MovieTicketLogin.savedPass = password;

                message.setText("Account Created!");
                new MainFrame();
                dispose();
            }
        }

        // Back to Login Page
        if (e.getSource() == backButton) {

            new MovieTicketLogin();
            dispose();
        }
    }
}