class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {
    private String name;
    private String memberId;
    private int booksIssued;
    private static final String libraryName = "SRM Central Library";
    private static int memberCount;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class libraryMembership {
    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenLibraryMember aditiBroken = new BrokenLibraryMember("Aditi", "LM-001", 2);
        BrokenLibraryMember rohanBroken = new BrokenLibraryMember("Rohan", "LM-002", 1);
        System.out.println(aditiBroken.name);
        System.out.println(rohanBroken.name);
        System.out.println("(Aditi's data was overwritten because all three fields are static)");

        System.out.println("\nFixed version:");
        LibraryMember aditi = new LibraryMember("Aditi", 2);
        LibraryMember rohan = new LibraryMember("Rohan", 1);
        aditi.printMemberCard();
        rohan.printMemberCard();
        LibraryMember.printTotalMembers();
    }

    /*
     * name, memberId, and booksIssued belong to individual members, so making them
     * static would overwrite one member with another. libraryName is shared by the
     * library, and memberCount is one count for all members, so both are static.
     */
}
