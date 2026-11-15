// Assignment Problem 1: Cancelling the kth Last Order
// A restaurant keeps today's orders in a singly linked list, newest at the end.
// When a customer cancels the order that is kth from the end, remove it.
// Implement removeKthFromEnd(Node head, int k) using a single pass.
// Do NOT use Java's built-in LinkedList or other collections for the list itself.

public class CancelKthLastOrder {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Build a linked list from an array (helper)
    public static Node buildList(int[] values) {
        Node dummy = new Node(0);
        Node tail  = dummy;
        for (int v : values) {
            tail.next = new Node(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Print the linked list
    public static void printList(Node head) {
        Node cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(" -> ");
            cur = cur.next;
        }
        System.out.println(sb.toString());
    }

    /**
     * Removes the kth node from the end in a single pass using two pointers.
     * Lead pointer moves k steps ahead, then both move until lead falls off the end.
     * The trailing pointer is then right before the node to remove.
     * Time:  O(n)  |  Space: O(1)
     */
    public static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node lead    = dummy;
        Node trailer = dummy;

        // Advance lead k+1 steps so the gap between lead and trailer is k
        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        // Move both until lead falls off the end
        while (lead != null) {
            lead    = lead.next;
            trailer = trailer.next;
        }

        // trailer.next is the node to remove
        trailer.next = trailer.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {

        // Sample 1: orders = [10, 20, 30, 40, 50], cancel k=2 from end → remove 40
        System.out.println("Sample 1:");
        Node list1 = buildList(new int[]{10, 20, 30, 40, 50});
        System.out.print("Before: ");
        printList(list1);
        list1 = removeKthFromEnd(list1, 2);
        System.out.print("After:  ");
        printList(list1);   // Expected: 10 -> 20 -> 30 -> 50

        System.out.println();

        // Sample 2: orders = [10, 20, 30, 40, 50], cancel k=5 from end → remove head (10)
        System.out.println("Sample 2:");
        Node list2 = buildList(new int[]{10, 20, 30, 40, 50});
        System.out.print("Before: ");
        printList(list2);
        list2 = removeKthFromEnd(list2, 5);
        System.out.print("After:  ");
        printList(list2);   // Expected: 20 -> 30 -> 40 -> 50
    }
}
