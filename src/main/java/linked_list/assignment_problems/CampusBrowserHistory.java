// Assignment Problem 2: Campus Portal Browser History
// A campus portal browser supports three actions on a doubly linked list:
//   visit(page)       — navigate to a new page (clears all forward history)
//   back(steps)       — go back up to `steps` pages
//   forward(steps)    — go forward up to `steps` pages
// Back and forward stop at the first / last available page.
// Do NOT use Java's built-in LinkedList or other collections for the list itself.

public class CampusBrowserHistory {

    static class Node {
        String page;
        Node   prev;
        Node   next;

        Node(String page) {
            this.page = page;
        }
    }

    private Node current;   // pointer to the current page node

    public CampusBrowserHistory(String homePage) {
        current = new Node(homePage);
    }

    // Visit a new page — link it after current and discard everything that followed
    public void visit(String page) {
        Node newNode  = new Node(page);
        newNode.prev  = current;
        current.next  = newNode;   // everything after current is discarded (GC collects it)
        current       = newNode;
        System.out.println("visit " + page);
    }

    // Go back up to `steps` pages; stop at the first page available
    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        System.out.println("BACK " + steps + " steps → " + current.page);
        return current.page;
    }

    // Go forward up to `steps` pages; stop at the last page available
    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        System.out.println("FORWARD " + steps + " steps → " + current.page);
        return current.page;
    }

    public String currentPage() {
        return current.page;
    }

    public static void main(String[] args) {

        // Sample input (from the problem PDF):
        // visit srmed   visit acom   visit bcom   visit ccom
        // BACK 1        BACK 1       FORWARD 1    visit dcom
        // FORWARD 2    BACK 1        BACK 1

        CampusBrowserHistory browser = new CampusBrowserHistory("srmed");

        browser.visit("acom");   // history: srmed -> acom*
        browser.visit("bcom");   // history: srmed -> acom -> bcom*
        browser.visit("ccom");   // history: srmed -> acom -> bcom -> ccom*

        browser.back(1);         // → bcom        (Expected: bcom)
        browser.back(1);         // → acom        (Expected: acom)
        browser.forward(1);      // → bcom        (Expected: bcom)

        browser.visit("dcom");   // history: srmed -> acom -> bcom -> dcom*  (ccom discarded)

        browser.forward(2);      // → dcom (already at end)  (Expected: dcom)
        browser.back(1);         // → bcom        (Expected: bcom)
        browser.back(1);         // → acom        (Expected: acom)
    }
}
