import java.io.*;
import java.util.*;

class BookingService implements Serializable {

    private Map<String, Integer> inventory = new HashMap<>();
    private List<String> bookings = new ArrayList<>();

    public BookingService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
    }

    public void bookRoom(String guestName, String roomType) {
        if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);
            bookings.add(guestName + "-" + roomType);
            System.out.println("Booking confirmed for " + guestName);
        } else {
            System.out.println("Booking failed for " + guestName);
        }
    }

    public void showState() {
        System.out.println("Current Inventory: " + inventory);
        System.out.println("Booking History: " + bookings);
    }
}

class PersistenceService {

    public void save(BookingService service, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(service);
            out.close();
            System.out.println("State saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving state");
        }
    }

    public BookingService load(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            BookingService service = (BookingService) in.readObject();
            in.close();
            System.out.println("State loaded successfully");
            return service;
        } catch (Exception e) {
            System.out.println("No previous data found, starting fresh");
            return new BookingService();
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        PersistenceService ps = new PersistenceService();

        BookingService service = ps.load("data.ser");

        service.bookRoom("Abhi", "Single");
        service.bookRoom("Subha", "Double");

        service.showState();

        ps.save(service, "data.ser");
    }
}