import java.util.*;

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Guest1", "Single Room"));
        bookingQueue.addRequest(new Reservation("Guest2", "Double Room"));
        bookingQueue.addRequest(new Reservation("Guest3", "Suite Room"));

        bookingQueue.displayQueue();
    }
}

class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    public void displayQueue() {

        System.out.println("Booking Request Queue\n");

        for (Reservation r : queue) {
            System.out.println("Guest: " + r.guestName);
            System.out.println("Requested Room: " + r.roomType);
            System.out.println();
        }
    }
}