import java.util.*;

public class LoginModule {

    static boolean loginSystem() {
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = Cinebook.readInt();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    if (login()) {
                        return true;
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using Cinebook.");
                    return false;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void register() {
        System.out.print("Enter username: ");
        String username = Cinebook.sc.nextLine();

        System.out.print("Enter password: ");
        String password = Cinebook.sc.nextLine();

        FileDB.registerUser(username, password);
    }

    static boolean login() {
        System.out.print("Enter username: ");
        String username = Cinebook.sc.nextLine();

        System.out.print("Enter password: ");
        String password = Cinebook.sc.nextLine();

        if (FileDB.validateCredentials(username, password)) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Invalid username or password.");
        return false;
    }
}