import java.util.Scanner;

public class FixedAcidityFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public FixedAcidityFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        String condition = "";

        // Keep prompting until a valid condition is entered
        while (true) {
            System.out.print("Enter fixed acidity condition (e.g., < 6.2, >= 7.5): ");
            condition = scanner.nextLine().trim();  // Use nextLine to capture the whole input

            // Ensure the user didn't enter an empty string
            if (condition.isEmpty()) {
                System.out.println("Condition cannot be empty. Please try again.");
                continue; // Re-prompt the user
            }

            // Validate the condition format using a regex pattern
            if (!condition.matches("[<>]=?\\s*\\d+(\\.\\d+)?")) {
                System.out.println("Invalid Fixed Acidity condition. Please enter a valid format (e.g., '< 6.2', '>= 5.0').");
                continue; // Re-prompt the user for a valid input
            }

            // If we reach here, the condition is valid
            System.out.println("Fixed Acidity condition accepted: " + condition);

            // Add the validated condition to the query builder
            // Use backticks around column name to ensure proper syntax
            queryBuilder.addCondition("`Fixed Acidity` " + condition);
            System.out.println("Condition added to query builder.");
            break; // Exit the loop after a valid input
        }
    }
}
