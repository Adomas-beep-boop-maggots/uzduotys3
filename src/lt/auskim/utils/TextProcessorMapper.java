package lt.auskim.utils;

import lt.auskim.TextProcessors;

import java.util.*;

public class TextProcessorMapper {
    private static Map<Class<? extends TextProcessor.Processor>, String> METHOD_MAP = new HashMap<>();

    // Add this method to register method names for processors
    public static void registerProcessor(Class<? extends TextProcessor.Processor> processorClass) {
        TextProcessor.MethodName methodName = processorClass.getAnnotation(TextProcessor.MethodName.class);
        if (methodName != null) {
            METHOD_MAP.put(processorClass, methodName.value());
        } else {
            METHOD_MAP.put(processorClass, processorClass.getSimpleName());
        }
    }

    // Modify getMethodName to use the registered method names
    public static String getMethodName(Class<? extends TextProcessor.Processor> processorClass) {
        return METHOD_MAP.getOrDefault(processorClass, processorClass.getSimpleName());
    }

    public static String getProcessorName(Class<? extends TextProcessor.Processor> processorClass) {
        return processorClass.getSimpleName();
    }

    public static String getMethodAndProcessorName(Class<? extends TextProcessor.Processor> processorClass) {
        String methodName = getMethodName(processorClass);
        String processorName = processorClass.getSimpleName();
        return "Method Name: " + methodName + ", Processor Name: " + processorName;
    }

    static {
        // Register method names for processors here
        registerProcessor(TextProcessors.SortProcessor.class);
        registerProcessor(TextProcessors.UniqueProcessor.class);
        registerProcessor(TextProcessors.GroupedProcessor.class);
    }
}
