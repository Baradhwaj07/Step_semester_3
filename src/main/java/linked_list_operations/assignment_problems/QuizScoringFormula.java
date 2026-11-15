// The Quiz Scoring Formula
// In a quiz competition, scores are stored in a singly linked list.
// The scoring formula alternately adds and subtracts scores from the list,
// starting with an add operation from the head node.
// Specifically: result = score[0] - score[1] + score[2] - score[3] + ...
// (positions 0, 2, 4, ... are added; positions 1, 3, 5, ... are subtracted)
// Return the final computed score. If the list is empty, return 0.
//
// Example:
//   Input:  [10 -> 3 -> 8 -> 2 -> 5]
//   Formula: 10 - 3 + 8 - 2 + 5 = 18
//   Output:  18

public class QuizScoringFormula {

    static class Node {
        int score;
        Node next;

        Node(int score) {
            this.score = score;
            this.next = null;
        }
    }

    // Compute the alternating add/subtract score formula.
    // Single pass: alternate the sign for each node.
    // sign starts at +1 (add) and toggles between +1 and -1.
    public static int computeScore(Node head) {
        if (head == null) {
            return 0;
        }

        int result = 0;
        int sign = 1; // +1 for add, -1 for subtract
        Node current = head;

        while (current != null) {
            result += sign * current.score;
            sign = -sign; // toggle sign for next node
            current = current.next;
        }

        return result;
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
            System.out.print(current.score);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    // Helper: print the formula string for clarity
    static void printFormula(Node head) {
        Node current = head;
        boolean add = true;
        StringBuilder sb = new StringBuilder("Formula: ");
        while (current != null) {
            if (!add) sb.append(" - ");
            else if (current != head) sb.append(" + ");
            sb.append(current.score);
            add = !add;
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // Sample 1: [10, 3, 8, 2, 5] → 10 - 3 + 8 - 2 + 5 = 18
        Node head1 = buildList(new int[]{10, 3, 8, 2, 5});
        System.out.print("Scores:  ");
        printList(head1);
        printFormula(head1);
        System.out.println("Result:  " + computeScore(head1));

        System.out.println();

        // Sample 2: [100, 20, 50, 10] → 100 - 20 + 50 - 10 = 120
        Node head2 = buildList(new int[]{100, 20, 50, 10});
        System.out.print("Scores:  ");
        printList(head2);
        printFormula(head2);
        System.out.println("Result:  " + computeScore(head2));

        System.out.println();

        // Sample 3: [5] → 5 (single element, just returned as-is)
        Node head3 = buildList(new int[]{5});
        System.out.print("Scores:  ");
        printList(head3);
        printFormula(head3);
        System.out.println("Result:  " + computeScore(head3));

        System.out.println();

        // Sample 4: [8, 8] → 8 - 8 = 0
        Node head4 = buildList(new int[]{8, 8});
        System.out.print("Scores:  ");
        printList(head4);
        printFormula(head4);
        System.out.println("Result:  " + computeScore(head4));
    }
}
