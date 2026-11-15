import java.util.ArrayList;
import java.util.List;

// Problem 1: Static Nested Class vs Member Inner Class
// Model an Order system that uses:
//   - A static nested class LineItem (can exist without an Order object)
//   - A member inner class Cursor (walks the Order's own items array, reads private fields)
// A static nested class needs no outer object and can exist on its own, so LineItem fits.
// A member inner class is tied to one outer object and can read its private fields,
// so Cursor can walk the Order's own array.

public class OrderProcessor {

    // ---- Static Nested Class ----
    // LineItem can be created independently without any Order instance.
    // It represents a single item (name, quantity, unit price).
    static class LineItem {
        String name;
        int quantity;
        double unitPrice;

        LineItem(String name, int quantity, double unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double totalPrice() {
            return quantity * unitPrice;
        }

        @Override
        public String toString() {
            return name + " x" + quantity + " @ " + unitPrice + " = " + totalPrice();
        }
    }

    // ---- Order class (outer class) ----
    private String orderId;
    private List<LineItem> items;

    OrderProcessor(String orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
    }

    void addItem(LineItem item) {
        items.add(item);
    }

    int itemCount() {
        return items.size();
    }

    double orderTotal() {
        double total = 0;
        for (LineItem item : items) {
            total += item.totalPrice();
        }
        return total;
    }

    // ---- Member Inner Class ----
    // Cursor is tied to this specific Order instance and can access
    // the Order's private 'items' list directly.
    class Cursor {
        private int index;

        Cursor() {
            this.index = 0;
        }

        boolean hasNext() {
            return index < items.size(); // accesses outer Order's private field
        }

        LineItem next() {
            return items.get(index++);
        }

        void reset() {
            index = 0;
        }
    }

    // Create and return a Cursor tied to this Order
    Cursor getCursor() {
        return new Cursor();
    }

    public static void main(String[] args) {
        // LineItem can be created without any Order — it's static nested
        LineItem pencil = new LineItem("Pencil", 2, 50.0);
        LineItem notebook = new LineItem("Notebook", 3, 350.0);
        LineItem pen = new LineItem("Pen", 5, 200.0);

        System.out.println("--- Standalone LineItems (no Order needed) ---");
        System.out.println(pencil);
        System.out.println(notebook);
        System.out.println(pen);
        System.out.println();

        // Build an Order and add items
        OrderProcessor order = new OrderProcessor("ORD-001");
        order.addItem(pencil);
        order.addItem(notebook);
        order.addItem(pen);

        System.out.println("Order ID    : " + order.orderId);
        System.out.println("Number of items: " + order.itemCount());
        System.out.printf("Order Total : %.1f%n", order.orderTotal());
        System.out.println();

        // Use the inner Cursor to walk the Order's items
        System.out.println("--- Walking Order items using Cursor (inner class) ---");
        OrderProcessor.Cursor cursor = order.getCursor();
        int idx = 1;
        while (cursor.hasNext()) {
            System.out.println("  Item " + idx++ + ": " + cursor.next());
        }
        System.out.println();

        // Sample 2: Single-item order
        OrderProcessor order2 = new OrderProcessor("ORD-002");
        order2.addItem(new LineItem("Ruler", 1, 150.0));
        System.out.println("Order: " + order2.orderId + " | Items: " + order2.itemCount() + " | Total: " + order2.orderTotal());

        // Static nested LineItem can also be used in utility contexts
        LineItem bulk = new LineItem("Eraser", 100, 10.0);
        System.out.println("Bulk order item: " + bulk);
    }
}
