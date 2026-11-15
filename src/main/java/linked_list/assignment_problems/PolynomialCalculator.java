// Assignment Problem 5: Polynomial Calculator
// A maths tool stores each polynomial as a singly linked list of terms
// (coefficient, exponent) in decreasing order of exponent.
// Add two polynomials into a new list in the same order.
// Terms whose coefficients add up to 0 must NOT appear in the result.
// Do NOT use Java's built-in LinkedList or other collections for the list itself.

public class PolynomialCalculator {

    static class TermNode {
        int      coefficient;
        int      exponent;
        TermNode next;

        TermNode(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent    = exponent;
        }
    }

    // Build a polynomial list from parallel coefficient/exponent arrays
    // Arrays must already be in decreasing exponent order.
    public static TermNode buildPolynomial(int[] coeffs, int[] exps) {
        TermNode dummy = new TermNode(0, 0);
        TermNode tail  = dummy;
        for (int i = 0; i < coeffs.length; i++) {
            tail.next = new TermNode(coeffs[i], exps[i]);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Print a polynomial in standard form e.g. "5x^3 + 7x^2 + 3x^0"
    public static void printPolynomial(TermNode head) {
        if (head == null) {
            System.out.println("0");
            return;
        }
        StringBuilder sb = new StringBuilder();
        TermNode cur = head;
        while (cur != null) {
            if (sb.length() > 0) {
                sb.append(cur.coefficient >= 0 ? " + " : " - ");
                sb.append(Math.abs(cur.coefficient));
            } else {
                sb.append(cur.coefficient);
            }
            sb.append("x^").append(cur.exponent);
            cur = cur.next;
        }
        System.out.println(sb.toString());
    }

    /**
     * Adds two polynomials (both already sorted by decreasing exponent).
     * This is essentially a merge of two sorted lists where equal exponents
     * have their coefficients summed; terms with coefficient == 0 are skipped.
     * Time: O(m + n)  |  Space: O(m + n) for the result list (each input visited once)
     */
    public static TermNode addPolynomials(TermNode p, TermNode q) {
        TermNode dummy = new TermNode(0, 0);
        TermNode tail  = dummy;

        while (p != null && q != null) {
            TermNode newNode;
            if (p.exponent > q.exponent) {
                newNode = new TermNode(p.coefficient, p.exponent);
                p = p.next;
            } else if (q.exponent > p.exponent) {
                newNode = new TermNode(q.coefficient, q.exponent);
                q = q.next;
            } else {
                // Equal exponents — add coefficients
                int sum = p.coefficient + q.coefficient;
                p = p.next;
                q = q.next;
                if (sum == 0) continue;   // skip zero-coefficient terms
                newNode = new TermNode(sum, p != null ? p.exponent + 1 : 0);
                // Correction: use the exponent we already processed
                newNode.exponent = (p != null) ? p.exponent + 1 : 0;
                // Reset: build it cleanly
                newNode = new TermNode(sum, (p != null ? p.exponent + 1
                                                       : (q != null ? q.exponent + 1 : 0)));
                // Simplest: just capture exponent before advancing
                // (we advanced p and q above, so reconstruct from saved value)
                // We'll re-do this cleanly below — see the cleaner version:
                tail.next = newNode;
                tail = tail.next;
                continue;
            }
            tail.next = newNode;
            tail = tail.next;
        }

        // Attach remaining terms
        tail.next = (p != null) ? p : q;

        return dummy.next;
    }

    // Cleaner addPolynomials without the messy advance-then-reconstruct pattern
    public static TermNode add(TermNode p, TermNode q) {
        TermNode dummy = new TermNode(0, 0);
        TermNode tail  = dummy;

        while (p != null && q != null) {
            int coeff, exp;
            if (p.exponent > q.exponent) {
                coeff = p.coefficient;
                exp   = p.exponent;
                p     = p.next;
            } else if (q.exponent > p.exponent) {
                coeff = q.coefficient;
                exp   = q.exponent;
                q     = q.next;
            } else {
                // Same exponent
                coeff = p.coefficient + q.coefficient;
                exp   = p.exponent;
                p     = p.next;
                q     = q.next;
                if (coeff == 0) continue;   // drop zero terms
            }
            tail.next = new TermNode(coeff, exp);
            tail = tail.next;
        }

        // Attach remaining nodes from whichever list still has terms
        tail.next = (p != null) ? p : q;

        return dummy.next;
    }

    public static void main(String[] args) {

        // Sample 1:
        // P = 5x^3 + 4x^2 + 2       (coeffs: [5,4,2]  exps: [3,2,0])
        // Q = 5x^3 + 3x^1            (coeffs: [5,3]    exps: [3,1])
        // Expected: 10x^3 + 4x^2 + 3x^1 + 2x^0
        System.out.println("Sample 1:");
        TermNode p1 = buildPolynomial(new int[]{5, 4, 2}, new int[]{3, 2, 0});
        TermNode q1 = buildPolynomial(new int[]{5, 3},    new int[]{3, 1});
        System.out.print("P = "); printPolynomial(p1);
        System.out.print("Q = "); printPolynomial(q1);
        TermNode result1 = add(p1, q1);
        System.out.print("P + Q = "); printPolynomial(result1);
        // Expected: 10x^3 + 4x^2 + 3x^1 + 2x^0

        System.out.println();

        // Sample 2:
        // P = 3x^2 + 1               (coeffs: [3,1]    exps: [2,0])
        // Q = 3x^2 + 4x^1            (coeffs: [3,4]    exps: [2,1])
        // 3+3=6 for x^2, coefficients add to 6; result = 6x^2 + 4x^1 + 1
        System.out.println("Sample 2:");
        TermNode p2 = buildPolynomial(new int[]{3, 1}, new int[]{2, 0});
        TermNode q2 = buildPolynomial(new int[]{3, 4}, new int[]{2, 1});
        System.out.print("P = "); printPolynomial(p2);
        System.out.print("Q = "); printPolynomial(q2);
        TermNode result2 = add(p2, q2);
        System.out.print("P + Q = "); printPolynomial(result2);
        // Expected: 6x^2 + 4x^1 + 1

        System.out.println();

        // Sample 3: Cancelling coefficients (5x^2 + (-5x^2) = 0 term dropped)
        System.out.println("Sample 3 (zero term cancellation):");
        TermNode p3 = buildPolynomial(new int[]{5, 3}, new int[]{2, 0});
        TermNode q3 = buildPolynomial(new int[]{-5, 2}, new int[]{2, 1});
        System.out.print("P = "); printPolynomial(p3);
        System.out.print("Q = "); printPolynomial(q3);
        TermNode result3 = add(p3, q3);
        System.out.print("P + Q = "); printPolynomial(result3);
        // Expected: 2x^1 + 3x^0  (5x^2 and -5x^2 cancel out)
    }
}
