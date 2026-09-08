final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0 || Double.isNaN(minimumPenaltyPercent)
                || Double.isInfinite(minimumPenaltyPercent)) {
            throw new IllegalArgumentException("Minimum penalty percent must be non-negative and finite");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || Double.isNaN(ticketFare) || Double.isInfinite(ticketFare)) {
            throw new IllegalArgumentException("Ticket fare must be non-negative and finite");
        }
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Late minutes cannot be negative");
        }
        if (minutesLate == 0) {
            return 0.0;
        }

        int firstTierMinutes = Math.min(minutesLate, 5);
        int secondTierMinutes = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTierMinutes = Math.max(minutesLate - 15, 0);
        double tieredPenalty = ticketFare * (firstTierMinutes * 0.005
                + secondTierMinutes * 0.01 + thirdTierMinutes * 0.02);
        double minimumPenalty = ticketFare * minimumPenaltyPercent / 100.0;
        return Math.max(tieredPenalty, minimumPenalty);
    }
}

public class BoardingPenaltyCalculatorDemo {
}
