import java.util.LinkedHashMap;
import java.util.Map;

public class StudentMarksTracker {

    private String[] subjects;
    private Map<String, int[]> studentMarks = new LinkedHashMap<>();

    StudentMarksTracker(String[] subjects) {
        this.subjects = subjects;
    }

    public void addStudent(String name, int[] marks) {
        studentMarks.put(name, marks);
    }

    // Print total marks for each student
    public void printTotals() {
        System.out.println("Totals");
        for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
            int total = 0;
            for (int mark : entry.getValue()) {
                total += mark;
            }
            System.out.println(entry.getKey() + " " + total);
        }
    }

    // Print the top scorer for each subject
    public void printToppers() {
        System.out.println("toppers");
        for (int j = 0; j < subjects.length; j++) {
            String topper  = null;
            int    maxMark = -1;

            for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
                int[] marks = entry.getValue();
                // Student may not have a mark for this subject
                if (j < marks.length && marks[j] > maxMark) {
                    maxMark = marks[j];
                    topper  = entry.getKey();
                }
            }

            if (topper != null) {
                System.out.println(subjects[j] + " " + topper + " " + maxMark);
            }
        }
    }

    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        StudentMarksTracker tracker = new StudentMarksTracker(subjects);

        tracker.addStudent("Anu",   new int[]{80, 90, 70});
        tracker.addStudent("Ravi",  new int[]{60, 85});       // missing Eng
        tracker.addStudent("Meena", new int[]{95, 75, 88});

        tracker.printTotals();
        tracker.printToppers();
    }
}
