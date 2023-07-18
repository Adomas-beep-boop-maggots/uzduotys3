import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessorMapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TextProcessors input = new TextProcessors("input.txt");
        List<String> words = input.process(TextProcessors.GroupedProcessor.class);
        for (String word : words) {
            System.out.println(word);
        }
        System.out.println("Sort Processor: " + TextProcessorMapper.getMethodAndProcessorName(TextProcessors.SortProcessor.class));
        System.out.println("Sort Processor: " + TextProcessorMapper.getMethodAndProcessorName(TextProcessors.UniqueProcessor.class));
        System.out.println("Sort Processor: " + TextProcessorMapper.getMethodAndProcessorName(TextProcessors.GroupedProcessor.class));
    }
}