import java.util.Arrays;

// Problem 5: Student Leaderboard — Merge Sort + Kadane's Algorithm + Two-Pointer
// Three sub-tasks on a dataset of students with weekly quiz scores:
//
// Part A — Sort students by total score descending using Merge Sort (stable sort).
//   Students with equal total scores retain their original order (stability matters).
//   "Asha stays above Neha because merge sort is stable."
//
// Part B — Kadane's Algorithm: Find the maximum sum of a consecutive sub-sequence
//   from a student's weekly score-change array (differences between consecutive weeks).
//   "Ravi's weekly changes are +2, −7, −5, +15, +3, so his best run is +15 +3 = +18."
//
// Part C — Two-Pointer: Given a sorted array of scores, find a pair that sums to a target.
//   "For PAIR 785, the pointers move from 420+380 (too big) → 418+380 → 405+380 = 785."

public class StudentLeaderboard {

    static class Student {
        String name;
        int[] weeklyScores;
        int totalScore;

        Student(String name, int[] weeklyScores) {
            this.name = name;
            this.weeklyScores = weeklyScores;
            this.totalScore = Arrays.stream(weeklyScores).sum();
        }

        @Override
        public String toString() {
            return String.format("%-10s | Total: %3d | Weeks: %s",
                    name, totalScore, Arrays.toString(weeklyScores));
        }
    }

    // ---- Part A: Stable Merge Sort (descending by totalScore) ----
    public static void mergeSort(Student[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Student[] arr, int left, int mid, int right) {
        Student[] temp = new Student[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Use >= so when scores are equal, left (earlier original) element is picked first → stable
            if (arr[i].totalScore >= arr[j].totalScore) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid)  temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // ---- Part B: Kadane's Algorithm — max sum contiguous subarray ----
    // Applied on the weekly score-change array (differences between consecutive weeks).
    public static int maxConsecutiveSum(int[] changes) {
        int maxSoFar = changes[0];
        int maxEndingHere = changes[0];

        for (int i = 1; i < changes.length; i++) {
            maxEndingHere = Math.max(changes[i], maxEndingHere + changes[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    // Compute the week-to-week changes array for a student
    public static int[] computeChanges(int[] weeklyScores) {
        int[] changes = new int[weeklyScores.length - 1];
        for (int i = 0; i < changes.length; i++) {
            changes[i] = weeklyScores[i + 1] - weeklyScores[i];
        }
        return changes;
    }

    // ---- Part C: Two-Pointer — find pair in sorted array summing to target ----
    public static int[] findPair(int[] sortedScores, int target) {
        int left = 0;
        int right = sortedScores.length - 1;

        while (left < right) {
            int sum = sortedScores[left] + sortedScores[right];
            if (sum == target) {
                return new int[]{sortedScores[left], sortedScores[right]};
            } else if (sum > target) {
                right--; // sum too large, move right pointer left
            } else {
                left++;  // sum too small, move left pointer right
            }
        }
        return null; // no pair found
    }

    public static void main(String[] args) {
        // ---- Dataset ----
        Student[] students = {
            new Student("Asha",  new int[]{40, 42, 35, 55, 47, 50}),  // total=269
            new Student("Devi",  new int[]{60, 62, 28, 50, 55, 70}),  // total=325
            new Student("Neha",  new int[]{40, 35, 29, 55, 60, 50}),  // total=269  (same as Asha)
            new Student("Ravi",  new int[]{30, 32, 25, 20, 35, 38}),  // total=180
            new Student("Mulexa",new int[]{70, 68, 72, 80, 65, 75}),  // total=430
        };

        // ---- Part A: Sort by total score descending (stable merge sort) ----
        System.out.println("=== Part A: Student Leaderboard (Stable Merge Sort — descending) ===");
        System.out.println("Original Order:");
        for (int i = 0; i < students.length; i++) {
            System.out.printf("  %d. %s%n", i + 1, students[i]);
        }

        mergeSort(students, 0, students.length - 1);

        System.out.println("\nSorted Leaderboard:");
        for (int i = 0; i < students.length; i++) {
            System.out.printf("  Rank %d: %s%n", i + 1, students[i]);
        }
        System.out.println("  → Asha stays above Neha (same score=269, stable sort preserves original order)");
        System.out.println();

        // ---- Part B: Kadane's on Ravi's weekly score changes ----
        System.out.println("=== Part B: Kadane's Algorithm — Best Score Run ===");
        // Use original Ravi data (find in sorted array)
        int[] raviScores = {30, 32, 25, 20, 35, 38};
        int[] changes = computeChanges(raviScores);
        System.out.print("Ravi's weekly scores : " + Arrays.toString(raviScores));
        System.out.print("\nWeek-to-week changes : " + Arrays.toString(changes));
        int bestRun = maxConsecutiveSum(changes);
        System.out.println("\nBest consecutive run : " + bestRun);
        System.out.println("  → Largest consecutive gain from the change array");
        System.out.println();

        // Extra example matching the PDF hint: changes = [+2, −7, −5, +15, +3]
        int[] exampleChanges = {2, -7, -5, 15, 3};
        System.out.println("Example changes: " + Arrays.toString(exampleChanges));
        System.out.println("Best run (Kadane's): " + maxConsecutiveSum(exampleChanges) + "  (expected: 18 = 15+3)");
        System.out.println();

        // ---- Part C: Two-Pointer — find pair summing to target ----
        System.out.println("=== Part C: Two-Pointer — Find Score Pair ===");
        int[] sortedScores = {180, 269, 269, 325, 430};
        System.out.println("Sorted scores: " + Arrays.toString(sortedScores));

        int target1 = 785;
        int[] pair1 = findPair(sortedScores, target1);
        System.out.print("PAIR " + target1 + ": ");
        System.out.println(pair1 != null
                ? pair1[0] + " + " + pair1[1] + " = " + target1
                : "No pair found");

        // PDF example: 405 + 380 = 785
        int[] pdfExample = {380, 405, 418, 420, 500};
        System.out.println("\nPDF example array: " + Arrays.toString(pdfExample));
        int[] pair2 = findPair(pdfExample, 785);
        System.out.print("PAIR 785: ");
        System.out.println(pair2 != null
                ? pair2[0] + " + " + pair2[1] + " = 785"
                : "No pair found");
        System.out.println("  → Pointers: 420+380=800 (too big) → 418+380=798 → 405+380=785 ✓");
    }
}
