package lt.auskim.utils;

import lt.auskim.TextProcessors;

import java.io.*;
import java.util.*;

import static lt.auskim.utils.TextProcessorMapper.getAllProcessorClasses;

public class TextProcessorsInput<T extends TextProcessors> {
    private T textProcessors;
    private List<String> words;
    private List<String> processedWords;
    private Map<Class<? extends TextProcessor>, Boolean> processingRequiredList;

    private TextProcessor processor;

    private String outputDirectory;

    public TextProcessorsInput(T textProcessors, String filePath) {
        outputDirectory = "output/";
        this.textProcessors = textProcessors;
        words = textProcessors.processWords;
        processedWords = new ArrayList<>();
        processingRequiredList = new HashMap<>();
        List<Class<? extends TextProcessor>> processorClasses = getAllProcessorClasses(textProcessors);
        // Set all the processor classes to true
        for (Class<? extends TextProcessor> processorClass : processorClasses) {
            processingRequiredList.put(processorClass, true);
        }
        readFromFile(filePath);
    }


    private void readFromFile(String filePath) {
        List<Class<? extends TextProcessor>> processorClasses = getAllProcessorClasses(textProcessors);
        for (Class<? extends TextProcessor> processorClass : processorClasses) {
            processingRequiredList.put(processorClass, true);
        }
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


    public void addNewText(String newText) {
        newText = newText.trim(); // Trim leading and trailing whitespace
        if (!newText.isEmpty()) { // Skip empty lines
            String[] wordsInLine = newText.split("\\W+");
            for (String word : wordsInLine) {
                if (!word.isEmpty()) {
                    words.add(word.toLowerCase());
                }
            }
        }
    }

    public List<String> process(Class<? extends TextProcessor> processorClass) {
        try {
            if (processingRequiredList.get(processorClass)) {
                TextProcessor processor = processorClass.getDeclaredConstructor().newInstance();
                processedWords = processor.process(words);
                this.processor = processor;
                processingRequiredList.put(processorClass, false);
                writeProcessedWordsToFile(processorClass);
            }
            return processedWords;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public void processAll() {
        List<Class<? extends TextProcessor>> processorClasses = getAllProcessorClasses(textProcessors);
        for (Class<? extends TextProcessor> processorClass : processorClasses) {
            try {
                this.process(processorClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public void deleteAllOutputFiles() {
        List<Class<? extends TextProcessor>> processorClasses = getAllProcessorClasses(textProcessors);
        for (Class<? extends TextProcessor> processorClass : processorClasses) {
            processingRequiredList.put(processorClass, true);
        }
        List<String> methodNames = TextProcessorMapper.getAllMethodNames(textProcessors);
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
                }
//                else {
//                    System.out.println("File not found: " + outputFile);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeProcessedWordsToFile(Class<? extends TextProcessor> processor) {
        if (processedWords.isEmpty()) {
            System.out.println("No processed words to write. Please run process() first.");
            return;
        }

        String processorName = TextProcessorMapper.getMethodName(processor);
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
