class MembershipLibraryMember {
    private final String membershipId;
    String branchCode;
    protected final double finesOwed;
    public final String displayName;

    public MembershipLibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("Membership ID must contain at least 4 characters");
        }
        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class MembershipFieldReachChecker {
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

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowed = new int[4];
        int[] denied = new int[4];
        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt == null || attempt.length < 2) {
                    continue;
                }
                for (int index = 0; index < modifiers.length; index++) {
                    if (modifiers[index].equals(attempt[0])) {
                        if ("ALLOWED".equals(classifyAccess(attempt[0], attempt[1]))) {
                            allowed[index]++;
                        } else {
                            denied[index]++;
                        }
                    }
                }
            }
        }
        return "private: " + allowed[0] + " allowed / " + denied[0] + " denied | default: "
                + allowed[1] + " allowed / " + denied[1] + " denied | protected: " + allowed[2]
                + " allowed / " + denied[2] + " denied | public: " + allowed[3] + " allowed / "
                + denied[3] + " denied";
    }

}

class MembershipFieldReachCheckerMain {
    public static void main(String[] args) {
        System.out.println(MembershipFieldReachChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(MembershipFieldReachChecker.summarizeByModifier(new String[][]{{"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"}, {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"}, {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"}, {"public", "DIFFERENT_PACKAGE"}}));
        try {
            new MembershipLibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException exception) {
            System.out.println("construction rejected");
        }
    }
}
