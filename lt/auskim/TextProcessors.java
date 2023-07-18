package lt.auskim;

import lt.auskim.utils.TextProcessor;

import java.util.*;

public class TextProcessors {


    @TextProcessor.MethodName(".upperCase")
    public static class UppercaseProcessor implements TextProcessor.Processor {
        @Override
        public List<String> process(List<String> inputList) {
            List<String> result = new ArrayList<>();
            for (String s : inputList) {
                result.add(s.toUpperCase());
            }
            return result;
        }
    }

//    @MethodName(".sort")
//    public static class SortProcessor implements TextProcessor {
//        @Override
//        public List<String> process(List<String> inputList) {
//            List<String> result = new ArrayList<>(inputList);
//            Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
//            return result;
//        }
//    }
//
//    // Implement the reverse text processor
//    @MethodName(".reverse")
//    public static class ReverseProcessor implements TextProcessor {
//        @Override
//        public List<String> process(List<String> inputList) {
//            List<String> result = new ArrayList<>();
//            for (String s : inputList) {
//                result.add(new StringBuilder(s).reverse().toString());
//            }
//            return result;
//        }
//    }
}
