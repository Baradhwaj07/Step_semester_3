class BookIssue {
    private String title;
    private String borrowerName;
    private int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {
        double totalFine = 0.0;
        for (BookIssue issue : issues) {
            totalFine += issue.fineAmount();
        }
        return totalFine;
    }

    void printStatus() {
        String status = isSeverelyOverdue() ? "Severely overdue" : "OK";
        System.out.println(title + " - " + daysOverdue + " days - " + status);
    }
}

public class libraryFineCollector {
    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Aditi", 18),
            new BookIssue("Effective Java", "Rohan", 5),
            new BookIssue("Refactoring", "Meera", 0),
            new BookIssue("DSA Handbook", "Karan", 21),
            new BookIssue("Design Patterns", "Divya", 9)
        };

        for (BookIssue issue : issues) {
            issue.printStatus();
        }

        // One book calculates its own fine; this method totals a whole collection.
        System.out.println("Total fine collected: Rs "
                + BookIssue.totalFineCollected(issues));
    }
}