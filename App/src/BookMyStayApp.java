import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {
    public void generateReport(List<Reservation> history) {
        System.out.println("Booking History and Reporting\n");
        System.out.println("Booking History Report\n");
        for (Reservation r : history) {
            System.out.println("Guest: " + r.guestName + ", Room Type: " + r.roomType);
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getHistory());
    }
}