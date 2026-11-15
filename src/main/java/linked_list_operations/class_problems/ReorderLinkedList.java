public class ReorderLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Reorder the linked list in-place so that nodes alternate between the
    // beginning and end of the list:
    //   L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
    // No new nodes are created and no values are copied.
    // Approach (three linked list techniques):
    //   1. Find the middle of the list using fast and slow pointers.
    //   2. Split the list into two halves from the middle.
    //   3. Reverse the second half in-place.
    //   4. Merge the first half and the reversed second half by alternating nodes.
    public static void reorderList(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // Step 1: Find the middle
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 'slow' is now at the end of the first half

        // Step 2: Split the list — sever connection at middle
        Node secondHalf = slow.next;
        slow.next = null;

        // Step 3: Reverse the second half
        secondHalf = reverseList(secondHalf);

        // Step 4: Merge the two halves by alternating nodes
        Node firstHalf = head;
        while (secondHalf != null) {
            Node nextFirst = firstHalf.next;
            Node nextSecond = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = nextFirst;

            firstHalf = nextFirst;
            secondHalf = nextSecond;
        }
    }

    // Reverse a linked list in-place and return the new head
    private static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node next = current.next; // save next before modifying current.next
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
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
        // Sample 1: [1, 2, 3, 4] → [1, 4, 2, 3]
        Node head1 = buildList(new int[]{1, 2, 3, 4});
        System.out.print("Input:  ");
        printList(head1);
        reorderList(head1);
        System.out.print("Output: ");
        printList(head1);

        System.out.println();

        // Sample 2: [1, 2, 3, 4, 5] → [1, 5, 2, 4, 3]
        Node head2 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input:  ");
        printList(head2);
        reorderList(head2);
        System.out.print("Output: ");
        printList(head2);

        System.out.println();

        // Sample 3: [1, 2, 3, 4, 5, 6] → [1, 6, 2, 5, 3, 4]
        Node head3 = buildList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Input:  ");
        printList(head3);
        reorderList(head3);
        System.out.print("Output: ");
        printList(head3);
    }
}
