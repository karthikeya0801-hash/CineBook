import java.util.*;

public class BookingModule {

    static int ticketAmount;
    static int totalAmount;
    static String paymentMethod;
    static String currentUsername = "";

    static void selectSeats() {
        System.out.println("\n========== SEATS ==========");

        for (char row = 'A'; row <= 'D'; row++) {
            for (int seat = 1; seat <= 15; seat++) {
                String seatName = row + String.valueOf(seat);
                if (Boolean.TRUE.equals(Cinebook.bookedSeats.get(seatName))) {
                    System.out.print("[XX] ");
                } else {
                    System.out.print("[" + seatName + "] ");
                }
            }
            System.out.println();
        }

        System.out.print("Enter number of seats: ");
        int count = Cinebook.readInt();

        Cinebook.selectedSeats.clear();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter seat " + (i + 1) + ": ");
            String seat = Cinebook.sc.nextLine().toUpperCase();

            if (Cinebook.bookedSeats.containsKey(seat)
                    && !Boolean.TRUE.equals(Cinebook.bookedSeats.get(seat))
                    && !Cinebook.selectedSeats.contains(seat)) {
                Cinebook.selectedSeats.add(seat);
                Cinebook.bookedSeats.put(seat, true);
            } else {
                System.out.println("Seat unavailable. Please select another seat.");
                i--;
            }
        }

        FileDB.saveBookedSeats(Cinebook.bookedSeats);
    }

    static void showBookingDetails() {
        ticketAmount = Cinebook.selectedSeats.size() * Cinebook.TICKET_PRICE;
        totalAmount = ticketAmount + Cinebook.CONVENIENCE_FEE;

        System.out.println("\n========== BOOKING DETAILS ==========");
        System.out.println("Movie          : " + Cinebook.selectedMovie);
        System.out.println("Theater        : " + Cinebook.selectedTheater);
        System.out.println("Timing         : " + Cinebook.selectedTiming);
        System.out.println("Seats          : " + Cinebook.selectedSeats);
        System.out.println("Ticket Amount  : Rs." + ticketAmount);
        System.out.println("Convenience Fee: Rs." + Cinebook.CONVENIENCE_FEE);
        System.out.println("Total Amount   : Rs." + totalAmount);
    }

    static void payment() {
        System.out.println("\n========== PAYMENT ==========");
        System.out.println("1. UPI");
        System.out.println("2. Credit Card");
        System.out.println("3. Debit Card");
        System.out.println("4. Net Banking");
        System.out.print("Select payment method: ");

        int choice = Cinebook.readInt();

        switch (choice) {
            case 1:
                paymentMethod = "UPI";
                break;
            case 2:
                paymentMethod = "Credit Card";
                break;
            case 3:
                paymentMethod = "Debit Card";
                break;
            case 4:
                paymentMethod = "Net Banking";
                break;
            default:
                paymentMethod = "UPI";
                System.out.println("Invalid choice. UPI selected.");
        }

        System.out.println("Payment successful!");
    }

    static void confirmBooking() {
        String bookingId = "CB" + (100000 + new Random().nextInt(900000));

        System.out.println("\n==============================================");
        System.out.println("              BOOKING CONFIRMED               ");
        System.out.println("==============================================");
        System.out.println("Booking ID     : " + bookingId);
        System.out.println("Movie          : " + Cinebook.selectedMovie);
        System.out.println("Theater        : " + Cinebook.selectedTheater);
        System.out.println("Timing         : " + Cinebook.selectedTiming);
        System.out.println("Seats          : " + Cinebook.selectedSeats);
        System.out.println("Payment Method : " + paymentMethod);
        System.out.println("Total Amount   : Rs." + totalAmount);
        System.out.println("==============================================");
        System.out.println("        Thank you for booking with Cinebook!");
        System.out.println("==============================================");

        FileDB.saveBooking(
                bookingId,
                currentUsername,
                Cinebook.selectedMovie,
                Cinebook.selectedTheater,
                Cinebook.selectedTiming,
                Cinebook.selectedSeats,
                ticketAmount,
                Cinebook.CONVENIENCE_FEE,
                totalAmount,
                paymentMethod
        );
    }
}