import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingService {
    private Map<String, Integer> inventory = new HashMap<>();

    public BookingService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public void bookRoom(String guestName, String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected");
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        inventory.put(roomType, inventory.get(roomType) - 1);
        System.out.println("Booking confirmed for " + guestName + " (" + roomType + ")");
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        try {
            service.bookRoom("Abhi", "Single");
            service.bookRoom("Subha", "Suite");
            service.bookRoom("Vanmathi", "Suite");
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("System continues running...");
    }
}