import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.LongStream;

class SumOfNumbers {

    public static long sum(long[] numbers) {
        return LongStream.of(numbers).sum(); // write your code here
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

        System.out.println(sum(numbers));
    }
}