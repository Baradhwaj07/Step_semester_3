public class PalindromeCheck {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Check if a singly linked list is a palindrome in O(n) time, O(1) extra space.
    // Steps:
    //   1. Find the middle of the list using fast and slow pointers.
    //   2. Reverse the second half in-place.
    //   3. Compare the first half and the reversed second half node by node.
    //   4. Restore the list (optional but good practice).
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 'slow' is now at the start of the second half

        // Step 2: Reverse the second half
        Node secondHalfHead = reverseList(slow);
        Node secondHalfCopy = secondHalfHead; // keep for restoration

        // Step 3: Compare both halves
        Node firstHalf = head;
        Node secondHalf = secondHalfHead;
        boolean isPalin = true;
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                isPalin = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // Step 4: Restore the list (reverse the second half back)
        reverseList(secondHalfCopy);

        return isPalin;
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
        // Sample 1: [1, 2, 2, 1] → true
        Node head1 = buildList(new int[]{1, 2, 2, 1});
        System.out.print("Input: ");
        printList(head1);
        System.out.println("Is Palindrome: " + isPalindrome(head1));

        System.out.println();

        // Sample 2: [1, 2, 3] → false
        Node head2 = buildList(new int[]{1, 2, 3});
        System.out.print("Input: ");
        printList(head2);
        System.out.println("Is Palindrome: " + isPalindrome(head2));

        System.out.println();

        // Sample 3: [1, 2, 3, 2, 1] → true (odd length)
        Node head3 = buildList(new int[]{1, 2, 3, 2, 1});
        System.out.print("Input: ");
        printList(head3);
        System.out.println("Is Palindrome: " + isPalindrome(head3));
    }
}
