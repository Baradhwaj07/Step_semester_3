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

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class employeePay {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee(101, "Plain", 40000),
            new ManagerEmployee(102, "Manager", 70000, 8000),
            new InternEmployee(103, "Intern", 12000, 10000)
        };

        for (Employee employee : employees) {
            if (employee instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs "
                        + ((ManagerEmployee) employee).effectiveSalary());
            } else if (employee instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs "
                        + ((InternEmployee) employee).effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + employee.getSalary());
            }
        }
    }
}