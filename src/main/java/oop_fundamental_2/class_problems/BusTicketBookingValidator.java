import java.util.HashSet;
import java.util.Set;

class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (!isMeaningful(passengerName) || !isMeaningful(destination)) {
            throw new IllegalArgumentException("Passenger name and destination must contain letters only");
        }
        this.passengerName = passengerName;
        this.destination = destination;
    }

    private static boolean isMeaningful(String value) {
        return value != null && !value.trim().isEmpty() && value.matches("[A-Za-z]+(?:[ '-][A-Za-z]+)*");
    }

    public void markCheckedIn() {
        if (checkedIn) {
            throw new IllegalStateException("Ticket is already checked in");
        }
        checkedIn = true;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }
}

public class BusTicketBookingValidator {
    public static void processBatch(String[][] rawBookings) {
        if (rawBookings == null) {
            return;
        }

        Set<String> acceptedPairs = new HashSet<>();
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                new BusTicket(booking[0], booking[1]);
                String pair = booking[0].toLowerCase() + "\u0000" + booking[1].toLowerCase();
                if (!acceptedPairs.add(pair)) {
                    duplicates++;
                } else {
                    valid++;
                }
            } catch (IllegalArgumentException exception) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicates);
    }
}
