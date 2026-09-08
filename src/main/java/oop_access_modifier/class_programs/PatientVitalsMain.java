class PatientVitals {
    private final double[] readings;
    private int readingCount;

    PatientVitals(double[] initialReadings) {
        readings = new double[500];
        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    void recordReading(double reading) {
        if (reading > 0 && reading <= 45 && readingCount < readings.length) {
            readings[readingCount] = reading;
            readingCount++;
        }
    }

    double getAverage() {
        if (readingCount == 0) {
            return 0.0;
        }
        double total = 0.0;
        for (int index = 0; index < readingCount; index++) {
            total += readings[index];
        }
        return total / readingCount;
    }

    double[] getAllReadings() {
        double[] result = new double[readingCount];
        for (int index = 0; index < readingCount; index++) {
            result[index] = readings[index];
        }
        return result;
    }

}

public class PatientVitalsMain {
    public static void main(String[] args) {
        PatientVitals vitals = new PatientVitals(new double[]{36.5, -2, 37.1});
        double[] readings = vitals.getAllReadings();
        for (double reading : readings) {
            System.out.print(reading + " ");
        }
        System.out.println();
        readings[0] = 999;
        System.out.println(vitals.getAllReadings()[0]);
    }
}
