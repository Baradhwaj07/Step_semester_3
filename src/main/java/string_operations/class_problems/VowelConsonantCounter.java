public class VowelConsonantCounter {
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;

        for (int index = 0; index < text.length(); index++) {
            char character = text.charAt(index);
            if (character == 'a' || character == 'e' || character == 'i'
                || character == 'o' || character == 'u'
                || character == 'A' || character == 'E' || character == 'I'
                || character == 'O' || character == 'U') {
                vowels++;
            } else if (character != ' ') {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        String text1 = "Java Programming";
        String text2 = "Hello World";
        countVowelsAndConsonants(text1);
        countVowelsAndConsonants(text2);
    }
}
