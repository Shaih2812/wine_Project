import java.util.Scanner;

public class ResidualSugarFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public ResidualSugarFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        String condition = "";

        // Keep prompting until a valid condition is entered
        while (true) {
            System.out.print("Enter residual sugar condition (e.g., < 5.0, >= 3.0): ");
            condition = scanner.nextLine().trim();  // Capture the whole input

            // Ensure the user didn't enter an empty string
            if (condition.isEmpty()) {
                System.out.println("Condition cannot be empty. Please try again.");
                continue; // Re-prompt the user
            }

            // Validate the condition format using a regex pattern
            if (!condition.matches("[<>]=?\\s*\\d+(\\.\\d+)?")) {
                System.out.println("Invalid Residual Sugar condition. Please enter a valid format (e.g., '< 5.0', '>= 3.0').");
                continue; // Re-prompt the user for a valid input
            }

            // If we reach here, the condition is valid
            System.out.println("Residual Sugar condition accepted: " + condition);

            // Add the validated condition to the query builder, wrap `residual sugar` in backticks
            queryBuilder.addCondition("`residual sugar` " + condition);
            System.out.println("Condition added to query builder.");
            break; // Exit the loop after a valid input
        }
    }
}
