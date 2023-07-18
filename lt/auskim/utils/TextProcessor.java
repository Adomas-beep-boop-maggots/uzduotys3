package lt.auskim.utils;

import java.util.List;

public class TextProcessor {
    // Define the TextProcessor interface
    public interface Processor {
        List<String> process(List<String> inputList);
    }

    // Annotation to specify the method name
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface MethodName {
        String value();
    }
}
