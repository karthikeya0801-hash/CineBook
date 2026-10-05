public class MovieModule {

    static void selectMovie() {
        System.out.println("\n========== MOVIES ==========");

        for (int i = 0; i < Cinebook.movies.size(); i++) {
            System.out.println((i + 1) + ". " + Cinebook.movies.get(i));
        }

        System.out.print("Select movie: ");
        int choice = Cinebook.readInt();

        if (choice >= 1 && choice <= Cinebook.movies.size()) {
            Cinebook.selectedMovie = Cinebook.movies.get(choice - 1);
        } else {
            Cinebook.selectedMovie = Cinebook.movies.get(Cinebook.movies.size() - 1);
            System.out.println("Invalid choice. Obsession selected.");
        }

        FileDB.saveMovies(Cinebook.movies);
    }

    static void selectTheater() {
        System.out.println("\n========== THEATERS ==========");
        System.out.println("1. Blaji Cinema");
        System.out.println("2. Sri Satyanarayana");
        System.out.println("3. S.V.c");
        System.out.println("4. V-Max");
        System.out.print("Select theater: ");

        int choice = Cinebook.readInt();

        switch (choice) {
            case 1:
                Cinebook.selectedTheater = "Blaji Cinema";
                break;
            case 2:
                Cinebook.selectedTheater = "Sri Satyanarayana";
                break;
            case 3:
                Cinebook.selectedTheater = "S.V.c";
                break;
            case 4:
                Cinebook.selectedTheater = "V-Max";
                break;
            default:
                Cinebook.selectedTheater = "V-Max";
                System.out.println("Invalid choice. PVR Cinemas selected.");
        }
    }

    static void selectTiming() {
        System.out.println("\n========== SHOW TIMINGS ==========");
        System.out.println("1. 11:00 AM");
        System.out.println("2. 02:00 PM");
        System.out.println("3. 06:30 PM");
        System.out.println("4. 09:00 PM");
        System.out.println("5. 10:40 PM");
        System.out.print("Select timing: ");

        int choice = Cinebook.readInt();

        switch (choice) {
            case 1:
                Cinebook.selectedTiming = "11:00 AM";
                break;
            case 2:
                Cinebook.selectedTiming = "02:00 PM";
                break;
            case 3:
                Cinebook.selectedTiming = "06:30 PM";
                break;
            case 4:
                Cinebook.selectedTiming = "09:00 PM";
                break;
            case 5:
                Cinebook.selectedTiming = "10:40 PM";
                break;
            default:
                Cinebook.selectedTiming = "04:00 PM";
                System.out.println("Invalid choice. 04:00 PM selected.");
        }
    }
}