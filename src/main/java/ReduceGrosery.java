import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class InflationProblem {

    public static long calculateTotalPriceInFuture(int numberOfYears, List<Grocery> groceries) {
        return groceries.stream()
                .map(aggregate -> {
                    if (aggregate.getCategory().equals(Category.VEGETABLES)) {
                        return aggregate.getCost() * (long) Math.pow(3, numberOfYears);
                    } else if (aggregate.getCategory().equals(Category.FRUITS)) {
                        return aggregate.getCost() * (long) Math.pow(4, numberOfYears);
                    } else {
                        return aggregate.getCost() * (long) Math.pow(2, numberOfYears);
                    }
                })
                .reduce(0L, Long::sum); // write your code here
    }

    public static void main(String[] args) {
//1
//15 VEGETABLES
//30 VEGETABLES
//10 FRUITS
//20 DAIRY
        Scanner scanner = new Scanner(System.in);

        int numberOfYears = Integer.parseInt(scanner.nextLine());

        List<Grocery> groceries = Stream.iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .limit(4)
                .map(i -> scanner.nextLine())
                .map(inputLine -> {
                    String[] parts = inputLine.split("\\s+");
                    return new Grocery(Long.parseLong(parts[0]), Category.valueOf(parts[1]));
                })
                .collect(Collectors.toList());

        long totalPriceInFuture = calculateTotalPriceInFuture(numberOfYears, groceries);

        System.out.println(totalPriceInFuture);
    }
}

enum Category {
    VEGETABLES,
    FRUITS,
    DAIRY
}

class Grocery {
    private final long cost;
    private final Category category;

    // Imagine that this class has some other fields but they are skipped for simplicity

    public Grocery(long cost, Category category) {
        this.cost = cost;
        this.category = category;
    }

    public long getCost() {
        return cost;
    }

    public Category getCategory() {
        return category;
    }
}
