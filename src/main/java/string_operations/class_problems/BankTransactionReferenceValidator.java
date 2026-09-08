public class BankTransactionReferenceValidator {
    public static String normalizeReference(String raw) {
        String reference = raw.trim();
        if (reference.length() < 3) {
            return reference.toUpperCase();
        }

        String bankCode = reference.substring(0, 3).toUpperCase();
        String body = reference.substring(3);
        return bankCode + body;
    }

    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        for (int index = 0; index < 3; index++) {
            if (!Character.isLetter(reference.charAt(index))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int index = 3; index < reference.length(); index++) {
            if (!Character.isDigit(reference.charAt(index))) {
                return "Invalid: body must contain only digits";
            }
        }

        StringBuilder formattedReference = new StringBuilder();
        formattedReference.append('[');
        formattedReference.append(reference.substring(0, 3));
        formattedReference.append("] DATE: ");
        formattedReference.append(reference.substring(3, 5));
        formattedReference.append('/');
        formattedReference.append(reference.substring(5, 7));
        formattedReference.append('/');
        formattedReference.append(reference.substring(7, 9));
        formattedReference.append(" | SEQ: ");
        formattedReference.append(reference.substring(9));
        return formattedReference.toString();
    }

    public static void main(String[] args) {
        String rawReference1 = " hdf03022600042 ";
        String rawReference2 = "12F03022600042";
        String reference1 = normalizeReference(rawReference1);
        String reference2 = normalizeReference(rawReference2);
        System.out.println(validateAndFormat(reference1));
        System.out.println(validateAndFormat(reference2));
    }
}
