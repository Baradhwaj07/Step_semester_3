public class DetectLoop {

    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    // Build a linked list and optionally create a cycle at cycleIndex (0-based).
    // cycleIndex = -1 means no cycle.
    static Node buildList(int[] values, int cycleIndex) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        Node cycleEntry = (cycleIndex == 0) ? head : null;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
            if (i == cycleIndex) cycleEntry = curr;
        }
        if (cycleEntry != null) curr.next = cycleEntry; // create cycle
        return head;
    }

    // Returns the meeting node if a cycle exists, else null.
    static Node detectCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return slow; // meeting point
        }
        return null;
    }

    // Count cycle length starting from meeting point.
    static int cycleLength(Node meeting) {
        int length = 1;
        Node curr = meeting.next;
        while (curr != meeting) {
            curr = curr.next;
            length++;
        }
        return length;
    }

    // Find cycle start: reset one pointer to head, advance both one step at a time.
    static Node cycleStart(Node head, Node meeting) {
        Node p1 = head, p2 = meeting;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    static void analyze(int[] values, int cycleIndex) {
        Node head = buildList(values, cycleIndex);
        Node meeting = detectCycle(head);
        if (meeting == null) {
            System.out.println("Cycle detected: false");
            System.out.println("No cycle");
        } else {
            Node start = cycleStart(head, meeting);
            int length = cycleLength(meeting);
            System.out.println("Cycle detected: true");
            System.out.println("Cycle starts at node with value: " + start.data);
            System.out.println("Cycle length: " + length);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: [10 -> 20 -> 30 -> 40 -> 50], cycle at index 2 (node 30)
        System.out.println("Sample 1:");
        analyze(new int[]{10, 20, 30, 40, 50}, 2);

        // Sample 2: [10 -> 20 -> 30 -> 40 -> 50], no cycle
        System.out.println("Sample 2:");
        analyze(new int[]{10, 20, 30, 40, 50}, -1);
    }
}
