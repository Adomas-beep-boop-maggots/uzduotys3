package lt.auskim.utils;

import lt.auskim.TextProcessors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TextProcessorsInput {
    private TextProcessors textProcessors;
    private List<String> words;
    private List<String> processedWords;


    public TextProcessorsInput(TextProcessors textProcessors, String filePath) {
        this.textProcessors = textProcessors;
        words = textProcessors.words;
        readFromFile(filePath);
    }

    public void readFromFile(String filePath) {
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

    public List<String> process(Class<? extends TextProcessor.Processor> processorClass) {
        try {
            TextProcessor.Processor processor = processorClass.getDeclaredConstructor(textProcessors.getClass()).newInstance(textProcessors);
            processedWords = processor.process();
            return processor.process();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void printWords() {
        if (processedWords.isEmpty()) {
            System.out.println("No processed words to print. Please run process() first.");
        } else {
            for (String word : processedWords) {
                System.out.println(word);
            }
        }
    }
}
