package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import movieBooking.model.Payment;
import movieBooking.service.BookingService;
import movieBooking.util.CustomException;

public class BookingForm extends Frame implements ActionListener {

    MainFrame mainFrame;

    Label heading, movieLabel, seatsLabel, userLabel, paymentLabel, message;

    TextField movieField, seatsField, userField;

    List movieList;

    Choice paymentChoice;

    Button bookBtn, cancelBtn;

    public BookingForm(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setTitle("Movie Ticket Booking");
        setSize(500, 560);
        setLayout(null);
        setBackground(new Color(15, 25, 55));

        // HEADING
        heading = new Label("BOOK MOVIE TICKET");
        heading.setBounds(90, 40, 320, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        add(heading);

        // MOVIE LABEL
        movieLabel = new Label("Search Movie:");
        movieLabel.setBounds(60, 110, 120, 30);
        movieLabel.setForeground(Color.WHITE);
        movieLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(movieLabel);

        // MOVIE FIELD
        movieField = new TextField();
        movieField.setBounds(220, 110, 180, 30);
        add(movieField);

        // MOVIE LIST (HIDDEN INITIALLY → FIX FOR EXTRA SPACE LOOK)
        movieList = new List();
        movieList.setBounds(220, 145, 180, 70);
        movieList.setVisible(false);
        add(movieList);

        // SEARCH WHILE TYPING
        movieField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchMovies(movieField.getText());
            }
        });

        // SELECT MOVIE
        movieList.addItemListener(e -> {
            movieField.setText(movieList.getSelectedItem());
            movieList.setVisible(false);
        });

        // SEATS LABEL (moved up slightly)
        seatsLabel = new Label("Number of Seats:");
        seatsLabel.setBounds(60, 230, 140, 30);
        seatsLabel.setForeground(Color.WHITE);
        seatsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(seatsLabel);

        seatsField = new TextField();
        seatsField.setBounds(220, 230, 180, 30);
        add(seatsField);

        // USER
        userLabel = new Label("Username:");
        userLabel.setBounds(60, 280, 120, 30);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(userLabel);

        userField = new TextField();
        userField.setBounds(220, 280, 180, 30);
        add(userField);

        // PAYMENT
        paymentLabel = new Label("Payment:");
        paymentLabel.setBounds(60, 330, 120, 30);
        paymentLabel.setForeground(Color.WHITE);
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(paymentLabel);

        paymentChoice = new Choice();
        paymentChoice.add("UPI");
        paymentChoice.add("Card");
        paymentChoice.add("Cash");
        paymentChoice.setBounds(220, 330, 180, 30);
        add(paymentChoice);

        // BUTTONS
        bookBtn = new Button("Confirm Booking");
        bookBtn.setBounds(90, 400, 150, 40);
        bookBtn.setBackground(new Color(0, 140, 255));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFont(new Font("Arial", Font.BOLD, 14));
        bookBtn.addActionListener(this);
        add(bookBtn);

        cancelBtn = new Button("Cancel");
        cancelBtn.setBounds(270, 400, 100, 40);
        cancelBtn.setBackground(new Color(180, 50, 50));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        // MESSAGE
        message = new Label("");
        message.setBounds(100, 470, 300, 30);
        message.setForeground(Color.YELLOW);
        add(message);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    // SEARCH MOVIES
    public void searchMovies(String movieName) {

        try {
            movieName = movieName.trim();
            movieList.removeAll();

            if (movieName.length() < 3) {
                movieList.setVisible(false);
                return;
            }

            movieList.setVisible(true);

            String encodedName = URLEncoder.encode(movieName, "UTF-8");

            String apiUrl = "https://www.omdbapi.com/?apikey=a7cd9cf6&s=" + encodedName;

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();

            String json = response.toString();

            if (json.contains("\"Response\":\"False\"")) {
                movieList.setVisible(false);
                return;
            }

            int index = 0;

            while ((index = json.indexOf("\"Title\":\"", index)) != -1) {
                index += 9;
                int end = json.indexOf("\"", index);

                String title = json.substring(index, end);

                if (title.toLowerCase().startsWith(movieName.toLowerCase())) {
                    movieList.add(title);
                }

                index = end;
            }

            if (movieList.getItemCount() == 0) {
                movieList.setVisible(false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            message.setText("API Error");
        }
    }

    // BUTTON ACTIONS
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookBtn) {

            try {
                String movie = movieField.getText();
                int seats = Integer.parseInt(seatsField.getText());
                String user = userField.getText().trim();
                String paymentMethod = paymentChoice.getSelectedItem();

                Payment payment = new Payment(paymentMethod, seats);
                double total = payment.calculateTotal();

                BookingService service = new BookingService();
                service.bookTicket(movie, seats, user);

                Dialog successDialog = new Dialog(this, "Booking Successful", true);
                successDialog.setSize(350, 220);
                successDialog.setLayout(null);
                successDialog.setBackground(new Color(20, 40, 90));

                Label successMsg = new Label("Ticket Booked Successfully!");
                successMsg.setBounds(50, 60, 260, 30);
                successMsg.setForeground(Color.WHITE);
                successDialog.add(successMsg);

                Label billMsg = new Label("Total Bill: ₹" + total);
                billMsg.setBounds(90, 100, 180, 30);
                billMsg.setForeground(Color.YELLOW);
                successDialog.add(billMsg);

                Button okBtn = new Button("OK");
                okBtn.setBounds(130, 150, 80, 30);
                successDialog.add(okBtn);

                okBtn.addActionListener(ev -> {
                    successDialog.dispose();
                    mainFrame.dispose();
                    dispose();
                });

                successDialog.setVisible(true);

            } catch (NumberFormatException ex) {
                message.setText("Enter Valid Number!");
            } catch (CustomException ex) {
                message.setText(ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                message.setText("Booking Failed!");
            }
        }

        if (e.getSource() == cancelBtn) {
            dispose();
        }
    }
}