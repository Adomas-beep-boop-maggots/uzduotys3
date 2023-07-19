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

//    public static String getProcessorName(Class<? extends TextProcessor.Processor> processorClass) {
//        return processorClass.getSimpleName();
//    }

//    public static Class<? extends TextProcessor.Processor> getProcessorClass(String methodName) {
//        for (Map.Entry<Class<? extends TextProcessor.Processor>, String> entry : METHOD_MAP.entrySet()) {
//            if (entry.getValue().equals(methodName)) {
//                return entry.getKey();
//            }
//        }
//        return null;
//    }

//    public static String getMethodAndProcessorName(Class<? extends TextProcessor.Processor> processorClass) {
//        String methodName = getMethodName(processorClass);
//        String processorName = processorClass.getSimpleName();
//        return "Method Name: " + methodName + ", Processor Name: " + processorName;
//    }

//    public static List<String> getAllMethodNames() {
//        List<String> processorNames = new ArrayList<>();
//        for (Class<? extends TextProcessor.Processor> processorClass : METHOD_MAP.keySet()) {
//            processorNames.add(getMethodName(processorClass));
//        }
//        return processorNames;
//    }

//    public static List<String> getAllProcessorNames() {
//        List<String> processorNames = new ArrayList<>();
//        for (Class<? extends TextProcessor.Processor> processorClass : METHOD_MAP.keySet()) {
//            processorNames.add(getProcessorName(processorClass));
//        }
//        return processorNames;
//    }

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
}
