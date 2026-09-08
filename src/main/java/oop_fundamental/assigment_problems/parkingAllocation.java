class ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    boolean hasSpace() {
        return occupiedCount < capacity;
    }
}

public class parkingAllocation {
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null) {
            return null;
        }
        for (ParkingSlot slot : slots) {
            if (slot != null && slot.hasSpace()) {
                return slot;
            }
        }
        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot == null) {
            System.out.println("No slots available for " + vehicleNo);
            return;
        }
        slot.allot(vehicleNo);
    }

    public static void main(String[] args) {
        ParkingSlot[] slotsWithSpace = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };
        safeAllot(slotsWithSpace, "TN09AB1234");

        ParkingSlot[] fullSlots = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };
        safeAllot(fullSlots, "TN09AB1234");
    }

    // The array passes references to ParkingSlot objects, not copies of those objects.
}
