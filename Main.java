import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessorMapper;

public class Main {
    public static void main(String[] args) {
//        TextProcessorMapper mapper = new TextProcessorMapper();
        TextProcessorMapper.registerDefaultProcessors();
        System.out.println("Sort Processor: " + TextProcessorMapper.getMethodName(TextProcessors.UppercaseProcessor.class));
        System.out.println("Sort Processor: " + TextProcessorMapper.getProcessorName(TextProcessors.UppercaseProcessor.class));
        System.out.println("Sort Processor: " + TextProcessorMapper.getMethodAndProcessorName(TextProcessors.UppercaseProcessor.class));
    }
}