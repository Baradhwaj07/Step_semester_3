class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || Double.isNaN(totalFare) || Double.isInfinite(totalFare)) {
            throw new IllegalArgumentException("Fare cannot be negative or non-finite");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        long totalCents = Math.round(totalFare * 100.0);
        long baseCents = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;
        double[] shares = new double[passengerCount];

        for (int index = 0; index < passengerCount; index++) {
            long cents = baseCents + (index == passengerCount - 1 ? remainder : 0);
            shares[index] = cents / 100.0;
        }
        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public String getTripId() {
        return tripId;
    }
}

public class FareSplitterDemo {
    public static void main(String[] args) {
        FareSplitter splitter = new FareSplitter("TRIP001", 100000, 3);
        for (double share : splitter.fareBreakdown()) {
            System.out.println(share);
        }
    }
}
