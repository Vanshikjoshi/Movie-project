package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MovieTicketLogin extends Frame implements ActionListener {

    Label title, userLabel, passLabel, signupMsg, message;

    TextField userField, passField;

    Button loginButton, signupButton;

    public MovieTicketLogin() {

        setTitle("Login Page");
        setSize(450, 400);
        setLayout(null);

        setBackground(new Color(20, 40, 90));

        // TITLE

        title = new Label("LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(170, 50, 150, 40);
        add(title);

        // USERNAME

        userLabel = new Label("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(70, 120, 100, 30);
        add(userLabel);

        userField = new TextField();
        userField.setBounds(180, 120, 180, 35);
        userField.setBackground(new Color(230, 240, 255));
        add(userField);

        // PASSWORD

        passLabel = new Label("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(70, 180, 100, 30);
        add(passLabel);

        passField = new TextField();
        passField.setEchoChar('*');
        passField.setBounds(180, 180, 180, 35);
        passField.setBackground(new Color(230, 240, 255));
        add(passField);

        // LOGIN BUTTON

        loginButton = new Button("Login");
        loginButton.setBounds(170, 250, 110, 40);
        loginButton.setBackground(new Color(0, 120, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(this);
        add(loginButton);

        // SIGNUP MESSAGE

        signupMsg = new Label("Don't have an account?");
        signupMsg.setForeground(Color.WHITE);
        signupMsg.setBounds(110, 320, 150, 30);
        add(signupMsg);

        // SIGNUP BUTTON

        signupButton = new Button("Sign Up");
        signupButton.setBounds(260, 320, 90, 30);
        signupButton.setBackground(new Color(0, 150, 255));
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(this);
        add(signupButton);

        // MESSAGE LABEL

        message = new Label("");
        message.setForeground(Color.YELLOW);
        message.setFont(new Font("Arial", Font.BOLD, 13));
        message.setBounds(120, 290, 250, 20);
        add(message);

        // CLOSE WINDOW

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

            String user = userField.getText().trim();
            String pass = passField.getText().trim();

            if (user.equals("") || pass.equals("")) {

                message.setText("Fields Cannot Be Empty!");
                return;
            }

            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {

                con = DBConnection.getConnection();

                if (con == null) {

                    message.setText("Database Connection Failed!");
                    return;
                }

                String query = "SELECT * FROM users WHERE username=? AND user_password=?";

                pst = con.prepareStatement(query);

                pst.setString(1, user);
                pst.setString(2, pass);

                rs = pst.executeQuery();

                if (rs.next()) {

                    message.setText("Login Successful!");

                    new MainFrame();

                    dispose();

                } else {

                    message.setText("Invalid Username or Password!");
                }

            } catch (Exception ex) {

                message.setText("Database Error!");
                ex.printStackTrace();

            } finally {

                try {

                    if (rs != null)
                        rs.close();

                    if (pst != null)
                        pst.close();

                    if (con != null)
                        con.close();

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
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

// ================= SIGNUP PAGE =================

class SignupPage extends Frame implements ActionListener {

    Label title, userLabel, passLabel, message;

    TextField userField, passField;

    Button createButton, backButton;

    SignupPage() {

        setTitle("Signup Page");
        setSize(450, 400);
        setLayout(null);

        setBackground(new Color(10, 60, 120));

        // TITLE

        title = new Label("SIGN UP");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(160, 50, 150, 40);
        add(title);

        // USERNAME

        userLabel = new Label("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(70, 120, 100, 30);
        add(userLabel);

        userField = new TextField();
        userField.setBounds(180, 120, 180, 35);
        userField.setBackground(new Color(230, 240, 255));
        add(userField);

        // PASSWORD

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

        // CREATE BUTTON

        createButton = new Button("Create Account");
        createButton.setBounds(120, 250, 130, 40);
        createButton.setBackground(new Color(0, 120, 255));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 13));
        createButton.addActionListener(this);
        add(createButton);

        // BACK BUTTON

        backButton = new Button("Back");
        backButton.setBounds(270, 250, 80, 40);
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        // MESSAGE

        message = new Label("");
        message.setForeground(Color.YELLOW);
        message.setFont(new Font("Arial", Font.BOLD, 13));
        message.setBounds(100, 310, 250, 30);
        add(message);

        // CLOSE WINDOW

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {

                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // CREATE ACCOUNT

        if (e.getSource() == createButton) {

            String username = userField.getText().trim();
            String password = passField.getText().trim();

            if (username.equals("") || password.equals("")) {

                message.setText("Fields Cannot Be Empty!");

                return;
            }

            Connection con = null;
            PreparedStatement pst = null;

            try {

                con = DBConnection.getConnection();

                if (con == null) {

                    message.setText("Database Connection Failed!");
                    return;
                }

                String query = "INSERT INTO users(username, user_password) VALUES(?, ?)";

                pst = con.prepareStatement(query);

                pst.setString(1, username);
                pst.setString(2, password);

                int rows = pst.executeUpdate();

                if (rows > 0) {

                    message.setText("Account Created!");

                    new MainFrame();

                    dispose();
                }

            } catch (SQLIntegrityConstraintViolationException ex) {

                message.setText("Username Already Exists!");

            } catch (Exception ex) {

                message.setText("Error Creating Account!");

                ex.printStackTrace();

            } finally {

                try {

                    if (pst != null)
                        pst.close();

                    if (con != null)
                        con.close();

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }

        // BACK BUTTON

        if (e.getSource() == backButton) {

            new MovieTicketLogin();

            dispose();
        }
    }
}