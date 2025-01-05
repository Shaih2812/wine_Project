import java.util.Scanner;

public class DensityFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public DensityFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        String condition = "";

        // Keep prompting until a valid condition is entered
        while (true) {
            System.out.print("Enter density condition (e.g., < 0.99, >= 1.0): ");
            condition = scanner.nextLine().trim();  // Use nextLine to capture the whole input

            // Ensure the user didn't enter an empty string
            if (condition.isEmpty()) {
                System.out.println("Condition cannot be empty. Please try again.");
                continue; // Re-prompt the user
            }

            // Validate the condition format using a regex pattern
            if (!condition.matches("[<>]=?\\s*\\d+(\\.\\d+)?")) {
                System.out.println("Invalid Density condition. Please enter a valid format (e.g., '< 0.99', '>= 1.0').");
                continue; // Re-prompt the user for a valid input
            }

            // If we reach here, the condition is valid
            System.out.println("Density condition accepted: " + condition);

            // Add the validated condition to the query builder, wrap 'density' in backticks
            queryBuilder.addCondition("`density` " + condition);
            System.out.println("Condition added to query builder.");
            break; // Exit the loop after a valid input
        }
    }
}
