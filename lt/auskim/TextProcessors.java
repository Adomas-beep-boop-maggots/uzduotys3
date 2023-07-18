package lt.auskim;

import lt.auskim.utils.TextProcessor;
import lt.auskim.utils.TextProcessorsInput;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextProcessors {
    public List<String> words;

    public TextProcessors() {
        words = new ArrayList<>();
    }

    @TextProcessor.MethodName(".sort")
    public class SortProcessor implements TextProcessor.Processor {
        @Override
        public List<String> process() {
            List<String> sortedWords = new ArrayList<>(words);
            Collections.sort(sortedWords);
            return sortedWords;
        }
    }

    @TextProcessor.MethodName(".unique")
    public class UniqueProcessor implements TextProcessor.Processor {
        @Override
        public List<String> process() {
            Set<String> uniqueWords = new HashSet<>(words);
            return new ArrayList<>(uniqueWords);
        }
    }
    @TextProcessor.MethodName(".grouped")
    public class GroupedProcessor implements TextProcessor.Processor {
        @Override
        public List<String> process() {
            Map<String, Integer> wordOccurrences = new HashMap<>();
            for (String word : words) {
                wordOccurrences.put(word, wordOccurrences.getOrDefault(word, 0) + 1);
            }

            List<String> groupedWords = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()) {
                groupedWords.add(entry.getKey() + ": " + entry.getValue());
            }
            return groupedWords;
        }
    }}
