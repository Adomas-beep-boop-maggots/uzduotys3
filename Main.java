import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessorMapper;
import lt.auskim.utils.TextProcessorsInput;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        TextProcessors textProcessors = new TextProcessors();

        TextProcessorsInput input = new TextProcessorsInput(textProcessors, "input.txt");

        input.process(TextProcessors.GroupedProcessor.class);
        input.printWords();

        System.out.println("Processor: " + TextProcessorMapper.getMethodName(TextProcessors.SortProcessor.class));
        System.out.println("Processor: " + TextProcessorMapper.getMethodName(TextProcessors.GroupedProcessor.class));
        System.out.println("Processor: " + TextProcessorMapper.getMethodName(TextProcessors.UniqueProcessor.class));
    }
}