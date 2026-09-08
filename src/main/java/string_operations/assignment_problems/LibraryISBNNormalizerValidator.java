package assignment_problems;

public class LibraryISBNNormalizerValidator {
    static String normalizeCode(String raw) {
        String code = raw.trim();
        if (code.length() < 3) {
            return code.toUpperCase();
        }

        String publisherCode = code.substring(0, 3).toUpperCase();
        String body = code.substring(3);
        return publisherCode + body;
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int index = 0; index < 3; index++) {
            if (!Character.isLetter(code.charAt(index))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int index = 3; index < code.length(); index++) {
            if (!Character.isDigit(code.charAt(index))) {
                return "Invalid: body must contain only digits";
            }
        }

        StringBuilder formattedCode = new StringBuilder();
        formattedCode.append('[');
        formattedCode.append(code.substring(0, 3));
        formattedCode.append("] YEAR: ");
        formattedCode.append(code.substring(3, 7));
        formattedCode.append(" | CATALOG: ");
        formattedCode.append(code.substring(7));
        return formattedCode.toString();
    }

    public static void main(String[] args) {
        String rawCode1 = " pen2026004251 ";
        String rawCode2 = "12N2026004251";
        String code1 = normalizeCode(rawCode1);
        String code2 = normalizeCode(rawCode2);
        System.out.println(validateAndFormat(code1));
        System.out.println(validateAndFormat(code2));
    }
}
