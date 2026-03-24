import java.util.*;

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();
        BookingService service = new BookingService(inventory);

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        service.processBookings(queue);
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

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Queue<Reservation> getQueue() {
        return queue;
    }
}

class RoomInventory {

    private HashMap<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

class BookingService {

    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    private HashMap<String, Integer> roomCounters = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBookings(BookingQueue bookingQueue) {

        System.out.println("Room Allocation Processing");

        Queue<Reservation> queue = bookingQueue.getQueue();

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            if (inventory.getAvailability(r.roomType) > 0) {

                int count = roomCounters.getOrDefault(r.roomType, 0) + 1;
                roomCounters.put(r.roomType, count);

                String roomId = r.roomType + "-" + count;

                allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());
                allocatedRooms.get(r.roomType).add(roomId);

                inventory.reduceAvailability(r.roomType);

                System.out.println("Booking confirmed for Guest: "
                        + r.guestName + ", Room ID: " + roomId);

            }
        }
    }
}