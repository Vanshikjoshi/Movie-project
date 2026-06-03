package movieBooking.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.Random;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import movieBooking.service.BookingService;

public class BookingForm extends JFrame implements ActionListener {

    MainFrame mainFrame;

    JTextField movieField, userField;

    JComboBox<String> seatTypeChoice, paymentChoice;

    DefaultListModel<String> listModel;
    JList<String> movieList;
    JScrollPane scroll;

    JButton bookBtn, cancelBtn;

    JLabel posterLabel, imdbLabel, yearLabel, genreLabel;

    JPanel card;

    Timer searchTimer;

    int seatNumber;
    double totalBill;

    boolean selecting = false; // 🔥 prevents double event glitch

    public BookingForm(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setTitle("Movie Booking System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(18, 18, 30));

        JScrollPane rootScroll = new JScrollPane(root);
        rootScroll.getVerticalScrollBar().setUnitIncrement(16);
        rootScroll.setBorder(null);

        add(rootScroll);

        card = new JPanel();
        card.setPreferredSize(new Dimension(520, 900));
        card.setBackground(new Color(35, 37, 55));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(25, 35, 25, 35));

        root.add(card);

        JLabel title = new JLabel("MOVIE TICKET BOOKING");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        card.add(Box.createVerticalStrut(15));

        movieField = new JTextField();
        styleField(movieField, "Search Movie");
        card.add(movieField);

        card.add(Box.createVerticalStrut(5));

        listModel = new DefaultListModel<>();
        movieList = new JList<>(listModel);

        scroll = new JScrollPane(movieList);
        scroll.setMaximumSize(new Dimension(450, 120));
        scroll.setVisible(false);

        card.add(scroll);

        card.add(Box.createVerticalStrut(10));

        posterLabel = new JLabel();
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imdbLabel = createLabel();
        yearLabel = createLabel();
        genreLabel = createLabel();

        card.add(posterLabel);
        card.add(imdbLabel);
        card.add(yearLabel);
        card.add(genreLabel);

        card.add(Box.createVerticalStrut(10));

        seatTypeChoice = new JComboBox<>(new String[] {
                "Select Seat Type",
                "Front Seat",
                "Middle Seat",
                "Last Seat"
        });
        styleCombo(seatTypeChoice);
        card.add(seatTypeChoice);

        card.add(Box.createVerticalStrut(10));

        userField = new JTextField();
        styleField(userField, "Username");
        card.add(userField);

        card.add(Box.createVerticalStrut(10));

        paymentChoice = new JComboBox<>(new String[] {
                "Select Payment Method",
                "UPI",
                "Card",
                "Cash"
        });
        styleCombo(paymentChoice);
        card.add(paymentChoice);

        card.add(Box.createVerticalStrut(15));

        bookBtn = new JButton("Confirm Booking");
        styleButton(bookBtn, new Color(0, 140, 255));
        bookBtn.addActionListener(this);

        cancelBtn = new JButton("Cancel");
        styleButton(cancelBtn, new Color(220, 70, 70));
        cancelBtn.addActionListener(this);

        card.add(bookBtn);
        card.add(Box.createVerticalStrut(10));
        card.add(cancelBtn);

        movieField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                trigger();
            }

            public void removeUpdate(DocumentEvent e) {
                trigger();
            }

            public void changedUpdate(DocumentEvent e) {
                trigger();
            }

