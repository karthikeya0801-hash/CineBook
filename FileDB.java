import java.io.*;
import java.util.*;

public class FileDB {

    static final String USERS_FILE = "users.txt";
    static final String BOOKINGS_FILE = "bookings.txt";
    static final String MOVIES_FILE = "movies.txt";
    static final String SEATS_FILE = "seats.txt";

    static void registerUser(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            bw.write(username + "," + password);
            bw.newLine();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    static boolean validateCredentials(String username, String password) {
        File file = new File(USERS_FILE);

        if (!file.exists()) {
            System.out.println("No registered users found.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 2);

                if (data.length == 2
                        && data[0].equals(username)
                        && data[1].equals(password)) {
                    BookingModule.currentUsername = username;
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return false;
    }

    static void saveBooking(String bookingId, String username, String movie,
                            String theater, String timing, List<String> seats,
                            int ticketAmount, int convenienceFee,
                            int totalAmount, String paymentMethod) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKINGS_FILE, true))) {
            bw.write(bookingId + "|" + username + "|" + movie + "|" + theater + "|"
                    + timing + "|" + String.join(",", seats) + "|"
                    + ticketAmount + "|" + convenienceFee + "|"
                    + totalAmount + "|" + paymentMethod);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving booking: " + e.getMessage());
        }
    }

    static void saveMovies(List<String> movies) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MOVIES_FILE))) {
            for (String movie : movies) {
                bw.write(movie);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving movies: " + e.getMessage());
        }
    }

    static void loadMovies(List<String> movies) {
        File file = new File(MOVIES_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    movies.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading movies: " + e.getMessage());
        }
    }

    static void saveBookedSeats(Map<String, Boolean> bookedSeats) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SEATS_FILE))) {
            for (Map.Entry<String, Boolean> entry : bookedSeats.entrySet()) {
                if (Boolean.TRUE.equals(entry.getValue())) {
                    bw.write(entry.getKey());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving seats: " + e.getMessage());
        }
    }

    static HashMap<String, Boolean> loadBookedSeats() {
        HashMap<String, Boolean> seats = new HashMap<>();
        File file = new File(SEATS_FILE);

        if (!file.exists()) {
            return seats;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    seats.put(line.trim(), true);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading seats: " + e.getMessage());
        }

        return seats;
    }
}