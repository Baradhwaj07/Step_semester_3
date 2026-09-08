final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0 || Double.isNaN(minimumSurgePercent)
                || Double.isInfinite(minimumSurgePercent)) {
            throw new IllegalArgumentException("Minimum surge percent must be non-negative and finite");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || Double.isNaN(orderValue) || Double.isInfinite(orderValue)) {
            throw new IllegalArgumentException("Order value must be non-negative and finite");
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        int firstTierMinutes = Math.min(delayMinutes, 5);
        int secondTierMinutes = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int thirdTierMinutes = Math.max(delayMinutes - 15, 0);
        double tieredFee = orderValue * (firstTierMinutes * 0.005
                + secondTierMinutes * 0.01 + thirdTierMinutes * 0.02);
        double minimumFee = orderValue * minimumSurgePercent / 100.0;
        return Math.round(Math.max(tieredFee, minimumFee) * 100.0) / 100.0;
    }
}

public class ExamWeekSurgeFeeCalculator {
}
