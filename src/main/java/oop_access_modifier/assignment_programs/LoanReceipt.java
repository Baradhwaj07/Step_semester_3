public class LoanReceipt {
    private static final String BOOK_PREFIX;
    private final String memberId;
    private final String[] bookIds;

    static {
        BOOK_PREFIX = "BK-";
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null) {
            throw new IllegalArgumentException("Book IDs are required");
        }
        this.memberId = memberId;
        this.bookIds = new String[bookIds.length];
        for (int index = 0; index < bookIds.length; index++) {
            if (!isValidBookId(bookIds[index])) {
                throw new IllegalArgumentException("Invalid book ID");
            }
            this.bookIds[index] = bookIds[index];
        }
    }

    private static boolean isValidBookId(String bookId) {
        return bookId != null && bookId.matches("BK-[0-9]{3}");
    }

    public String[] getBookIds() {
        String[] copy = new String[bookIds.length];
        for (int index = 0; index < bookIds.length; index++) {
            copy[index] = bookIds[index];
        }
        return copy;
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length || !isValidBookId(newId)) {
            throw new IllegalArgumentException("Invalid book correction");
        }
        String[] corrected = getBookIds();
        corrected[index] = newId;
        return new LoanReceipt(memberId, corrected);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;
        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt) {
                        referenceOnly++;
                    } else {
                        regular++;
                    }
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly
                + " reference-only | " + regular + " regular";
    }

}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

class LoanReceiptMain {
    public static void main(String[] args) {
        LoanReceipt receipt = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = receipt.getBookIds();
        ids[0] = "HACKED";
        System.out.println(receipt.getBookIds()[0]);
        System.out.println(LoanReceipt.processNightlyCirculation(new LoanReceipt[]{
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null, new LoanReceipt("LIB-002", new String[]{"BK-201"})}));
    }
}
