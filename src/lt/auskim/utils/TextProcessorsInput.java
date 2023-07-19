package lt.auskim.utils;

import lt.auskim.TextProcessors;

import java.io.*;
import java.util.*;

public class TextProcessorsInput<T extends TextProcessors> {
    private T textProcessors;
    private List<String> words;
    private List<String> processedWords;
    //    private boolean processingRequired; // New flag to determine if processing is needed
//    private Map<Class<? extends TextProcessor.Processor>, Boolean> processingRequiredList;

    private TextProcessor processor;

    private String outputDirectory;

    public TextProcessorsInput(T textProcessors, String filePath) {
        outputDirectory = "output/";
        this.textProcessors = textProcessors;
        words = textProcessors.processWords;
        processedWords = new ArrayList<>();
//        processingRequiredList = new HashMap<>();
        readFromFile(filePath);
    }

    public List<String> getWords() {
//      We pass referance to words, not data itself, i hope...?
        return words;
    }


    private void readFromFile(String filePath) {
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


//    public List<String> process(Class<? extends TextProcessor.Processor> processorClass) {
//        try {
////            if (processingRequiredList.get(processorClass)) {
//                TextProcessor.Processor processor = processorClass.getDeclaredConstructor(textProcessors.getClass()).newInstance(textProcessors);
////                TextProcessorMapper.registerProcessor(processor);
//                processedWords = processor.process(words);
//                this.processor = processor;
////                processingRequiredList.put(processorClass, false);
//                writeProcessedWordsToFile(processorClass);
////            }
//            return processedWords;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }

    public List<String> process(Class<? extends TextProcessor> processorClass) {
        try {
            TextProcessor processor = processorClass.getDeclaredConstructor().newInstance();
            processedWords = processor.process(words);
            this.processor = processor;
            writeProcessedWordsToFile(processorClass);
            return processedWords;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public void processAll() {
        List<Class<? extends TextProcessor>> processorClasses = TextProcessorMapper.getAllProcessorClasses(textProcessors);
//        System.out.println(processorClasses);
        for (Class<? extends TextProcessor> processorClass : processorClasses) {
            try {
                System.out.println("from process All" + processorClass);
//                processingRequired = true;
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

//    public void deleteAllOutputFiles() {
//        List<String> methodNames = TextProcessorMapper.getAllMethodNames();
//        String outputDirectory = "output/";
//
//        for (String methodName : methodNames) {
//            String outputFile = outputDirectory + "output." + methodName;
//
//            try {
//                File file = new File(outputFile);
//                if (file.exists()) {
//                    if (file.delete()) {
//                        System.out.println("Deleted: " + outputFile);
//                    } else {
//                        System.out.println("Failed to delete: " + outputFile);
//                    }
//                } else {
//                    System.out.println("File not found: " + outputFile);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void writeProcessedWordsToFile(Class<? extends TextProcessor> processor) {
        if (processedWords.isEmpty()) {
            System.out.println("No processed words to write. Please run process() first.");
            return;
        }

        String processorName = TextProcessorMapper.getMethodName(processor);
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
