// The Canteen Price Streak
// A linked list of daily food prices is maintained at the campus canteen.
// A "price streak" is defined as a consecutive sequence of prices where
// each price is greater than or equal to the previous one (non-decreasing).
// Find the length of the longest such streak in the linked list.
// Return 0 if the list is empty; at least 1 otherwise (a single node is a streak of length 1).

public class CanteenPriceStreak {

    static class Node {
        int price;
        Node next;

        Node(int price) {
            this.price = price;
            this.next = null;
        }
    }

    // Find the length of the longest non-decreasing consecutive streak.
    // Single pass: maintain currentStreak and maxStreak counters.
    // When the current node price is >= previous price, extend the streak.
    // Otherwise, reset the current streak to 1.
    public static int longestPriceStreak(Node head) {
        if (head == null) {
            return 0;
        }

        int maxStreak = 1;
        int currentStreak = 1;
        Node current = head.next;
        Node prev = head;

        while (current != null) {
            if (current.price >= prev.price) {
                currentStreak++;
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                }
            } else {
                currentStreak = 1;
            }
            prev = current;
            current = current.next;
        }

        return maxStreak;
    }

    // Helper: build linked list from array
    static Node buildList(int[] arr) {
        Node dummy = new Node(0);
        Node current = dummy;
        for (int val : arr) {
            current.next = new Node(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper: print linked list
    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.price);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: [30, 40, 40, 50, 20, 30, 60]
        // Streaks: [30,40,40,50] = 4, [20,30,60] = 3
        // Longest streak: 4
        Node head1 = buildList(new int[]{30, 40, 40, 50, 20, 30, 60});
        System.out.print("Prices: ");
        printList(head1);
        System.out.println("Longest Price Streak: " + longestPriceStreak(head1));

        System.out.println();

        // Sample 2: [100, 90, 80, 70]
        // All decreasing — each node is a streak of 1
        // Longest streak: 1
        Node head2 = buildList(new int[]{100, 90, 80, 70});
        System.out.print("Prices: ");
        printList(head2);
        System.out.println("Longest Price Streak: " + longestPriceStreak(head2));

        System.out.println();

        // Sample 3: [10, 20, 30, 40, 50]
        // Fully non-decreasing — entire list is one streak
        // Longest streak: 5
        Node head3 = buildList(new int[]{10, 20, 30, 40, 50});
        System.out.print("Prices: ");
        printList(head3);
        System.out.println("Longest Price Streak: " + longestPriceStreak(head3));

        System.out.println();

        // Sample 4: [50, 50, 50]
        // All equal — non-decreasing streak of length 3
        Node head4 = buildList(new int[]{50, 50, 50});
        System.out.print("Prices: ");
        printList(head4);
        System.out.println("Longest Price Streak: " + longestPriceStreak(head4));
    }
}
