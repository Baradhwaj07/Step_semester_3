class DeliveryAccount {
    private static final double DEFAULT_SURGE_PERCENT;

    static {
        DEFAULT_SURGE_PERCENT = 1.0;
    }

    private final String studentId;
    private final double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        if (orderValue < 0 || Double.isNaN(orderValue) || Double.isInfinite(orderValue)) {
            throw new IllegalArgumentException("Order value must be non-negative and finite");
        }
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
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
        double minimumFee = orderValue * DEFAULT_SURGE_PERCENT / 100.0;
        return Math.round(Math.max(tieredFee, minimumFee) * 100.0) / 100.0;
    }

    public String getStudentId() {
        return studentId;
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }
        if (amount < 0 || Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Settlement amount must be non-negative and finite");
        }
        System.out.println(account.studentId + " regular settlement: Rs " + amount
                + ", surge fee: Rs " + account.calculateSurgeFee(delayMinutes));
    }
}

class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }

    public void processPremiumAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }
        if (amount < 0 || Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Settlement amount must be non-negative and finite");
        }
        System.out.println(account.getStudentId() + " premium settlement: Rs " + amount
                + ", surge fee: Rs " + account.calculateSurgeFee(delayMinutes));
    }
}

public class NightlyMultiKitchenReconciliationEngine {
    public static void processBatch(DeliveryAccount[] accounts, double[] amounts,
            int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null
                || accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Batch arrays must be non-null and have matching lengths");
        }

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        for (int index = 0; index < accounts.length; index++) {
            DeliveryAccount account = accounts[index];
            if (account == null) {
                nullSkipped++;
                continue;
            }
            if (account instanceof Premium) {
                ((Premium) account).processPremiumAccount(account, amounts[index], delayMinutesArray[index]);
                premiumCount++;
            } else {
                account.processAccount(account, amounts[index], delayMinutesArray[index]);
                regularCount++;
            }
            processed++;
            grandTotalSurgeFees += account.calculateSurgeFee(delayMinutesArray[index]);
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | "
                + premiumCount + " premium | " + regularCount + " regular | grand total surge fees = "
                + grandTotalSurgeFees);
    }
}
