package lt.auskim;

import lt.auskim.utils.TextProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextProcessors {
    private static List<String> words;

    public TextProcessors(String filePath) {
        words = new ArrayList<>();
        readFromFile(filePath);
    }

    public List<String> process(Class<? extends TextProcessor.Processor> processorClass) {
        try {
            TextProcessor.Processor processor = processorClass.getDeclaredConstructor(this.getClass()).newInstance(this);
            return processor.process();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] wordsInLine = line.split("\\W+");
                for (String word : wordsInLine) {
                    words.add(word.toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
