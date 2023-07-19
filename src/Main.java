import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessorMapper;
import lt.auskim.utils.TextProcessorsInput;
import lt.auskim.utils.TextProcessor;

import java.util.ArrayList;
import java.util.List;

class MyTextProcessors extends TextProcessors {
    @TextProcessor.MethodName("upperCase")
    public static class UppercaseProcessor extends TextProcessor {
        @Override
        public List<String> process(List<String> words) {
            List<String> result = new ArrayList<>();
            for (String s : words) {
                result.add(s.toUpperCase());
            }
            return result;
        }
    }

    // Add other custom processor classes as needed
}


public class Main {
    public static void main(String[] args) {
        TextProcessors textProcessors = new TextProcessors();
        TextProcessorsInput input = new TextProcessorsInput(textProcessors, "input.txt");
//
//        input.processAll();
//        input.deleteAllOutputFiles();
//      Process all
        input.processAll();
        input.processAll();

//        input.processAll();

//      Process one
//        input.process(TextProcessors.SortProcessor.class);


//        input.process(TextProcessors.GroupedProcessor.class);

//      Custom processor
//        TextProcessors myTextProcessors = new MyTextProcessors();
//        TextProcessorsInput myInput = new TextProcessorsInput(myTextProcessors, "input.txt");
//        myInput.processAll();
//        myInput.process(MyTextProcessors.UppercaseProcessor.class);


    }

}