// Problem 4: Static Members vs Instance Methods — Polymorphism Demo
// In Java, static members (methods and fields) are HIDDEN, not overridden.
// The reference type determines which static member is called at compile time.
// Instance methods are OVERRIDDEN and chosen at run time from the actual object.
//
// Key rules:
//   - Static method / field called on a variable → decided by the declared type (compile time).
//   - Instance method called on a variable     → decided by the actual object (run time).
//   - Inside HostelPin's own methods, 'name' refers to HostelPin's own field (closest scope).
//
// Hierarchy:
//   HostelRoom (base)
//     → name = "Generic Room"
//     → static getCategory() → "Standard"
//     → instance getDetails()  → shows HostelRoom's name
//   HostelPin (extends HostelRoom)
//     → name = "Hostel Pin Room"      (hides base field)
//     → static getCategory() → "Premium"   (hides base static method)
//     → instance getDetails() overrides → shows HostelPin's own name

public class HostelPinPolymorphism {

    static class HostelRoom {
        String name = "Generic Room";      // instance field

        // Static method — HIDDEN (not overridden) in subclass
        static String getCategory() {
            return "Standard";
        }

        // Instance method — will be OVERRIDDEN by subclass
        String getDetails() {
            return "HostelRoom | Name: " + name + " | Category: " + getCategory();
        }
    }

    static class HostelPin extends HostelRoom {
        String name = "Hostel Pin Room";   // hides HostelRoom.name (not override)

        // Static method HIDES HostelRoom.getCategory() — does NOT override
        static String getCategory() {
            return "Premium";
        }

        // Instance method OVERRIDES HostelRoom.getDetails()
        // Inside here, 'name' resolves to HostelPin's own 'name' field (closest scope)
        @Override
        String getDetails() {
            return "HostelPin | Name: " + name + " | Category: " + getCategory();
        }
    }

    public static void main(String[] args) {
        HostelRoom room = new HostelRoom();
        HostelPin pin   = new HostelPin();

        // --- Direct calls on actual types ---
        System.out.println("=== Direct object calls ===");
        System.out.println(room.getDetails());           // HostelRoom.getDetails()
        System.out.println(pin.getDetails());            // HostelPin.getDetails()
        System.out.println("room category: " + HostelRoom.getCategory()); // Standard
        System.out.println("pin  category: " + HostelPin.getCategory());  // Premium
        System.out.println();

        // --- Polymorphism: base reference pointing to subclass object ---
        HostelRoom ref = new HostelPin();   // reference type = HostelRoom, actual = HostelPin

        System.out.println("=== Polymorphic reference (HostelRoom ref = new HostelPin()) ===");

        // Instance method → run-time dispatch → calls HostelPin.getDetails() ✓
        System.out.println("ref.getDetails()   → " + ref.getDetails());

        // Static method  → compile-time type (HostelRoom) → calls HostelRoom.getCategory()
        System.out.println("ref.getCategory()  → " + ref.getCategory()); // "Standard" (not Premium!)

        // Field access    → compile-time type → HostelRoom.name
        System.out.println("ref.name           → " + ref.name);           // "Generic Room"
        System.out.println();

        // --- Field shadowing demo ---
        System.out.println("=== Field shadowing ===");
        HostelPin directPin = new HostelPin();
        System.out.println("directPin.name           → " + directPin.name);  // HostelPin's field
        System.out.println("((HostelRoom)pin).name   → " + ((HostelRoom) directPin).name); // HostelRoom's field
        System.out.println();

        // --- Summary ----
        System.out.println("=== Key Takeaways ===");
        System.out.println("Static methods are HIDDEN (compile-time type decides):");
        System.out.println("  HostelRoom ref = new HostelPin(); ref.getCategory() → \"Standard\"");
        System.out.println("Instance methods are OVERRIDDEN (run-time type decides):");
        System.out.println("  HostelRoom ref = new HostelPin(); ref.getDetails() → HostelPin's version");
        System.out.println("Fields are HIDDEN (compile-time type decides):");
        System.out.println("  Inside HostelPin.getDetails(), 'name' means HostelPin's own field.");
    }
}
