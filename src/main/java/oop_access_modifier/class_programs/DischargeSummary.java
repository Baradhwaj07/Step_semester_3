public class DischargeSummary {
    private static final String MEDICATION_PREFIX;
    private final String patientId;
    private final String[] medicationCodes;

    static {
        MEDICATION_PREFIX = "MED-";
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (medicationCodes == null) {
            throw new IllegalArgumentException("Medication codes are required");
        }
        this.patientId = patientId;
        this.medicationCodes = new String[medicationCodes.length];
        for (int index = 0; index < medicationCodes.length; index++) {
            if (!isValidCode(medicationCodes[index])) {
                throw new IllegalArgumentException("Invalid medication code");
            }
            this.medicationCodes[index] = medicationCodes[index];
        }
    }

    private static boolean isValidCode(String code) {
        return code != null && code.matches("MED-[A-Z]");
    }

    public String[] getMedicationCodes() {
        String[] copy = new String[medicationCodes.length];
        for (int index = 0; index < medicationCodes.length; index++) {
            copy[index] = medicationCodes[index];
        }
        return copy;
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length || !isValidCode(newCode)) {
            throw new IllegalArgumentException("Invalid medication correction");
        }
        String[] corrected = getMedicationCodes();
        corrected[index] = newCode;
        return new DischargeSummary(patientId, corrected);
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int criticalCount = 0;
        int routineCount = 0;
        if (summaries != null) {
            for (DischargeSummary summary : summaries) {
                if (summary == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (summary instanceof CriticalCareDischargeSummary) {
                        criticalCount++;
                    } else {
                        routineCount++;
                    }
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + criticalCount
                + " critical-care | " + routineCount + " routine";
    }

}

class DischargeSummaryMain {
    public static void main(String[] args) {
        DischargeSummary summary = new DischargeSummary("MT2026-0142",
                new String[]{"MED-A", "MED-B"});
        String[] codes = summary.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(summary.getMedicationCodes()[0]);
        System.out.println(DischargeSummary.processNightlyBatch(new DischargeSummary[]{
                new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
                null, new DischargeSummary("MT002", new String[]{"MED-Y"})}));
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}
