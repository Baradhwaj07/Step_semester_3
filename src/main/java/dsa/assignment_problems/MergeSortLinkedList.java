public class MergeSortLinkedList {

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

    static String listToString(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }

    // Find middle using slow/fast pointers; cut the list at the middle and return the first half's head.
    // The second half starts at middle.next; we set middle.next = null to split.
    static Node[] splitHalf(Node head) {
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = slow.next;
        slow.next = null; // cut
        return new Node[]{head, secondHalf};
    }

    // Merge two sorted lists by relinking nodes (no new nodes created). Stable: left wins on ties.
    static Node merge(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    static Node mergeSort(Node head) {
        if (head == null || head.next == null) return head;
        Node[] halves = splitHalf(head);
        Node left = mergeSort(halves[0]);
        Node right = mergeSort(halves[1]);
        return merge(left, right);
    }

    public static void main(String[] args) {
        // Sample 1: Unsorted list
        System.out.println("Sample 1:");
        Node list1 = buildList(new int[]{38, 27, 43, 3, 9, 82, 10});
        System.out.println("Before: " + listToString(list1));
        list1 = mergeSort(list1);
        System.out.println("After : " + listToString(list1));
        System.out.println();

        // Sample 2: Already sorted / single node
        System.out.println("Sample 2:");
        Node list2 = buildList(new int[]{5});
        System.out.println("Before: " + listToString(list2));
        list2 = mergeSort(list2);
        System.out.println("After : " + listToString(list2));
        System.out.println();

        // Sample 3: Two elements reversed
        System.out.println("Sample 3:");
        Node list3 = buildList(new int[]{15, 4, 20, 1, 9});
        System.out.println("Before: " + listToString(list3));
        list3 = mergeSort(list3);
        System.out.println("After : " + listToString(list3));
    }
}
