import java.awt.*;
import java.awt.event.*;

public class MovieTicketLogin extends Frame implements ActionListener {

    // Login Components
    Label loginTitle, emailPhoneLabel, loginPasswordLabel;
    TextField emailPhoneField, loginPasswordField;
    Button loginButton, signupButton;

    // Signup Components
    Label signupTitle, signupUsernameLabel, signupPasswordLabel;
    TextField signupUsernameField, signupPasswordField;
    Button createAccountButton;

    MovieTicketLogin() {

        setTitle("Movie Ticket Booking System");
        setSize(500, 500);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        // ---------- LOGIN SECTION ----------

        loginTitle = new Label("LOGIN");
        loginTitle.setBounds(220, 50, 100, 30);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 18));

        emailPhoneLabel = new Label("Email / Phone:");
        emailPhoneLabel.setBounds(70, 100, 100, 30);

        emailPhoneField = new TextField();
        emailPhoneField.setBounds(200, 100, 180, 30);

        loginPasswordLabel = new Label("Password:");
        loginPasswordLabel.setBounds(70, 150, 100, 30);

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
        signupUsernameLabel.setBounds(70, 330, 100, 30);

        signupUsernameField = new TextField();
        signupUsernameField.setBounds(200, 330, 180, 30);

        signupPasswordLabel = new Label("Password:");
        signupPasswordLabel.setBounds(70, 370, 100, 30);

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

            // Open Preference Page
            new MoviePreferencePage();

            // Close Login Window
            dispose();
        }

        else if (e.getSource() == createAccountButton) {

            System.out.println("Account Created Successfully");

            // Open Preference Page
            new MoviePreferencePage();

            // Close Login Window
            dispose();
        }

        else if (e.getSource() == signupButton) {

            System.out.println("Signup Section");

        }
    }

    public static void main(String[] args) {

        new MovieTicketLogin();

    }
}

// ---------- SECOND SCREEN ----------

class MoviePreferencePage extends Frame implements ActionListener {

    Label titleLabel, genreLabel, languageLabel;

    Checkbox action, comedy, horror, sciFi;
    Checkbox hindi, english, punjabi;

    Button saveButton;

    MoviePreferencePage() {

        setTitle("Movie Preferences");
        setSize(500, 400);
        setLayout(null);
        setBackground(Color.WHITE);

        titleLabel = new Label("Select Your Movie Preferences");
        titleLabel.setBounds(130, 50, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Genre
        genreLabel = new Label("Favorite Genre:");
        genreLabel.setBounds(50, 110, 120, 30);

        action = new Checkbox("Action");
        action.setBounds(200, 110, 100, 30);

        comedy = new Checkbox("Comedy");
        comedy.setBounds(300, 110, 100, 30);

        horror = new Checkbox("Horror");
        horror.setBounds(200, 150, 100, 30);

        sciFi = new Checkbox("Sci-Fi");
        sciFi.setBounds(300, 150, 100, 30);

        // Language
        languageLabel = new Label("Preferred Language:");
        languageLabel.setBounds(50, 220, 140, 30);

        hindi = new Checkbox("Hindi");
        hindi.setBounds(220, 220, 80, 30);

        english = new Checkbox("English");
        english.setBounds(300, 220, 80, 30);

        punjabi = new Checkbox("Punjabi");
        punjabi.setBounds(390, 220, 80, 30);

        // Save Button
        saveButton = new Button("Save Preferences");
        saveButton.setBounds(170, 300, 150, 40);

        saveButton.addActionListener(this);

        // Add Components
        add(titleLabel);

        add(genreLabel);
        add(action);
        add(comedy);
        add(horror);
        add(sciFi);

        add(languageLabel);
        add(hindi);
        add(english);
        add(punjabi);

        add(saveButton);

        // Close Window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("Preferences Saved Successfully!");

    }
}