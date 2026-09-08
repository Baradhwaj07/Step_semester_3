class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    boolean hasSpace() {
        return occupiedCount < capacity;
    }

    void allot() {
        if (hasSpace()) {
            occupiedCount++;
        }
    }

    String getSlotNo() {
        return slotNo;
    }
}

class CompanyEmployeeRecord {
    static int totalRecords;
    private String name;
    private String empId;
    private Employee employee;
    private ParkingSlot slot;

    CompanyEmployeeRecord(String name, String empId, Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        totalRecords++;
    }

    void assignSlot(ParkingSlot slot) {
        if (slot != null && slot.hasSpace()) {
            slot.allot();
            this.slot = slot;
        }
    }

    double effectivePay() {
        if (employee instanceof ManagerEmployee) {
            return ((ManagerEmployee) employee).effectiveSalary();
        }
        return employee.getSalary();
    }

    String fullProfile() {
        String slotStatus = slot == null ? "no parking assigned" : slot.getSlotNo();
        return name + " | Pay: Rs " + effectivePay() + " | Slot: " + slotStatus;
    }
}

public class hrParkingSystem {
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
        slot.allot();
        System.out.println(vehicleNo + " allotted to slot " + slot.getSlotNo());
    }

    public static void main(String[] args) {
        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        CompanyEmployeeRecord divya = new CompanyEmployeeRecord(
                "Divya", "E101", new ManagerEmployee(101, "Divya", 70000, 8000));
        CompanyEmployeeRecord karan = new CompanyEmployeeRecord(
                "Karan", "E102", new Employee(102, "Karan", 40000));
        CompanyEmployeeRecord meera = new CompanyEmployeeRecord(
                "Meera", "E103", new Employee(103, "Meera", 10000));

        ParkingSlot firstSlot = findAvailableSlot(slots);
        divya.assignSlot(firstSlot);
        ParkingSlot secondSlot = findAvailableSlot(slots);
        karan.assignSlot(secondSlot);

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
