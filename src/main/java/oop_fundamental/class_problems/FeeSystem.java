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
        if (amount <= 0) {
            pay(amount);
            return;
        }
        pay(amount / 2);
        pay(amount / 2);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        if (scholarshipPercent < 0 || scholarshipPercent > 100) {
            throw new IllegalArgumentException("Scholarship must be between 0 and 100");
        }
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}

public class FeeSystem {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA001", 150000);
        plain.pay(150000);

        HostelFeeAccount hostel = new HostelFeeAccount("RA002", 200000);
        hostel.payInTwoInstallments(60000);

        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA003", 180000, 20);

        System.out.println("Plain account due: Rs " + plain.getDue());
        System.out.println("Hostel account due: Rs " + hostel.getDue());

        if (plain instanceof HostelFeeAccount) {
            ((HostelFeeAccount) plain).payInTwoInstallments(1000);
        }
        if (hostel instanceof HostelFeeAccount) {
            System.out.println("Hostel account supports installment payments.");
        }
        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println("Scholarship account effective due: Rs " + scholarship.effectiveDue());
        }
    }
}
