package lt.auskim.utils;

import lt.auskim.TextProcessors;

import java.util.*;

public class TextProcessorMapper {
    private static Map<Class<? extends TextProcessor.Processor>, String> METHOD_MAP = new HashMap<>();

    public static String getMethodName(Class<? extends TextProcessor.Processor> processorClass) {
        return processorClass.getSimpleName();
    }

    public static String getProcessorName(Class<? extends TextProcessor.Processor> processorClass) {
        return processorClass.getSimpleName();
    }

    public static String getMethodAndProcessorName(Class<? extends TextProcessor.Processor> processorClass) {
        String methodName = getMethodName(processorClass);
        String processorName = processorClass.getSimpleName();
        return "Method Name: " + methodName + ", Processor Name: " + processorName;
    }

    public static void registerProcessor(Class<? extends TextProcessor.Processor> processorClass) {
        TextProcessor.MethodName methodName = processorClass.getAnnotation(TextProcessor.MethodName.class);
        if (methodName != null) {
            METHOD_MAP.put(processorClass, methodName.value());
        }
    }

//    public static void registerDefaultProcessors() {
//        TextProcessorMapper.registerProcessor(TextProcessors.UppercaseProcessor.class);
//        TextProcessorMapper.registerProcessor(TextProcessors.SortProcessor.class);
////        TextProcessorMapper.registerProcessor(TextProcessors.ReverseProcessor.class);
//    }
}
