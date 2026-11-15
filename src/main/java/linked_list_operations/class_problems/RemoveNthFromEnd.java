public class RemoveNthFromEnd {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Remove the nth node from the end of the list in a single pass.
    // Uses two pointers: advance fast pointer n+1 steps ahead,
    // then move both until fast reaches null — slow.next is the target node.
    // A dummy head simplifies the edge case where the head itself is removed.
    public static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        // Advance fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next is the node to remove
        slow.next = slow.next.next;

        return dummy.next;
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
            System.out.print(current.data);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: [1, 2, 3, 4, 1], n = 2 → remove 4 → [1, 2, 3, 1]
        Node head1 = buildList(new int[]{1, 2, 3, 4, 1});
        System.out.print("Input:  ");
        printList(head1);
        head1 = removeNthFromEnd(head1, 2);
        System.out.print("Output: ");
        printList(head1);

        System.out.println();

        // Sample 2: [1, 2, 3], n = 3 → remove head → [2, 3]
        Node head2 = buildList(new int[]{1, 2, 3});
        System.out.print("Input:  ");
        printList(head2);
        head2 = removeNthFromEnd(head2, 3);
        System.out.print("Output: ");
        printList(head2);
    }
}
