import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        System.out.println("Welcome to the Hotel Reservation System!");
        while (true) {
            System.out.println("\n1. Check Room Availability");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View All Reservations");
            System.out.println("4. Simulate Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.checkAvailability();
                    break;
                case 2:
                    hotel.makeReservation(scanner);
                    break;
                case 3:
                    hotel.viewReservations();
                    break;
                case 4:
                    hotel.simulatePayment(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}