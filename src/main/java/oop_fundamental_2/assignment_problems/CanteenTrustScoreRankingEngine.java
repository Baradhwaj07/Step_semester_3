class Canteen {
    private final String canteenCode;
    private final String canteenName;
    private final int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {
        int result = Integer.compare(other.trustScore, trustScore);
        if (result != 0) {
            return result;
        }

        result = compareIgnoringCase(canteenCode, other.canteenCode);
        if (result != 0) {
            return result;
        }

        return Integer.compare(nameLength(canteenName), nameLength(other.canteenName));
    }

    private static int compareIgnoringCase(String first, String second) {
        if (first == null && second == null) {
            return 0;
        }
        if (first == null) {
            return -1;
        }
        if (second == null) {
            return 1;
        }
        return first.compareToIgnoreCase(second);
    }

    private static int nameLength(String name) {
        return name == null ? -1 : name.length();
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null) {
            return null;
        }

        Canteen[] ranked = canteens.clone();
        for (int end = ranked.length - 1; end > 0; end--) {
            for (int index = 0; index < end; index++) {
                if (ranked[index].compareTo(ranked[index + 1]) > 0) {
                    Canteen temporary = ranked[index];
                    ranked[index] = ranked[index + 1];
                    ranked[index + 1] = temporary;
                }
            }
        }
        return ranked;
    }

    public String getCanteenCode() {
        return canteenCode;
    }
}

public class CanteenTrustScoreRankingEngine {
}
