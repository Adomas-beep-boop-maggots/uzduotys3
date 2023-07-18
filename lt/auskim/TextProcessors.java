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

//    public List<String> getWords() {
//        return words;
//    }

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

    @TextProcessor.MethodName(".upperCase")
    public class UppercaseProcessor implements TextProcessor.Processor {
        @Override
        public List<String> process() {
            List<String> result = new ArrayList<>();
            for (String s : words) {
                result.add(s.toUpperCase());
            }
            return result;
        }
    }




//    @MethodName(".sort")
//    public static class SortProcessor implements TextProcessor {
//        @Override
//        public List<String> process() {
//            List<String> result = new ArrayList<>(inputList);
//            Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
//            return result;
//        }
//    }
//
//    // Implement the reverse text processor
//    @MethodName(".reverse")
//    public static class ReverseProcessor implements TextProcessor {
//        @Override
//        public List<String> process() {
//            List<String> result = new ArrayList<>();
//            for (String s : inputList) {
//                result.add(new StringBuilder(s).reverse().toString());
//            }
//            return result;
//        }
//    }
}
