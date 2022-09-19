import java.util.Scanner;

class Isogram {

    public static boolean isIsogram(String word) {
        return word.length() == word.chars().distinct().count(); // write your code here
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        System.out.println(isIsogram(word));
    }
}