## Clone & Compile this library
```
git clone https://github.com/Adomas-beep-boop-maggots/uzduotys3.git &&
cd uzduotys3 &&
javac ./src/lt/auskim/*.java ./src/lt/auskim/utils/*.java -d ./build/ &&
jar cf textProcessor.jar -C ./build lt
```
## Usage

### Read Text from File
```java
import lt.auskim.utils.TextProcessorsInput;

public class Main {
    public static void main(String[] args) {
        TextProcessorsInput input = new TextProcessorsInput(new lt.auskim.TextProcessors(), "input.txt");
        // You can now use the input object to perform various operations.
    }
}
```
### Run Built-in Proccessors
Sort and Write Words in Alphabetical Order
```java
input.process(TextProcessors.SortProcessor.class);
```
Unique and Write Only Unique Words
```java
input.process(TextProcessors.UniqueProcessor.class);
```
Group and Write Unique Words with Occurrences
```java
input.process(TextProcessors.GroupedProcessor.class);
```
### Delete Processed Files
```java
input.deleteAllOutputFiles();
```


## Custom Processors
You can create custom processors by extending the TextProcessor class and registering them using the MethodName annotation. Here's an example of creating a custom processor:
```java
import lt.auskim.utils.TextProcessor;

public class MyTextProcessors extends lt.auskim.TextProcessors {
    @TextProcessor.MethodName("upperCase")
    public static class UppercaseProcessor extends TextProcessor {
        @Override
        public List<String> process(List<String> words) {
            List<String> result = new ArrayList<>();
            for (String s : words) {
                result.add(s.toUpperCase());
            }
            return result;
        }
    }

    // Add other custom processor classes as needed
}
```
You can then use the custom processors in the same way as the built-in ones:
```java
TextProcessors myTextProcessors = new MyTextProcessors();
TextProcessorsInput myInput = new TextProcessorsInput(myTextProcessors, "input.txt");
myInput.processAll();
// or
myInput.process(MyTextProcessors.UppercaseProcessor.class);
```
