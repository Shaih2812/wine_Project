import java.util.Scanner;

public class ChloridesFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public ChloridesFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        String operator = "";
        float chloridesValue = -1;

        // Prompt user for the condition until valid input is entered
        while (true) {
            System.out.print("Enter condition for chlorides (e.g., < 0.5, >= 0.2): ");
            String input = scanner.nextLine().trim();

            // Ensure the input is not empty
            if (input.isEmpty()) {
                System.out.println("Condition cannot be empty. Please try again.");
                continue; // Re-prompt the user
            }

            // Check if the input matches the expected format (operator followed by a value)
            if (input.matches("[<>]=?\\s*\\d+(\\.\\d+)?")) {
                // Extract the operator and value
                String[] parts = input.split("\\s+");
                operator = parts[0].trim();
                chloridesValue = Float.parseFloat(parts[1].trim());

                // Check if the value is within the valid range
                if (chloridesValue >= 0 && chloridesValue <= 1) {
                    break;  // Exit the loop if the input is valid
                } else {
                    System.out.println("Chlorides value must be between 0 and 1. Please try again.");
                }
            } else {
                System.out.println("Invalid format. Please enter the condition in the form of '< 0.5', '>= 0.2', etc.");
            }
        }

        // Add the valid condition to the query builder
        queryBuilder.addCondition("`chlorides` " + operator + " " + chloridesValue);
        System.out.println("Chlorides condition added to query builder: `chlorides` " + operator + " " + chloridesValue);
    }
}
