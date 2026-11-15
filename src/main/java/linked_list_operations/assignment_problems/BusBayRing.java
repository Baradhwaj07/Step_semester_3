// The Bus Bay Ring
// A circular linked list models a ring of bus bays at a transit hub.
// Each node represents a bay with a bay number.
// Implement the following operations on the circular linked list:
//   1. insert(bayNumber)   — Insert a new bay at the end of the ring.
//   2. delete(bayNumber)   — Remove the bay with the given bay number.
//   3. displayRing()       — Print all bays in circular order starting from head.
//   4. detectCycle()       — Confirm whether the list is truly circular (always true here).
//   5. rotateRing(k)       — Rotate the ring k positions clockwise
//                            (the kth node from head becomes the new head).
//
// All operations preserve the circular structure (last node always points back to head).

public class BusBayRing {

    static class Node {
        int bayNumber;
        Node next;

        Node(int bayNumber) {
            this.bayNumber = bayNumber;
            this.next = null;
        }
    }

    private Node head;  // head of the circular linked list
    private Node tail;  // tail always points back to head

    public BusBayRing() {
        head = null;
        tail = null;
    }

    // Insert a new bay at the end of the ring
    public void insert(int bayNumber) {
        Node newNode = new Node(bayNumber);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head; // circular link
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // maintain circular link
        }
    }

    // Delete the bay with the given bay number
    public void delete(int bayNumber) {
        if (head == null) {
            System.out.println("Ring is empty. Cannot delete bay " + bayNumber + ".");
            return;
        }

        // Special case: only one node in the ring
        if (head == tail && head.bayNumber == bayNumber) {
            head = null;
            tail = null;
            System.out.println("Bay " + bayNumber + " deleted. Ring is now empty.");
            return;
        }

        // Find the node to delete and its predecessor
        Node current = head;
        Node prev = tail; // tail is the predecessor of head in a circular list

        do {
            if (current.bayNumber == bayNumber) {
                if (current == head) {
                    // Deleting the head — update head and tail's next
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    // Deleting the tail — update tail to prev
                    tail = prev;
                    tail.next = head;
                } else {
                    // Deleting a middle node
                    prev.next = current.next;
                }
                System.out.println("Bay " + bayNumber + " deleted.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Bay " + bayNumber + " not found in the ring.");
    }

    // Print all bays in circular order starting from head
    public void displayRing() {
        if (head == null) {
            System.out.println("Ring is empty.");
            return;
        }
        System.out.print("Ring: ");
        Node current = head;
        do {
            System.out.print("[Bay " + current.bayNumber + "]");
            current = current.next;
            if (current != head) System.out.print(" -> ");
        } while (current != head);
        System.out.println(" -> (back to Bay " + head.bayNumber + ")");
    }

    // Confirm that the list is truly circular
    public boolean detectCycle() {
        if (head == null) return false;
        Node slow = head;
        Node fast = head;
        do {
            slow = slow.next;
            if (fast.next == null || fast.next.next == null) return false;
            fast = fast.next.next;
        } while (slow != fast);
        return true;
    }

    // Rotate the ring k positions clockwise:
    // The kth node from head becomes the new head.
    // Before: head -> n1 -> ... -> newTail -> rotatedHead -> ... -> oldTail -> head
    // After:  rotatedHead -> ... -> oldTail -> head -> n1 -> ... -> newTail -> rotatedHead
    public void rotateRing(int k) {
        if (head == null || head == tail || k <= 0) return;

        // Count total nodes to handle k > size
        int size = 1;
        Node current = head;
        while (current.next != head) {
            size++;
            current = current.next;
        }
        // current is now the old tail

        k = k % size;
        if (k == 0) return;

        // Find the node just before the new head (newTail = kth node from head, 1-indexed)
        Node newTail = head;
        for (int i = 0; i < k - 1; i++) {
            newTail = newTail.next;
        }

        Node rotatedHead = newTail.next; // new head after rotation
        Node oldTail = current;          // old tail (points to old head)

        // Rewire:
        // 1. old tail now points to old head (mid-list): already set (oldTail.next == head)
        // 2. newTail now points back to rotatedHead to close the circle
        newTail.next = rotatedHead;  // close the new circle at the split point
        // Wait — this makes newTail point to rotatedHead, losing the rest of chain 2.
        // We need newTail to be the new TAIL (wrapping to rotatedHead).
        // The correct rewiring:
        //   oldTail.next = head (old head)      ← already the case
        //   newTail.next = rotatedHead           ← this is what closes the circle
        // So the final circle is: rotatedHead -> ... -> oldTail -> head -> ... -> newTail -> rotatedHead ✓

        head = rotatedHead;
        tail = newTail;
        System.out.println("Ring rotated by " + k + " positions.");
    }

    public static void main(String[] args) {
        BusBayRing ring = new BusBayRing();

        // Insert bays
        ring.insert(101);
        ring.insert(102);
        ring.insert(103);
        ring.insert(104);
        ring.insert(105);

        ring.displayRing();
        System.out.println("Is circular: " + ring.detectCycle());

        System.out.println();

        // Delete middle bay
        ring.delete(103);
        ring.displayRing();

        System.out.println();

        // Delete head bay
        ring.delete(101);
        ring.displayRing();

        System.out.println();

        // Rotate ring by 2 positions
        ring.insert(106);
        ring.insert(107);
        ring.displayRing();
        ring.rotateRing(2);
        ring.displayRing();

        System.out.println();

        // Delete non-existent bay
        ring.delete(999);
    }
}
