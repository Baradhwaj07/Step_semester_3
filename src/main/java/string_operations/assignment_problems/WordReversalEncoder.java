package assignment_problems;

public class WordReversalEncoder {
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            StringBuilder reversedWord = new StringBuilder();
            for (int characterIndex = words[wordIndex].length() - 1; characterIndex >= 0; characterIndex--) {
                reversedWord.append(words[wordIndex].charAt(characterIndex));
            }
            result.append(reversedWord);
            if (wordIndex < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String sentence = "hello club";
        System.out.println(reverseEachWord(sentence));
    }
}
