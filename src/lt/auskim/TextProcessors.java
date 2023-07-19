package lt.auskim;

import lt.auskim.utils.TextProcessor;
import java.util.*;

public class TextProcessors {
    public static List<String> processWords;

    public TextProcessors() {
        processWords = new ArrayList<>();
    }

//    @TextProcessor.MethodName("sort")
//    public class SortProcessor implements TextProcessor.Processor {
//        @Override
//        public List<String> process(List<String> words) {
//            List<String> sortedWords = new ArrayList<>(processWords);
//            Collections.sort(sortedWords);
//            return sortedWords;
//        }
//    }

    @TextProcessor.MethodName("sort")
    public static class SortProcessor extends TextProcessor {
        @Override
        public List<String> process(List<String> words) {
            List<String> result = new ArrayList<>();
            for (String s : words) {
                result.add(s.toUpperCase());
            }
            return result;
        }

    }

    @TextProcessor.MethodName("unique")
    public static class UniqueProcessor extends TextProcessor {
        @Override
        public List<String> process(List<String> words) {
            Set<String> uniqueWords = new HashSet<>(processWords);
            return new ArrayList<>(uniqueWords);
        }
    }
    @TextProcessor.MethodName("grouped")
    public static class GroupedProcessor extends TextProcessor {
        @Override
        public List<String> process(List<String> words) {
            Map<String, Integer> wordOccurrences = new HashMap<>();
            for (String word : processWords) {
                wordOccurrences.put(word, wordOccurrences.getOrDefault(word, 0) + 1);
            }

            List<String> groupedWords = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()) {
                groupedWords.add(entry.getKey() + ": " + entry.getValue());
            }
            return groupedWords;
        }
    }
}
