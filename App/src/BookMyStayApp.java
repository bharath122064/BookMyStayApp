import java.util.*;

class BookingService {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, String> reservations = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public BookingService() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void addReservation(String reservationId, String roomType) {
        reservations.put(reservationId, roomType);
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void cancelBooking(String reservationId) {
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Invalid cancellation request");
            return;
        }

        String roomType = reservations.get(reservationId);

        rollbackStack.push(reservationId);
        inventory.put(roomType, inventory.get(roomType) + 1);
        reservations.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");
        Stack<String> temp = (Stack<String>) rollbackStack.clone();

        while (!temp.isEmpty()) {
            System.out.println("Released Reservation ID: " + temp.pop());
        }
    }

    public void showInventory(String roomType) {
        System.out.println("\nUpdated " + roomType + " Room Availability: " + inventory.get(roomType));
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        service.addReservation("Single-1", "Single");
        service.cancelBooking("Single-1");

        service.showRollbackHistory();
        service.showInventory("Single");
    }
}