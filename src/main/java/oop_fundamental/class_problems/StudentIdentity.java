class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}

class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;
    private static final String university = "SRM";
    private static int admissionCount;

    SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + (admissionCount + 10);
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo + " | " + university);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class StudentIdentity {
    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenSrmStudent raviBroken = new BrokenSrmStudent("Ravi", "RA001", 82);
        BrokenSrmStudent meeraBroken = new BrokenSrmStudent("Meera", "RA002", 74);
        System.out.println(raviBroken.name);
        System.out.println(meeraBroken.name);
        System.out.println("(Ravi's data was overwritten because all three fields are static)");

        System.out.println("\nFixed version:");
        SrmStudent ravi = new SrmStudent("Ravi", 82);
        SrmStudent meera = new SrmStudent("Meera", 74);
        ravi.printIdCard();
        meera.printIdCard();
        SrmStudent.printTotalAdmissions();
    }

    /*
     * name, regNo, and attendance are different for each student, so they must be
     * instance fields. university is shared by the college, while admissionCount
     * is one counter for the whole class, so those two fields are static.
     */
}
