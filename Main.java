import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessorMapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Processor: " + TextProcessorMapper.getMethodName(TextProcessors.SortProcessor.class));
        System.out.println("Processor: " + TextProcessorMapper.getMethodName(TextProcessors.UniqueProcessor.class));
        System.out.println("Processor: " + TextProcessorMapper.getMethodName(TextProcessors.GroupedProcessor.class));
    }
}