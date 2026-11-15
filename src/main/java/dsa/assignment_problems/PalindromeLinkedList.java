public class PalindromeLinkedList {

    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    static Node buildList(int[] values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    // Find the end of the first half using slow/fast pointers.
    // For even length: slow stops at n/2 - 1 (last node of first half).
    // For odd length: slow stops at the middle node.
    static Node endOfFirstHalf(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Reverse a linked list in place and return the new head.
    static Node reverseList(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find end of first half
        Node firstHalfEnd = endOfFirstHalf(head);

        // Step 2: Reverse second half
        Node secondHalfStart = reverseList(firstHalfEnd.next);

        // Step 3: Compare both halves
        boolean palindrome = true;
        Node p1 = head, p2 = secondHalfStart;
        while (p2 != null) {
            if (p1.data != p2.data) {
                palindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // Step 4: Restore the list (reverse second half back and reattach)
        firstHalfEnd.next = reverseList(secondHalfStart);

        return palindrome;
    }

    public static void main(String[] args) {
        // Sample 1: [5, 9, 9, 5] -> Palindrome: true
        System.out.println("Sample 1:");
        Node list1 = buildList(new int[]{5, 9, 9, 5});
        System.out.println("Palindrome: " + isPalindrome(list1));

        // Sample 2: [5, 9, 9] -> Palindrome: false
        System.out.println("Sample 2:");
        Node list2 = buildList(new int[]{5, 9, 9});
        System.out.println("Palindrome: " + isPalindrome(list2));

        // Sample 3: [1, 2, 3, 2, 1] -> Palindrome: true
        System.out.println("Sample 3:");
        Node list3 = buildList(new int[]{1, 2, 3, 2, 1});
        System.out.println("Palindrome: " + isPalindrome(list3));

        // Sample 4: [1] -> Palindrome: true
        System.out.println("Sample 4:");
        Node list4 = buildList(new int[]{1});
        System.out.println("Palindrome: " + isPalindrome(list4));
    }
}
