class BusRoute {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 0);
    }

    public int compareTo(BusRoute other) {
        int result = Integer.compare(priority, other.priority);
        if (result != 0) {
            return -result;
        }
        result = compareIgnoringCase(routeCode, other.routeCode);
        if (result != 0) {
            return result;
        }
        return compareIgnoringCase(routeName, other.routeName);
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
        int result = first.compareToIgnoreCase(second);
        return result != 0 ? result : first.compareTo(second);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null) {
            return null;
        }
        BusRoute[] ranked = routes.clone();
        for (int end = ranked.length - 1; end > 0; end--) {
            for (int index = 0; index < end; index++) {
                if (ranked[index].compareTo(ranked[index + 1]) > 0) {
                    BusRoute temporary = ranked[index];
                    ranked[index] = ranked[index + 1];
                    ranked[index + 1] = temporary;
                }
            }
        }
        return ranked;
    }

    public String getRouteCode() {
        return routeCode;
    }
}

public class BusRouteRankingEngine {
}
