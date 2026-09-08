package assignment_problems;

public class ATMPinLengthValidator {
    static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        String pin1 = "482";
        String pin2 = "4820";
        checkPinLength(pin1);
        checkPinLength(pin2);
    }
}
