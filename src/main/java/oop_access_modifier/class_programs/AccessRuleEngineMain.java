class PatientRecord {
    private final String patientId;
    String wardCode;
    protected final double vitalsScore;
    public final String facilityName;

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("Patient ID must contain at least 4 characters");
        }
        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

class AccessRuleEngine {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("public".equals(fieldModifier)) {
            return "ALLOWED";
        }
        if ("private".equals(fieldModifier)) {
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
        }
        if ("default".equals(fieldModifier)) {
            return "SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)
                    ? "ALLOWED" : "DENIED";
        }
        if ("protected".equals(fieldModifier)) {
            return "SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)
                    ? "ALLOWED" : "DENIED";
        }
        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2
                        && "ALLOWED".equals(classifyAccess(attempt[0], attempt[1]))) {
                    allowed++;
                } else {
                    denied++;
                }
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

}

public class AccessRuleEngineMain {
    public static void main(String[] args) {
        System.out.println(AccessRuleEngine.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessRuleEngine.classifyAccess("default", "DIFFERENT_PACKAGE"));
        System.out.println(AccessRuleEngine.summarizeBatch(new String[][]{{"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"}, {"public", "DIFFERENT_PACKAGE"}}));
        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
        } catch (IllegalArgumentException exception) {
            System.out.println("construction rejected");
        }
    }
}
