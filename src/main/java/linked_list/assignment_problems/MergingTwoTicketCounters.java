// Assignment Problem 3: Merging Two Ticket Counters
// Two railway counters each produce a sorted singly linked list of ticket numbers.
// Merge both lists into one sorted singly linked list in O(m+n) time, O(1) extra space.
// Do NOT use Java's built-in LinkedList or other collections for the list itself.

public class MergingTwoTicketCounters {

    static class Node {
        int  data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Build a linked list from a sorted array (helper)
    public static Node buildList(int[] values) {
        Node dummy = new Node(0);
        Node tail  = dummy;
        for (int v : values) {
            tail.next = new Node(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Print linked list on one line
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
     * Merges two sorted singly linked lists in-place.
     * Uses a dummy head and always attaches the smaller of the two current nodes.
     * When one list runs out, the rest of the other is attached directly.
     * Time: O(m + n)  |  Space: O(1) — only pointer manipulations, no new nodes created.
     */
    public static Node mergeSorted(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node tail  = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // Attach whichever list still has remaining nodes
        tail.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }

    public static void main(String[] args) {

        // Sample 1: Counter A: [1, 3, 5, 7]   Counter B: [2, 4, 6, 8]
        System.out.println("Sample 1:");
        Node a1 = buildList(new int[]{1, 3, 5, 7});
        Node b1 = buildList(new int[]{2, 4, 6, 8});
        System.out.print("Counter A: "); printList(a1);
        System.out.print("Counter B: "); printList(b1);
        Node merged1 = mergeSorted(a1, b1);
        System.out.print("Merged:    "); printList(merged1);
        // Expected: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8

        System.out.println();

        // Sample 2: Counter A: [1, 2, 4]   Counter B: [1, 3, 4]
        System.out.println("Sample 2:");
        Node a2 = buildList(new int[]{1, 2, 4});
        Node b2 = buildList(new int[]{1, 3, 4});
        System.out.print("Counter A: "); printList(a2);
        System.out.print("Counter B: "); printList(b2);
        Node merged2 = mergeSorted(a2, b2);
        System.out.print("Merged:    "); printList(merged2);
        // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4

        System.out.println();

        // Sample 3: Counter A: [10, 20, 30]  Counter B: [] (empty)
        System.out.println("Sample 3:");
        Node a3 = buildList(new int[]{10, 20, 30});
        Node b3 = null;
        System.out.print("Counter A: "); printList(a3);
        System.out.print("Counter B: (empty)\n");
        Node merged3 = mergeSorted(a3, b3);
        System.out.print("Merged:    "); printList(merged3);
        // Expected: 10 -> 20 -> 30
    }
}
