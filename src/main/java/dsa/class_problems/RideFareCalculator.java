// Problem 2: Ride Fare Calculator
// A ride-booking platform offers two vehicle types — Cab and Bike.
// Fare calculation rules:
//   Cab  : fare = (baseFare + ratePerKm × distance) × surgeFactor
//   Bike : fare = (baseFare + ratePerKm × distance) + waitingCharge
// Both vehicle types share common booking fields (name, baseFare, ratePerKm, distance).
// Surge factor applies only to cabs; waiting charge applies only to bikes.
// Use inheritance: a base RideVehicle class with Cab and Bike as subclasses.
// computeFare() is overridden in each subclass to apply the correct formula.
//
// Sample:
//   Cab (base=50, rate=20, distance=6, surge=1.5) → (50 + 20×6) × 1.5 = 255.0
//   Bike (base=10, rate=15, distance=4, waiting=20) → (10 + 15×4) + 20 = 90.0

public class RideFareCalculator {

    // ---- Base class ----
    static abstract class RideVehicle {
        String vehicleType;
        double baseFare;
        double ratePerKm;
        double distanceKm;

        RideVehicle(String vehicleType, double baseFare, double ratePerKm, double distanceKm) {
            this.vehicleType = vehicleType;
            this.baseFare = baseFare;
            this.ratePerKm = ratePerKm;
            this.distanceKm = distanceKm;
        }

        // Template method: subclasses override to apply vehicle-specific rules
        abstract double computeFare();

        void printReceipt() {
            System.out.println("Vehicle     : " + vehicleType);
            System.out.printf("Base Fare   : %.1f%n", baseFare);
            System.out.printf("Rate/km     : %.1f%n", ratePerKm);
            System.out.printf("Distance    : %.1f km%n", distanceKm);
        }
    }

    // ---- Cab subclass ----
    static class Cab extends RideVehicle {
        double surgeFactor;

        Cab(double baseFare, double ratePerKm, double distanceKm, double surgeFactor) {
            super("Cab", baseFare, ratePerKm, distanceKm);
            this.surgeFactor = surgeFactor;
        }

        @Override
        double computeFare() {
            // Apply surge after the base calculation
            double baseCost = baseFare + ratePerKm * distanceKm;
            return baseCost * surgeFactor;
        }

        @Override
        void printReceipt() {
            super.printReceipt();
            System.out.printf("Surge Factor: %.1fx%n", surgeFactor);
            System.out.printf("Formula     : (%.1f + %.1f × %.1f) × %.1f%n",
                    baseFare, ratePerKm, distanceKm, surgeFactor);
            System.out.printf("Total Fare  : %.1f%n", computeFare());
        }
    }

    // ---- Bike subclass ----
    static class Bike extends RideVehicle {
        double waitingCharge;

        Bike(double baseFare, double ratePerKm, double distanceKm, double waitingCharge) {
            super("Bike", baseFare, ratePerKm, distanceKm);
            this.waitingCharge = waitingCharge;
        }

        @Override
        double computeFare() {
            // Add waiting charge after base calculation (no surge for bikes)
            double baseCost = baseFare + ratePerKm * distanceKm;
            return baseCost + waitingCharge;
        }

        @Override
        void printReceipt() {
            super.printReceipt();
            System.out.printf("Waiting Chg : %.1f%n", waitingCharge);
            System.out.printf("Formula     : (%.1f + %.1f × %.1f) + %.1f%n",
                    baseFare, ratePerKm, distanceKm, waitingCharge);
            System.out.printf("Total Fare  : %.1f%n", computeFare());
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Cab Ride =====");
        // Sample: (50 + 20 × 6) × 1.5 = 255
        Cab cab1 = new Cab(50, 20, 6, 1.5);
        cab1.printReceipt();
        System.out.println();

        System.out.println("===== Bike Ride =====");
        // Sample: (10 + 15 × 4) + 20 = 90
        Bike bike1 = new Bike(10, 15, 4, 20);
        bike1.printReceipt();
        System.out.println();

        // Polymorphic usage via base class reference
        System.out.println("===== All Rides (Polymorphic) =====");
        RideVehicle[] rides = {
            new Cab(50, 20, 6, 1.5),
            new Bike(10, 15, 4, 20),
            new Cab(80, 25, 10, 2.0),
            new Bike(15, 12, 7, 0)
        };
        for (RideVehicle ride : rides) {
            System.out.printf("%-5s | Fare: %.1f%n", ride.vehicleType, ride.computeFare());
        }
    }
}
