// Assignment Problem 4: The Palindrome Train Code
// Each train has a code stored as a singly linked list of digits.
// Codes that read the same from both ends are reserved for special trains.
// Check palindrome in O(n) time and O(1) extra space (no array/stack copies).
// The list must be left exactly as it was after the check.
// Do NOT use Java's built-in LinkedList or other collections for the list itself.

public class PalindromeTrainCode {

    static class Node {
        int  data;
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

    // Reverse a linked list in-place and return the new head
    private static Node reverse(Node head) {
        Node prev = null;
        Node cur  = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next  = prev;
            prev      = cur;
            cur       = next;
        }
        return prev;
    }

    /**
     * Checks whether the singly linked list is a palindrome.
     *   1. Find the middle using slow/fast pointers.
     *   2. Reverse the second half.
     *   3. Compare first and second halves.
     *   4. Reverse the second half again to restore the list.
     * Time: O(n)  |  Space: O(1)
     */
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // Step 1: find middle (slow ends at start of second half for even, mid for odd)
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: reverse second half
        Node secondHalfHead = reverse(slow.next);

        // Step 3: compare
        Node p1      = head;
        Node p2      = secondHalfHead;
        boolean isPalin = true;
        while (p2 != null) {
            if (p1.data != p2.data) {
                isPalin = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // Step 4: restore the list
        slow.next = reverse(secondHalfHead);

        return isPalin;
    }

    public static void main(String[] args) {

        // Sample 1: code = [1, 2, 3, 2, 1] → true (palindrome)
        System.out.println("Sample 1:");
        Node list1 = buildList(new int[]{1, 2, 3, 2, 1});
        System.out.print("Code:   "); printList(list1);
        System.out.println("Result: " + isPalindrome(list1));
        System.out.print("After check (unchanged): "); printList(list1);

        System.out.println();

        // Sample 2: code = [1, 2, 3, 2] → false
        System.out.println("Sample 2:");
        Node list2 = buildList(new int[]{1, 2, 3, 2});
        System.out.print("Code:   "); printList(list2);
        System.out.println("Result: " + isPalindrome(list2));
        System.out.print("After check (unchanged): "); printList(list2);

        System.out.println();

        // Sample 3: code = [1, 2, 2, 1] → true
        System.out.println("Sample 3:");
        Node list3 = buildList(new int[]{1, 2, 2, 1});
        System.out.print("Code:   "); printList(list3);
        System.out.println("Result: " + isPalindrome(list3));
        System.out.print("After check (unchanged): "); printList(list3);
    }
}
