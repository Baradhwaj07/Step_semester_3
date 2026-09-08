class BusTicketAccount {
    private static final double DEFAULT_PENALTY_PERCENT;

    static {
        DEFAULT_PENALTY_PERCENT = 1.0;
    }

    private final String bookingId;
    private final double ticketFare;

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0 || Double.isNaN(ticketFare) || Double.isInfinite(ticketFare)) {
            throw new IllegalArgumentException("Ticket fare must be non-negative and finite");
        }
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Late minutes cannot be negative");
        }
        return ticketFare * DEFAULT_PENALTY_PERCENT / 100.0 * minutesLate;
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return;
        }
        if (amount < 0 || Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Settlement amount must be non-negative and finite");
        }
        System.out.println(account.bookingId + " settled: Rs " + amount
                + ", penalty: Rs " + account.calculatePenalty(minutesLate));
    }
}

class Sleeper extends BusTicketAccount {
    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }

    @Override
    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return;
        }
        super.processAccount(account, amount, minutesLate);
    }
}

public class NightlyFleetReconciliationEngine {
    public static void processBatch(BusTicketAccount[] accounts, double[] amounts,
            int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null
                || accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Batch arrays must be non-null and have matching lengths");
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;
        for (int index = 0; index < accounts.length; index++) {
            BusTicketAccount account = accounts[index];
            if (account == null) {
                nullSkipped++;
                continue;
            }
            if (account instanceof Sleeper) {
                account.processAccount(account, amounts[index], minutesLateArray[index]);
                sleeperCount++;
            } else {
                account.processAccount(account, amounts[index], minutesLateArray[index]);
                regularCount++;
            }
            processed++;
            grandTotalPenalties += account.calculatePenalty(minutesLateArray[index]);
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | "
                + sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = "
                + grandTotalPenalties);
    }
}
