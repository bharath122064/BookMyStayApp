import java.util.*;

class BookingService {

    private Map<String, Integer> inventory = new HashMap<>();

    public BookingService() {
        inventory.put("Single", 1);
    }

    public synchronized void bookRoom(String guestName, String roomType) {
        if (inventory.get(roomType) > 0) {
            System.out.println(guestName + " is booking " + roomType);
            inventory.put(roomType, inventory.get(roomType) - 1);
            System.out.println("Booking confirmed for " + guestName);
        } else {
            System.out.println("No rooms available for " + guestName);
        }
    }

    public void showInventory() {
        System.out.println("Final Availability: " + inventory.get("Single"));
    }
}

class BookingThread extends Thread {
    BookingService service;
    String guestName;

    public BookingThread(BookingService service, String guestName) {
        this.service = service;
        this.guestName = guestName;
    }

    public void run() {
        service.bookRoom(guestName, "Single");
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        Thread t1 = new BookingThread(service, "Guest1");
        Thread t2 = new BookingThread(service, "Guest2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }

        service.showInventory();
    }
}