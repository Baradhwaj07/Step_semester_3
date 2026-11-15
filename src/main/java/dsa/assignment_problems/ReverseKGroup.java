public class ReverseKGroup {

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

    // Check if at least k nodes remain starting from node.
    static boolean hasKNodes(Node node, int k) {
        int count = 0;
        while (node != null && count < k) {
            node = node.next;
            count++;
        }
        return count == k;
    }

    // Reverse k nodes starting from head; returns new head after reversal.
    // groupTail (the original head) will point to the next group after this method.
    static Node reverseKGroup(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node prevGroupTail = dummy;

        while (hasKNodes(prevGroupTail.next, k)) {
            Node groupHead = prevGroupTail.next;
            // Reverse k nodes
            Node prev = null;
            Node curr = groupHead;
            for (int i = 0; i < k; i++) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            // prev is now the new head of the reversed group
            // groupHead is now the tail of the reversed group
            prevGroupTail.next = prev;      // connect previous group tail to new group head
            groupHead.next = curr;           // connect reversed group tail to remaining list
            prevGroupTail = groupHead;       // advance prevGroupTail
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Sample 1: [1,2,3,4,5,6,7,8], k=3 -> [3,2,1,6,5,4,7,8]
        System.out.println("Sample 1:");
        Node list1 = buildList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println("Original : " + listToString(list1));
        Node result1 = reverseKGroup(list1, 3);
        System.out.println("Reversed in groups of 3: " + listToString(result1));
        System.out.println();

        // Sample 2: [11,12], k=3 -> [11,12] (no complete group of 3)
        System.out.println("Sample 2:");
        Node list2 = buildList(new int[]{11, 12});
        System.out.println("Original : " + listToString(list2));
        Node result2 = reverseKGroup(list2, 3);
        System.out.println("Reversed in groups of 3: " + listToString(result2));
    }
}
