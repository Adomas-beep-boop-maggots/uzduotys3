package lt.auskim.utils;
import java.util.List;

public abstract class TextProcessor {
    // Define the process method with a base implementation
    public abstract List<String> process(List<String> words);

    // Annotation to specify the method name
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface MethodName {
        String value();
    }

    // Automatically register processors using reflection
    public TextProcessor() {
        try {
            TextProcessorMapper.registerProcessor(getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}