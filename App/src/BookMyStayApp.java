import java.util.HashMap;

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        SearchService search = new SearchService(inventory);

        search.displayAvailableRooms();
    }
}

class RoomInventory {

    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();

        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }
}

class Room {

    String type;
    int beds;
    int size;
    double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
}

class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void displayAvailableRooms() {

        System.out.println("Available Rooms\n");

        Room single = new Room("Single Room", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double Room", 2, 400, 2500.0);
        Room suite = new Room("Suite Room", 3, 750, 5000.0);

        display(single);
        display(doubleRoom);
        display(suite);
    }

    private void display(Room room) {

        int available = inventory.getAvailability(room.type);

        if (available > 0) {
            System.out.println(room.type + ":");
            System.out.println("Beds: " + room.beds);
            System.out.println("Size: " + room.size + " sqft");
            System.out.println("Price per night: " + room.price);
            System.out.println("Available Rooms: " + available);
            System.out.println();
        }
    }
}