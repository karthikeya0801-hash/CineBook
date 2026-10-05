import java.util.*;

public class Cinebook {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> movies = new ArrayList<>();
    static LinkedList<String> selectedSeats = new LinkedList<>();
    static HashMap<String, Boolean> bookedSeats = new HashMap<>();

    static String selectedMovie;
    static String selectedTheater;
    static String selectedTiming;

    static final int TICKET_PRICE = 250;
    static final int CONVENIENCE_FEE = 60;
    static final String FILE_NAME = "users.txt";

    public static void main(String[] args) {
        initializeMovies();
        initializeSeats();

        System.out.println("==============================================");
        System.out.println("              WELCOME TO CINEBOOK             ");
        System.out.println("==============================================");

        if (!LoginModule.loginSystem()) {
            return;
        }

        MovieModule.selectMovie();
        MovieModule.selectTheater();
        MovieModule.selectTiming();
        BookingModule.selectSeats();
        BookingModule.showBookingDetails();
        BookingModule.payment();
        BookingModule.confirmBooking();
    }

    static void initializeMovies() {
        movies.clear();
        FileDB.loadMovies(movies);
        if (movies.isEmpty()) {
            movies.add("Jalsa");
            movies.add("RRR");
            movies.add("Pushpa 2");
            movies.add("Jawan");
            movies.add("Panja");
            movies.add("Leo");
            movies.add("Spider Man Brand New Day");
            movies.add("Spirit");
            movies.add("Odyssey");
            movies.add("Obsession");
            FileDB.saveMovies(movies);
        }
    }

    static void initializeSeats() {
        bookedSeats.clear();
        for (char row = 'A'; row <= 'D'; row++) {
            for (int seat = 1; seat <= 15; seat++) {
                bookedSeats.put(row + String.valueOf(seat), false);
            }
        }

        bookedSeats.putAll(FileDB.loadBookedSeats());

        if (!bookedSeats.containsValue(true)) {
            bookedSeats.put("A2", true);
            bookedSeats.put("A5", true);
            bookedSeats.put("B1", true);
            bookedSeats.put("B4", true);
            bookedSeats.put("C3", true);
            bookedSeats.put("C6", true);
            bookedSeats.put("D2", true);
            bookedSeats.put("D7", true);
            FileDB.saveBookedSeats(bookedSeats);
        }
    }

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}