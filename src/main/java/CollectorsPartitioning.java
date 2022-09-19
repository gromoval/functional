import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Partitioner {

    public static Map<Boolean, List<Application>> getPartition(List<Application> applications) {
        return applications.stream()
                .collect(Collectors.partitioningBy(Application::isFree));// write your code here
    }
}

class Application {
    private final String name;
    private final boolean isFree;

    public Application(String name, boolean isFree) {
        this.name = name;
        this.isFree = isFree;
    }

    public String getName() {
        return name;
    }

    public boolean isFree() {
        return isFree;
    }
}

class SomeRunner {
    public static void main(String[] args) {
//app1 true
//app2 true
//app3 false
//app4 true
//app5 false
        Scanner sc = new Scanner(System.in);
        List<Application> applications = Stream.iterate(1, i -> sc.hasNextLine(), i -> i + 1)
                .map(i -> sc.nextLine().split("\\s+"))
                .map(s -> new Application(s[0], Boolean.valueOf(s[1])))
                .collect(Collectors.toList());
        Partitioner.getPartition(applications).forEach((k, v) -> System.out.println(k + " = " + v));
    }
}