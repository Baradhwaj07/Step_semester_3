class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()
                || dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name and dish name are required");
        }
        this.studentName = studentName;
        this.dishName = dishName;
    }

    public void markDelivered() {
        if (delivered) {
            System.out.println("Order has already been delivered.");
        } else {
            delivered = true;
            System.out.println("Order delivered.");
        }
    }
}

public class GhostOrderValidator {
    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        if (rawOrders != null) {
            for (String[] order : rawOrders) {
                try {
                    if (order == null || order.length < 2) {
                        throw new IllegalArgumentException("Incomplete order");
                    }
                    new FoodOrder(order[0], order[1]);
                    valid++;
                } catch (IllegalArgumentException exception) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
}
