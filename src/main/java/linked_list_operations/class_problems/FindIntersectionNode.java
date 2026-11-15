public class FindIntersectionNode {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Find the first node where two singly linked lists physically merge (share the same node),
    // not just nodes with equal values.
    // Approach: Advance each pointer through both lists (a then b, b then a).
    // Both pointers will travel the same total distance and meet at the intersection
    // node after at most one full traversal of each list. If there is no intersection,
    // both pointers will reach null simultaneously.
    // Time: O(m + n), Space: O(1)
    public static Node findIntersection(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Node pointerA = headA;
        Node pointerB = headB;

        // When a pointer reaches the end of its list, redirect it to the head of the other list.
        // Both pointers will cover the same total distance (lenA + lenB),
        // so they meet at the intersection node or both reach null together.
        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        // pointerA is either the intersection node or null
        return pointerA;
    }

    public static void main(String[] args) {
        // Build two lists that share a common tail:
        // List A: 1 -> 2 -> 3
        //                     \
        //                      7 -> 8 -> null  (intersection starts at node 7)
        //                     /
        // List B:      4 -> 5

        Node shared1 = new Node(7);
        Node shared2 = new Node(8);
        shared1.next = shared2;

        // List A: 1 -> 2 -> 3 -> 7 -> 8
        Node headA = new Node(1);
        headA.next = new Node(2);
        headA.next.next = new Node(3);
        headA.next.next.next = shared1;

        // List B: 4 -> 5 -> 7 -> 8
        Node headB = new Node(4);
        headB.next = new Node(5);
        headB.next.next = shared1;

        Node intersection = findIntersection(headA, headB);
        if (intersection != null) {
            System.out.println("Intersection at node with value: " + intersection.data);
        } else {
            System.out.println("No intersection found.");
        }

        // No intersection case
        Node headC = new Node(1);
        headC.next = new Node(2);
        Node headD = new Node(3);
        headD.next = new Node(4);

        Node noIntersection = findIntersection(headC, headD);
        if (noIntersection != null) {
            System.out.println("Intersection at node with value: " + noIntersection.data);
        } else {
            System.out.println("No intersection found.");
        }
    }
}
