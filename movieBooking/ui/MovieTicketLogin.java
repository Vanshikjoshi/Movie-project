package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;

public class MovieTicketLogin extends Frame implements ActionListener {

    // Login components
    Label loginTitle, emailPhoneLabel, loginPasswordLabel;
    TextField emailPhoneField, loginPasswordField;
    Button loginButton, signupButton;

    // Signup components
    Label signupTitle, signupUsernameLabel, signupPasswordLabel;
    TextField signupUsernameField, signupPasswordField;
    Button createAccountButton;

    private String savedUser = null;
    private String savedPass = null;

    public MovieTicketLogin() {

        setTitle("Movie Ticket Booking System");
        setSize(500, 500);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        // ---------------- LOGIN ----------------
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
        signupButton.setBounds(250, 210, 120, 35);

        // ---------------- SIGNUP ----------------
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
        createAccountButton.setBounds(180, 420, 140, 30);

        // listeners
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
        createAccountButton.addActionListener(this);

        // add components
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

        // window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ---------------- LOGIN ----------------
        if (e.getSource() == loginButton) {

            String user = emailPhoneField.getText();
            String pass = loginPasswordField.getText();

            if (savedUser == null || savedPass == null) {
                System.out.println("No account exists. Please sign up first.");
                emailPhoneField.setText("");
                loginPasswordField.setText("");
                return;
            }

            if (pass.equals(savedPass)) {
                System.out.println("Login Successful");
                emailPhoneField.setText("");
                loginPasswordField.setText("");

                new MainFrame();
                dispose();

            } else {
                System.out.println("Invalid credentials");
                emailPhoneField.setText("");
                loginPasswordField.setText("");
            }
        }

        // ---------------- SIGNUP ----------------
        else if (e.getSource() == createAccountButton) {

            String username = signupUsernameField.getText();
            String password = signupPasswordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Fields cannot be empty!");
                return;
            }

            savedUser = username;
            savedPass = password;

            System.out.println("Account Created Successfully!");

            // clear fields
            signupUsernameField.setText("");
            signupPasswordField.setText("");
        }

        // ---------------- TOGGLE MESSAGE ONLY ----------------
        else if (e.getSource() == signupButton) {
            System.out.println("Signup section already visible below login.");
        }
    }
}