// The Hackathon Banner Printer
// At a hackathon, teams are stored in a doubly linked list.
// A banner must be printed in a "zigzag" format: print one team from the front,
// then one from the back, alternating until all teams are printed.
// This should be achieved by manipulating pointers on the doubly linked list itself
// without using any additional data structures.
// If the list has an odd number of nodes, the middle node is printed last.
//
// Example:
//   Input:  "TeamA" <-> "TeamB" <-> "TeamC" <-> "TeamD" <-> "TeamE"
//   Banner: TeamA, TeamE, TeamB, TeamD, TeamC

public class HackathonBannerPrinter {

    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    // Build doubly linked list and return both head and tail
    static Node[] buildDoublyList(String[] names) {
        if (names.length == 0) return new Node[]{null, null};
        Node head = new Node(names[0]);
        Node current = head;
        for (int i = 1; i < names.length; i++) {
            Node newNode = new Node(names[i]);
            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }
        return new Node[]{head, current}; // head and tail
    }

    // Print the banner in zigzag order using two pointers (front and back),
    // moving toward each other and alternating picks.
    public static void printBanner(Node head) {
        if (head == null) {
            System.out.println("(empty)");
            return;
        }

        // Find the tail
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        Node front = head;
        Node back = tail;
        boolean pickFront = true;
        StringBuilder banner = new StringBuilder();

        while (front != back && front.prev != back) {
            if (pickFront) {
                if (banner.length() > 0) banner.append(", ");
                banner.append(front.name);
                front = front.next;
            } else {
                if (banner.length() > 0) banner.append(", ");
                banner.append(back.name);
                back = back.prev;
            }
            pickFront = !pickFront;
        }

        // Print the final middle node (when front == back — odd length list)
        // or when front has crossed back (even length — both pointers printed)
        if (front == back) {
            if (banner.length() > 0) banner.append(", ");
            banner.append(front.name);
        }

        System.out.println("Banner: " + banner.toString());
    }

    public static void main(String[] args) {
        // Sample 1: Odd number of teams → 5 teams
        // Expected: TeamA, TeamE, TeamB, TeamD, TeamC
        String[] teams1 = {"TeamA", "TeamB", "TeamC", "TeamD", "TeamE"};
        Node[] list1 = buildDoublyList(teams1);
        System.out.print("Teams:  ");
        printForward(list1[0]);
        printBanner(list1[0]);

        System.out.println();

        // Sample 2: Even number of teams → 4 teams
        // Expected: Alpha, Delta, Beta, Gamma
        String[] teams2 = {"Alpha", "Beta", "Gamma", "Delta"};
        Node[] list2 = buildDoublyList(teams2);
        System.out.print("Teams:  ");
        printForward(list2[0]);
        printBanner(list2[0]);

        System.out.println();

        // Sample 3: Single team
        String[] teams3 = {"SoloSquad"};
        Node[] list3 = buildDoublyList(teams3);
        System.out.print("Teams:  ");
        printForward(list3[0]);
        printBanner(list3[0]);
    }

    static void printForward(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.name);
            if (current.next != null) System.out.print(" <-> ");
            current = current.next;
        }
        System.out.println();
    }
}
