package lt.auskim.utils;

import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessor;

import java.util.Collections;
import java.util.List;

public class ProcessorManager {
    private TextProcessorsInput textProcessors;

    public ProcessorManager(TextProcessorsInput textProcessors) {
        this.textProcessors = textProcessors;
    }

    public List<String> process(Class<? extends TextProcessor.Processor> processorClass) {
        try {
            TextProcessor.Processor processor = processorClass.getDeclaredConstructor(TextProcessors.class).newInstance(textProcessors);
            return processor.process();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
