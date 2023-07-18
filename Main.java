import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessorMapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sort Processor: " + TextProcessorMapper.getMethodName(TextProcessors.SortProcessor.class));
        System.out.println("Sort Processor: " + TextProcessorMapper.getProcessorName(TextProcessors.SortProcessor.class));
        System.out.println("Sort Processor: " + TextProcessorMapper.getMethodAndProcessorName(TextProcessors.SortProcessor.class));

        List<String> words = new TextProcessors("input.txt").new SortProcessor().process();
        new TextProcessors("input.txt");
        for (String word : words) {
            System.out.println(word);
        }
    }
}