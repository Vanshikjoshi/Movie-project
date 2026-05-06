package movieBooking.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static void saveToFile(String data) {
        try {
            FileWriter fw = new FileWriter("movieBooking/data/bookings.txt", true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

