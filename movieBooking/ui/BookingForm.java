package movieBooking.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import java.net.*;
import java.io.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import movieBooking.model.Payment;
import movieBooking.service.BookingService;

public class BookingForm extends JFrame
        implements ActionListener {

    MainFrame mainFrame;

    JTextField movieField,
            seatsField,
            userField;

    JComboBox<String> paymentChoice;

    DefaultListModel<String> listModel;

    JList<String> movieList;

    JButton bookBtn,
            cancelBtn;

    JLabel message;

    JScrollPane scroll;

    JPanel card;

    JLabel posterLabel;

    JLabel imdbLabel;

    JLabel yearLabel;

    JLabel genreLabel;

    public BookingForm(
            MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setTitle("Book Movie Ticket");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);

        // ===== MAIN PANEL =====

        JPanel mainPanel = new JPanel();

        mainPanel.setBackground(
                new Color(10, 15, 30));

        mainPanel.setLayout(
                new GridBagLayout());

        // ===== FULL PAGE SCROLL =====

        JScrollPane mainScroll =
                new JScrollPane(mainPanel);

        mainScroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        mainScroll.setBorder(null);

        add(mainScroll);

        // ===== CARD =====

        card = new JPanel();

        card.setPreferredSize(
                new Dimension(520, 1000));

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

        // ===== HEADING =====

        JLabel heading =
                new JLabel(
                        "MOVIE TICKET BOOKING");

        heading.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        heading.setForeground(Color.WHITE);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        JLabel sub =
                new JLabel(
                        "Book your favourite movie");

        sub.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        sub.setForeground(
                Color.LIGHT_GRAY);

        sub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        card.add(heading);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        card.add(sub);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 35)));

        // ===== MOVIE FIELD =====

        movieField = new JTextField();

        styleField(
                movieField,
                "Movie Name");

        card.add(movieField);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        // ===== MOVIE LIST =====

        listModel =
                new DefaultListModel<>();

        movieList =
                new JList<>(listModel);

        movieList.setVisibleRowCount(5);

        movieList.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        scroll =
                new JScrollPane(movieList);

        scroll.setMaximumSize(
                new Dimension(420, 100));

        scroll.setVisible(false);

        card.add(scroll);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 20)));

        // ===== POSTER =====

        posterLabel = new JLabel();

        posterLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        card.add(posterLabel);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 20)));

        // ===== DETAILS =====

        imdbLabel =
                new JLabel("");

        imdbLabel.setForeground(Color.WHITE);

        imdbLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        imdbLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        card.add(imdbLabel);

        yearLabel =
                new JLabel("");

        yearLabel.setForeground(Color.WHITE);

        yearLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        yearLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        card.add(yearLabel);

        genreLabel =
                new JLabel("");

        genreLabel.setForeground(Color.WHITE);

        genreLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        genreLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        card.add(genreLabel);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 25)));

        // ===== SEATS =====

        seatsField =
                new JTextField();

        styleField(
                seatsField,
                "Number Of Seats");

        card.add(seatsField);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 20)));

        // ===== USER =====

        userField =
                new JTextField();

        styleField(
                userField,
                "Username");

        card.add(userField);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 20)));

        // ===== PAYMENT =====

        paymentChoice =
                new JComboBox<>();

        paymentChoice.addItem("UPI");

        paymentChoice.addItem("Card");

        paymentChoice.addItem("Cash");

        paymentChoice.setMaximumSize(
                new Dimension(420, 45));

        paymentChoice.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        card.add(paymentChoice);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 35)));

        // ===== BOOK BUTTON =====

        bookBtn =
                new JButton(
                        "Confirm Booking");

        styleButton(
                bookBtn,
                new Color(0, 140, 255));

        bookBtn.addActionListener(this);

        card.add(bookBtn);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 15)));

        // ===== CANCEL BUTTON =====

        cancelBtn =
                new JButton("Cancel");

        styleButton(
                cancelBtn,
                new Color(220, 70, 70));

        cancelBtn.addActionListener(this);

        card.add(cancelBtn);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 15)));

        // ===== MESSAGE =====

        message =
                new JLabel("");

        message.setForeground(Color.YELLOW);

        message.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        card.add(message);

        mainPanel.add(card);

        // ===== SEARCH EVENT =====

        movieField.addKeyListener(
                new KeyAdapter() {

            public void keyReleased(
                    KeyEvent e) {

                String text =
                        movieField
                                .getText()
                                .trim();

                if (text.length() < 3) {

                    scroll.setVisible(false);

                    return;
                }

                searchMovies(text);

                if (listModel.size() > 0) {

                    scroll.setVisible(true);

                } else {

                    scroll.setVisible(false);
                }

                card.revalidate();

                card.repaint();
            }
        });

        // ===== SELECT MOVIE =====

        movieList.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                String selected =
                        movieList.getSelectedValue();

                if (selected != null) {

                    movieField.setText(selected);

                    loadMovieDetails(selected);

                    scroll.setVisible(false);

                    card.revalidate();

                    card.repaint();
                }
            }
        });

        setVisible(true);
    }

    // ===== FIELD STYLE =====

    private void styleField(
            JTextField field,
            String title) {

        field.setMaximumSize(
                new Dimension(420, 45));

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        field.setBorder(
                BorderFactory
                        .createTitledBorder(
                                title));
    }

    // ===== BUTTON STYLE =====

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
                        16));

        btn.setMaximumSize(
                new Dimension(420, 45));
    }

    // ===== BUTTON ACTIONS =====

    public void actionPerformed(
            ActionEvent e) {

        if (e.getSource()
                == bookBtn) {

            try {

                String movie =
                        movieField.getText();

                int seats =
                        Integer.parseInt(
                                seatsField.getText());

                String user =
                        userField.getText();

                Payment payment =
                        new Payment(
                                paymentChoice
                                        .getSelectedItem()
                                        .toString(),
                                seats);

                double total =
                        payment.calculateTotal();

                new BookingService()
                        .bookTicket(
                                movie,
                                seats,
                                user);

                int option =
                        JOptionPane.showOptionDialog(
                                this,
                                "Booking Successful!\n\n"
                                        + "Movie : "
                                        + movie
                                        + "\nSeats : "
                                        + seats
                                        + "\nTotal Bill : ₹"
                                        + total,
                                "Booking Confirmed",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new String[]{"OK"},
                                "OK");

                if (option == 0) {

                    dispose();

                    mainFrame.dispose();

                    System.exit(0);
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking Failed / Invalid Input");
            }
        }

        if (e.getSource()
                == cancelBtn) {

            dispose();
        }
    }

    // ===== SEARCH MOVIES =====

    public void searchMovies(
            String movieName) {

        try {

            movieName =
                    movieName.trim();

            listModel.clear();

            String encoded =
                    URLEncoder.encode(
                            movieName,
                            "UTF-8");

            String apiUrl =
                    "https://www.omdbapi.com/?apikey=a7cd9cf6&s="
                            + encoded;

            HttpURLConnection con =
                    (HttpURLConnection)
                            new URL(apiUrl)
                                    .openConnection();

            con.setRequestMethod("GET");

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    con.getInputStream()));

            String line;

            StringBuilder sb =
                    new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
            }

            br.close();

            String json =
                    sb.toString();

            int i = 0;

            while ((i = json.indexOf(
                    "\"Title\":\"",
                    i)) != -1) {

                i += 9;

                int end =
                        json.indexOf(
                                "\"",
                                i);

                String title =
                        json.substring(
                                i,
                                end);

                listModel.addElement(title);

                i = end;
            }

        } catch (Exception ex) {

            message.setText(
                    "Movie API Error");
        }
    }

    // ===== LOAD MOVIE DETAILS =====

    public void loadMovieDetails(
            String movieName) {

        try {

            String encoded =
                    URLEncoder.encode(
                            movieName,
                            "UTF-8");

            String apiUrl =
                    "https://www.omdbapi.com/?apikey=a7cd9cf6&t="
                            + encoded;

            HttpURLConnection con =
                    (HttpURLConnection)
                            new URL(apiUrl)
                                    .openConnection();

            con.setRequestMethod("GET");

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    con.getInputStream()));

            String line;

            StringBuilder sb =
                    new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
            }

            br.close();

            String json =
                    sb.toString();

            String year =
                    extractValue(
                            json,
                            "Year");

            String genre =
                    extractValue(
                            json,
                            "Genre");

            String imdb =
                    extractValue(
                            json,
                            "imdbRating");

            String poster =
                    extractValue(
                            json,
                            "Poster");

            imdbLabel.setText(
                    "IMDb Rating : " + imdb);

            yearLabel.setText(
                    "Year : " + year);

            genreLabel.setText(
                    "Genre : " + genre);

            if (!poster.equals("N/A")) {

                BufferedImage image =
                        ImageIO.read(
                                new URL(poster));

                Image scaled =
                        image.getScaledInstance(
                                220,
                                300,
                                Image.SCALE_SMOOTH);

                posterLabel.setIcon(
                        new ImageIcon(scaled));
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    // ===== JSON VALUE EXTRACT =====

    public String extractValue(
            String json,
            String key) {

        try {

            int start =
                    json.indexOf(
                            "\"" + key + "\":\"");

            if (start == -1) {

                return "N/A";
            }

            start += key.length() + 4;

            int end =
                    json.indexOf(
                            "\"",
                            start);

            return json.substring(
                    start,
                    end);

        } catch (Exception e) {

            return "N/A";
        }
    }
}