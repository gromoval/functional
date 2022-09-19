import java.util.function.Function;
import java.util.function.Predicate;

class ExtensionService {
    public static Function<String, String> addExtension(Predicate<String> isXml, Predicate<String> isJson) {
        // write your code here
        return file -> file + (isXml.test(file) ? ".xml" : isJson.test(file) ? ".json" : "");
    }

    public static void main(String[] args) {
        Function<String, String> func = addExtension(y -> y.equals("First"), y -> y.equals("Second"));
        System.out.println(func.apply("First")); //should print First.xml
        System.out.println(func.apply("Second")); //should print Second.json
        System.out.println(func.apply("Otherwise")); //should print Otherwise
    }
}

