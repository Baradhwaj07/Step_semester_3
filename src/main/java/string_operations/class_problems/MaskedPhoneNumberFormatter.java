public class MaskedPhoneNumberFormatter {
    public static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int index = 0; index < phone.length(); index++) {
            if (!Character.isDigit(phone.charAt(index))) {
                return "Invalid phone number";
            }
        }

        StringBuilder maskedNumber = new StringBuilder("XXXXXX");
        maskedNumber.insert(6, "-");
        maskedNumber.append(phone.substring(6));
        return maskedNumber.toString();
    }

    public static void main(String[] args) {
        String phone1 = "9876543210";
        String phone2 = "98765";
        System.out.println(maskPhoneNumber(phone1));
        System.out.println(maskPhoneNumber(phone2));
    }
}
