package movieBooking.ui;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import movieBooking.model.Payment;
import movieBooking.service.BookingService;

public class BookingForm extends Frame implements ActionListener {

    MainFrame mainFrame;

    Label heading;
    TextField movieField, seatsField, userField;
    List movieList;
    Choice paymentChoice;
    Button bookBtn, cancelBtn;

    Label message;

    public BookingForm(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setTitle("Movie Ticket Booking");
        setSize(520, 600);
        setLayout(null);
        setBackground(new Color(18, 18, 28));

        // ===== HEADER =====
        heading = new Label("Movie Ticket Booking", Label.CENTER);
        heading.setBounds(0, 40, 520, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        add(heading);

        // ===== MOVIE =====
        Label movieLabel = new Label("Movie");
        movieLabel.setBounds(60, 110, 100, 20);
        movieLabel.setForeground(Color.LIGHT_GRAY);
        add(movieLabel);

        movieField = new TextField();
        movieField.setBounds(60, 135, 400, 30);
        add(movieField);

        movieList = new List();
        movieList.setBounds(60, 170, 400, 80);
        movieList.setVisible(false);
        add(movieList);

        // ===== SEATS =====
        Label seatsLabel = new Label("Seats");
        seatsLabel.setBounds(60, 260, 100, 20);
        seatsLabel.setForeground(Color.LIGHT_GRAY);
        add(seatsLabel);

        seatsField = new TextField();
        seatsField.setBounds(60, 285, 400, 30);
        add(seatsField);

        // ===== USER =====
        Label userLabel = new Label("Username");
        userLabel.setBounds(60, 330, 100, 20);
        userLabel.setForeground(Color.LIGHT_GRAY);
        add(userLabel);

        userField = new TextField();
        userField.setBounds(60, 355, 400, 30);
        add(userField);

        // ===== PAYMENT =====
        Label paymentLabel = new Label("Payment");
        paymentLabel.setBounds(60, 400, 100, 20);
        paymentLabel.setForeground(Color.LIGHT_GRAY);
        add(paymentLabel);

        paymentChoice = new Choice();
        paymentChoice.add("UPI");
        paymentChoice.add("Card");
        paymentChoice.add("Cash");
        paymentChoice.setBounds(60, 425, 400, 30);
        add(paymentChoice);

        // ===== BUTTONS =====
        bookBtn = new Button("Confirm Booking");
        bookBtn.setBounds(120, 470, 170, 40);
        bookBtn.setBackground(new Color(0, 140, 255));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.addActionListener(this);
        add(bookBtn);

        cancelBtn = new Button("Cancel");
        cancelBtn.setBounds(300, 470, 100, 40);
        cancelBtn.setBackground(new Color(200, 60, 60));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        message = new Label("", Label.CENTER);
        message.setBounds(60, 520, 400, 30);
        message.setForeground(Color.YELLOW);
        add(message);

        // ===== EVENTS =====
        movieField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchMovies(movieField.getText());
            }
        });

        movieList.addItemListener(e -> {
            movieField.setText(movieList.getSelectedItem());
            movieList.setVisible(false);
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    // ===== BOOKING ACTION =====
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookBtn) {

            try {

                String movie = movieField.getText();
                int seats = Integer.parseInt(seatsField.getText());
                String user = userField.getText().trim();

                Payment payment = new Payment(paymentChoice.getSelectedItem(), seats);
                double total = payment.calculateTotal();

                new BookingService().bookTicket(movie, seats, user);

                // CLOSE MAIN BOOKING WINDOW
                this.dispose();

                // SHOW CONFIRMATION DIALOG
                Dialog d = new Dialog(mainFrame, "Booking Confirmed", true);
                d.setSize(380, 220);
                d.setLayout(null);
                d.setBackground(new Color(30, 30, 45));
                d.setLocationRelativeTo(null);

                Label title = new Label("CONFIRM BOOKING", Label.CENTER);
                title.setBounds(0, 30, 380, 30);
                title.setForeground(Color.WHITE);
                title.setFont(new Font("Segoe UI", Font.BOLD, 16));
                d.add(title);

                Label bill = new Label("Total Bill: ₹" + total, Label.CENTER);
                bill.setBounds(0, 70, 380, 30);
                bill.setForeground(Color.WHITE);
                bill.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                d.add(bill);

                Label quote = new Label("\"Have a great movie experience!\"", Label.CENTER);
                quote.setBounds(0, 100, 380, 25);
                quote.setForeground(Color.LIGHT_GRAY);
                d.add(quote);

                Button ok = new Button("OK");
                ok.setBounds(150, 140, 80, 30);
                d.add(ok);

                ok.addActionListener(ev -> {
                    d.dispose();       // close dialog
                    mainFrame.dispose(); // close main frame too
                });

                d.setVisible(true);

            } catch (Exception ex) {
                message.setText("Booking Failed / Invalid Input");
            }
        }

        if (e.getSource() == cancelBtn) {
            dispose();
        }
    }

    // KEEP YOUR SEARCH FUNCTION AS IT IS (not changed here)
    public void searchMovies(String movieName) {
        try {
            movieName = movieName.trim();
            movieList.removeAll();

            if (movieName.length() < 3) {
                movieList.setVisible(false);
                return;
            }

            movieList.setVisible(true);

            String encoded = URLEncoder.encode(movieName, "UTF-8");
            String apiUrl = "https://www.omdbapi.com/?apikey=a7cd9cf6&s=" + encoded;

            HttpURLConnection con = (HttpURLConnection) new URL(apiUrl).openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            String json = sb.toString();

            if (json.contains("\"Response\":\"False\"")) {
                movieList.setVisible(false);
                return;
            }

            int i = 0;

            while ((i = json.indexOf("\"Title\":\"", i)) != -1) {
                i += 9;
                int end = json.indexOf("\"", i);

                String title = json.substring(i, end);

                if (title.toLowerCase().startsWith(movieName.toLowerCase())) {
                    movieList.add(title);
                }

                i = end;
            }

            if (movieList.getItemCount() == 0)
                movieList.setVisible(false);

        } catch (Exception ex) {
            message.setText("API Error");
        }
    }
}