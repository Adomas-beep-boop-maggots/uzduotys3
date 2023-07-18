package lt.auskim.utils;

import java.util.ArrayList;
import java.util.List;

public class TextProcessor {
    // Define the TextProcessor interface
    public interface Processor {
        List<String> process();
    }

    // Annotation to specify the method name
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface MethodName {
        String value();
    }

    // Automatically register processors using reflection
    static {
        registerProcessors();
    }

    private static void registerProcessors() {
        Class<?> enclosingClass = Processor.class.getEnclosingClass();
        if (enclosingClass != null) {
            for (Class<?> processorClass : enclosingClass.getDeclaredClasses()) {
                if (Processor.class.isAssignableFrom(processorClass)) {
                    TextProcessorMapper.registerProcessor((Class<? extends Processor>) processorClass);
                }
            }
        }
    }
}
