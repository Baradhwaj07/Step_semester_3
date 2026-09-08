class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) {
            return 0.0;
        }

        int total = 0;
        for (SrmStudent student : students) {
            if (student != null) {
                total += student.attendance;
            }
        }
        return (double) total / students.length;
    }

    void printAttendanceStatus() {
        String status = isEligible() ? "Eligible" : "Detained";
        System.out.println(name + " - " + attendance + "% - " + status);
    }
}

public class AttendanceSystem {
    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA001", 82),
            new SrmStudent("Anitha", "RA002", 68),
            new SrmStudent("Karthik", "RA003", 91),
            new SrmStudent("Meera", "RA004", 74),
            new SrmStudent("Suresh", "RA005", 60)
        };

        for (SrmStudent student : students) {
            student.printAttendanceStatus();
        }

        // classAverage uses the whole array; isEligible uses one student's attendance.
        System.out.println("Class average: " + SrmStudent.classAverage(students) + "%");
    }
}
