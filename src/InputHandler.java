import java.util.Scanner;

public class InputHandler {
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    public static float getFloatInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextFloat();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}