            private void trigger() {
                if (searchTimer != null)
                    searchTimer.stop();

                searchTimer = new Timer(350, e -> searchMovies());
                searchTimer.setRepeats(false);
                searchTimer.start();
            }
        });

        movieList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (selecting)
                    return;

                String selected = movieList.getSelectedValue();

                if (selected != null) {

                    selecting = true;

                    movieField.setText(selected);

                    SwingUtilities.invokeLater(() -> {
                        hideList();
                        loadMovieDetails(selected);
                        selecting = false;
                    });
                }
            }
        });

        setVisible(true);
    }

    private void searchMovies() {

        String text = movieField.getText().trim();

        if (text.length() < 3) {
            hideList();
            return;
        }

        new Thread(() -> {
            try {

                String url = "https://www.omdbapi.com/?apikey=a7cd9cf6&s=" +
                        URLEncoder.encode(text, "UTF-8");

                HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null)
                    sb.append(line);

                String json = sb.toString();

                DefaultListModel<String> temp = new DefaultListModel<>();

                int i = json.indexOf("\"Title\":\"");

                while (i != -1) {
                    i += 9;
                    int end = json.indexOf("\"", i);
                    if (end == -1)
                        break;

                    temp.addElement(json.substring(i, end));
                    i = json.indexOf("\"Title\":\"", end);
                }

                SwingUtilities.invokeLater(() -> {

                    listModel.clear();

                    for (int j = 0; j < temp.size(); j++) {
                        listModel.addElement(temp.get(j));
                    }

                    scroll.setVisible(listModel.size() > 0);
                    card.revalidate();
                    card.repaint();
                });

            } catch (Exception e) {
                hideList();
            }
        }).start();
    }

    private void loadMovieDetails(String movie) {

        try {

            clearMovieUI();

            String url = "https://www.omdbapi.com/?apikey=a7cd9cf6&t=" +
                    URLEncoder.encode(movie, "UTF-8");

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null)
                sb.append(line);

            String json = sb.toString();

            imdbLabel.setText("IMDb: " + extract(json, "imdbRating"));
            yearLabel.setText("Year: " + extract(json, "Year"));
            genreLabel.setText("Genre: " + extract(json, "Genre"));

            posterLabel.setIcon(null);

            String poster = extract(json, "Poster");

            if (poster != null && poster.startsWith("http")) {
                BufferedImage img = ImageIO.read(new URL(poster));
                posterLabel.setIcon(new ImageIcon(
                        img.getScaledInstance(200, 280, Image.SCALE_SMOOTH)));
            }

        } catch (Exception e) {
            clearMovieUI();
        }
    }

    private void hideList() {
        scroll.setVisible(false);
        card.remove(scroll);
        listModel.clear();
        card.revalidate();
        card.repaint();
    }

    private void clearMovieUI() {
        posterLabel.setIcon(null);
        imdbLabel.setText("");
        yearLabel.setText("");
        genreLabel.setText("");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookBtn) {

            try {

                String movie = movieField.getText();
                String user = userField.getText();

                String seatType = seatTypeChoice.getSelectedItem().toString();
                String payment = paymentChoice.getSelectedItem().toString();

                if (movie.isEmpty() || user.isEmpty()
                        || seatType.equals("Select Seat Type")
                        || payment.equals("Select Payment Method")) {
                    JOptionPane.showMessageDialog(this, "Fill all fields");
                    return;
                }

                seatNumber = generateSeat(seatType);
                totalBill = calculatePrice(seatType);

                new BookingService().bookTicket(
                        movie, user, seatType, seatNumber, payment);

                JOptionPane.showMessageDialog(this,
                        "BOOKING CONFIRMED\n\n" +
                                "Movie: " + movie + "\n" +
                                "Seat Type: " + seatType + "\n" +
                                "Seat No: " + seatNumber + "\n" +
                                "Payment: " + payment + "\n" +
                                "Total: ₹" + totalBill);

                for (Window w : Window.getWindows())
                    w.dispose();
                System.exit(0);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Booking Failed");
            }
        }

        if (e.getSource() == cancelBtn)
            dispose();
    }

    private String extract(String json, String key) {
        int i = json.indexOf("\"" + key + "\":\"");
        if (i == -1)
            return "N/A";
        i += key.length() + 4;
        int j = json.indexOf("\"", i);
        return json.substring(i, j);
    }

    private JLabel createLabel() {
        JLabel l = new JLabel();
        l.setForeground(Color.LIGHT_GRAY);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }

    private void styleField(JTextField f, String t) {
        f.setMaximumSize(new Dimension(450, 40));
        f.setBorder(BorderFactory.createTitledBorder(t));
    }

    private void styleButton(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setMaximumSize(new Dimension(450, 40));
    }

    private void styleCombo(JComboBox<?> c) {
        c.setMaximumSize(new Dimension(450, 40));
    }

    private int generateSeat(String type) {
        Random r = new Random();
        if (type.equals("Front Seat"))
            return r.nextInt(30) + 1;
        if (type.equals("Middle Seat"))
            return r.nextInt(40) + 31;
        return r.nextInt(30) + 71;
    }

    private double calculatePrice(String type) {
        if (type.equals("Front Seat"))
            return 300;
        if (type.equals("Middle Seat"))
            return 250;
        return 200;
    }
}