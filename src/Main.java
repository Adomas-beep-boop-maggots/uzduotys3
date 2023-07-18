import lt.auskim.TextProcessors;
import lt.auskim.utils.TextProcessorsInput;

public class Main {
    public static void main(String[] args) {

        TextProcessors textProcessors = new TextProcessors();

        TextProcessorsInput input = new TextProcessorsInput(textProcessors, "input.txt");
        input.deleteAllOutputFiles();
        input.processAll();
    }
}