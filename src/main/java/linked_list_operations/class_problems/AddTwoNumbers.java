public class AddTwoNumbers {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Add two non-negative integers represented as singly linked lists.
    // Digits are stored in reverse order (least significant digit at head).
    // e.g., 8 -> 7 -> 9 represents 978, and 1 -> 2 -> 3 represents 321.
    // Returns the head of a new linked list representing the sum (also in reverse order).
    // Handles lists of different lengths and a final carry.
    public static Node addNumbers(Node a, Node b) {
        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        // Iterate through both lists simultaneously
        while (a != null || b != null || carry != 0) {
            int sum = carry;

            if (a != null) {
                sum += a.data;
                a = a.next;
            }
            if (b != null) {
                sum += b.data;
                b = b.next;
            }

            carry = sum / 10;
            int digit = sum % 10;

            current.next = new Node(digit);
            current = current.next;
        }

        return dummy.next;
    }

    // Helper: build linked list from array (least significant digit first)
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
        // Sample 1: 978 + 321 = 1299
        // a = [8 -> 7 -> 9] (represents 978)
        // b = [1 -> 2 -> 3] (represents 321)
        // Result: [9 -> 9 -> 2 -> 1] (represents 1299)
        Node a1 = buildList(new int[]{8, 7, 9});
        Node b1 = buildList(new int[]{1, 2, 3});
        System.out.print("a: ");
        printList(a1);
        System.out.print("b: ");
        printList(b1);
        System.out.print("Sum: ");
        printList(addNumbers(a1, b1));

        System.out.println();

        // Sample 2: 342 + 465 = 807
        // a = [2 -> 4 -> 3] (represents 342)
        // b = [5 -> 6 -> 4] (represents 465)
        // Result: [7 -> 0 -> 8] (represents 807)
        Node a2 = buildList(new int[]{2, 4, 3});
        Node b2 = buildList(new int[]{5, 6, 4});
        System.out.print("a: ");
        printList(a2);
        System.out.print("b: ");
        printList(b2);
        System.out.print("Sum: ");
        printList(addNumbers(a2, b2));

        System.out.println();

        // Sample 3: Different lengths — 99 + 1 = 100
        // a = [9 -> 9], b = [1]
        // Result: [0 -> 0 -> 1]
        Node a3 = buildList(new int[]{9, 9});
        Node b3 = buildList(new int[]{1});
        System.out.print("a: ");
        printList(a3);
        System.out.print("b: ");
        printList(b3);
        System.out.print("Sum: ");
        printList(addNumbers(a3, b3));
    }
}
