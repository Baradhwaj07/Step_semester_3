package assignment_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StopWordFilteredWordFrequencyReport {
    static void printFilteredWordFrequency(String feedback) {
        String cleanedFeedback = feedback.toLowerCase();
        cleanedFeedback = cleanedFeedback.replace(".", "");
        cleanedFeedback = cleanedFeedback.replace(",", "");

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        HashSet<String> stopWordSet = new HashSet<String>();
        for (int index = 0; index < stopWords.length; index++) {
            stopWordSet.add(stopWords[index]);
        }

        String[] words = cleanedFeedback.split("\\s+");
        HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();

        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            if (!stopWordSet.contains(word)) {
                if (wordCounts.containsKey(word)) {
                    wordCounts.put(word, wordCounts.get(word) + 1);
                } else {
                    wordCounts.put(word, 1);
                }
            }
        }

        ArrayList<Map.Entry<String, Integer>> wordEntries = new ArrayList<Map.Entry<String, Integer>>(wordCounts.entrySet());
        Collections.sort(wordEntries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
                return second.getValue() - first.getValue();
            }
        });

        for (int index = 0; index < wordEntries.size(); index++) {
            System.out.println(wordEntries.get(index).getKey() + ": " + wordEntries.get(index).getValue());
        }
    }

    public static void main(String[] args) {
        String feedback = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(feedback);
    }
}
