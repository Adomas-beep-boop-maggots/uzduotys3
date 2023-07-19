package lt.auskim.utils;

import lt.auskim.TextProcessors;

import java.util.*;

public class TextProcessorMapper {
    private static Map<Class<? extends TextProcessor>, String> METHOD_MAP = new HashMap<>();

    // Add this method to register method names for processors
    public static void registerProcessor(Class<? extends TextProcessor> processorClass) {
        TextProcessor.MethodName methodName = processorClass.getAnnotation(TextProcessor.MethodName.class);
        if (methodName != null) {
            METHOD_MAP.put(processorClass, methodName.value());
        } else {
            METHOD_MAP.put(processorClass, processorClass.getSimpleName());
        }
    }

    // Modify getMethodName to use the registered method names
    public static String getMethodName(Class<? extends TextProcessor> processorClass) {
        return METHOD_MAP.getOrDefault(processorClass, processorClass.getSimpleName());
    }

    public static List<Class<? extends TextProcessor>> getAllProcessorClasses(TextProcessors instance) {
        List<Class<? extends TextProcessor>> processors = new ArrayList<>();
        Class<? extends TextProcessors> enclosingClass = instance.getClass();

        for (Class<?> innerClass : enclosingClass.getDeclaredClasses()) {
            if (TextProcessor.class.isAssignableFrom(innerClass)) {
                try {
//                    Class<? extends TextProcessor> processorInstance = innerClass.getDeclaredConstructor().newInstance();
                    Class<? extends TextProcessor> processorClass = innerClass.asSubclass(TextProcessor.class);
                    processors.add(processorClass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return processors;
    }

    public static List<String> getAllMethodNames(TextProcessors instance) {
        List<String> processorNames = new ArrayList<String>();
        for (Class<?> innerClass : instance.getClass().getDeclaredClasses()) {
            processorNames.add(getMethodName((Class<? extends TextProcessor>) innerClass));
        }
        return processorNames;
    }
}
