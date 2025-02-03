import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        // Initialize rooms, example with different types
        rooms.add(new Room(1, "Single", 100.00));
        rooms.add(new Room(2, "Double", 150.00));
        rooms.add(new Room(3, "Suite", 250.00));
    }

    public void checkAvailability() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println("Room " + room.getRoomNumber() + " (" + room.getType() + ") - Price: $" + room.getPrice());
            }
        }
    }

    public void makeReservation(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.next();
        System.out.print("Enter room number to reserve: ");
        int roomNumber = scanner.nextInt();

        Room room = findRoomByNumber(roomNumber);
        if (room != null && !room.isBooked()) {
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            String checkInDate = scanner.next();
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            String checkOutDate = scanner.next();

            Customer customer = new Customer(customerName);
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
            reservations.add(reservation);
            room.setBooked(true);
            System.out.println("Reservation successful for " + customerName + " in Room " + room.getRoomNumber() + ".");
        } else {
            System.out.println("Room not available.");
        }
    }

    public void viewReservations() {
        System.out.println("\nAll Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println("Reservation for " + reservation.getCustomer().getName() + " - Room " + reservation.getRoom().getRoomNumber() +
                    " from " + reservation.getCheckInDate() + " to " + reservation.getCheckOutDate());
        }
    }

    public void simulatePayment(Scanner scanner) {
        System.out.print("Enter reservation name to process payment: ");
        String customerName = scanner.next();
        boolean found = false;

        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getName().equalsIgnoreCase(customerName)) {
                found = true;
                double totalAmount = reservation.getRoom().getPrice();
                System.out.println("Total Amount: $" + totalAmount);
                System.out.print("Enter payment method (Credit/Debit/Cash): ");
                String paymentMethod = scanner.next();
                Payment payment = new Payment(paymentMethod, totalAmount);
                System.out.println("Payment successful for " + customerName + " with " + paymentMethod + ".");
                break;
            }
        }

        if (!found) {
            System.out.println("No reservation found for " + customerName + ".");
        }
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}