class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Payment rejected for " + regNo);
            return;
        }
        amountPaid += amount;
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        if (amount > 0) {
            pay(amount / 2);
            pay(amount / 2);
        } else {
            pay(amount);
        }
    }
}

class HostelRoom {
    private String roomNo;
    private int beds;
    private int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    boolean hasAvailableBed() {
        return occupied < beds;
    }

    void allot() {
        if (hasAvailableBed()) {
            occupied++;
        }
    }

    String getRoomNo() {
        return roomNo;
    }
}

class SrmStudent {
    static int totalStudents;
    private String name;
    private String regNo;
    private HostelFeeAccount feeAccount;
    private HostelRoom room;

    SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        totalStudents++;
    }

    void assignRoom(HostelRoom room) {
        if (room != null && room.hasAvailableBed()) {
            room.allot();
            this.room = room;
        }
    }

    void payFee(double amount) {
        feeAccount.pay(amount);
    }

    void payFeeInTwoInstallments(double amount) {
        feeAccount.payInTwoInstallments(amount);
    }

    String fullStatus() {
        String roomStatus = room == null ? "unallotted" : room.getRoomNo();
        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomStatus;
    }
}

public class MiniSystem {
    public static void main(String[] args) {
        HostelRoom firstRoom = new HostelRoom("C-214", 1, 0);
        HostelRoom secondRoom = new HostelRoom("C-507", 1, 0);

        SrmStudent ravi = new SrmStudent("Ravi", "RA001", new HostelFeeAccount("RA001", 150000));
        SrmStudent anitha = new SrmStudent("Anitha", "RA002", new HostelFeeAccount("RA002", 200000));
        SrmStudent karthik = new SrmStudent("Karthik", "RA003", new HostelFeeAccount("RA003", 200000));

        ravi.assignRoom(firstRoom);
        anitha.assignRoom(secondRoom);

        ravi.payFeeInTwoInstallments(10000);
        anitha.payFee(20000);
        karthik.payFee(-5000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
