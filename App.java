import java.util.*;

public class App {
    public static void main(String[] args) {
        
        // List of letters from A to U
        // Placeholder for the nodes, we can make this not a list if we want to
        List<String> letters = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U"
        );

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the goal state (A-U): ");
        String input = scanner.nextLine().toUpperCase();

        if (letters.contains(input)) {
            System.out.println("The goal state is: " + input);
        } else {
            System.out.println("Invalid input. Please enter a letter from A to U.");
        }
    }
}
