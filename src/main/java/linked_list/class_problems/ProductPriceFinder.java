public class ProductPriceFinder {

    /**
     * Returns the first index where prices[index] >= lowerPrice (lower bound).
     * This is the classic binary search lower_bound.
     */
    public static int lowerBound(int[] prices, int lowerPrice) {
        int left = 0, right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] < lowerPrice) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * Returns the first index where prices[index] > upperPrice (upper bound).
     * Elements from lowerBound to upperBound-1 are within the inclusive range.
     */
    public static int upperBound(int[] prices, int upperPrice) {
        int left = 0, right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] <= upperPrice) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static String formatPrices(int[] prices, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (i > start) sb.append(" ");
            sb.append(prices[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int lower = 150;
        int upper = 300;

        int lowerIdx = lowerBound(prices, lower);
        int upperIdx = upperBound(prices, upper);
        int count    = upperIdx - lowerIdx;

        System.out.println("Lower bound index " + lowerIdx);
        System.out.println("Upper bound index " + upperIdx);
        System.out.println("Count " + count
                + " (products with prices " + formatPrices(prices, lowerIdx, upperIdx) + ")");
    }
}
