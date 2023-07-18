package lt.auskim.utils;

import lt.auskim.TextProcessors;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class TextProcessorsInput {
    private TextProcessors textProcessors;
    private List<String> words;
    private List<String> processedWords;

    private TextProcessor.Processor processor;

    private String outputDirectory;


    public TextProcessorsInput(TextProcessors textProcessors, String filePath) {
        String outputDirectory = "output/";
        this.textProcessors = textProcessors;
        words = textProcessors.words;
        readFromFile(filePath);
    }

    public void readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Trim leading and trailing whitespace
                if (!line.isEmpty()) { // Skip empty lines
                    String[] wordsInLine = line.split("\\W+");
                    for (String word : wordsInLine) {
                        if (!word.isEmpty()) {
                            words.add(word.toLowerCase());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> process(Class<? extends TextProcessor.Processor> processorClass) {
        try {
            TextProcessor.Processor processor = processorClass.getDeclaredConstructor(textProcessors.getClass()).newInstance(textProcessors);
            this.processor = processor;
            this.processedWords = processor.process();
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

    public void deleteOutputFiles() {
        List<String> methodNames = TextProcessorMapper.getAllMethodNames();
        String outputDirectory = "output/";

        for (String methodName : methodNames) {
            String outputFile = outputDirectory + "output." + methodName;

            try {
                File file = new File(outputFile);
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("Deleted: " + outputFile);
                    } else {
                        System.out.println("Failed to delete: " + outputFile);
                    }
                } else {
                    System.out.println("File not found: " + outputFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeProcessedWordsToFile() {
        if (processedWords.isEmpty()) {
            System.out.println("No processed words to write. Please run process() first.");
            return;
        }

        String processorName = TextProcessorMapper.getMethodName(processor.getClass());
//        String outputDirectory = "output/";
        String outputFile = outputDirectory + "output." + processorName;

        try {
            File directory = new File(outputDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
                for (String word : processedWords) {
                    bw.write(word);
                    bw.newLine();
                }
                System.out.println("Processed words have been written to: " + outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
