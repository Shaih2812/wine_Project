import java.util.Scanner;

public class VolatileAcidityFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public VolatileAcidityFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        String condition = "";

        // Keep prompting until a valid condition is entered
        while (true) {
            System.out.print("Enter volatile acidity condition (e.g., < 0.5, >= 0.3, = 1.5): ");
            condition = scanner.nextLine().trim();  // Use nextLine to capture the whole input

            // Ensure the user didn't enter an empty string
            if (condition.isEmpty()) {
                System.out.println("Condition cannot be empty. Please try again.");
                continue; // Re-prompt the user
            }

            // Validate the condition format using a regex pattern
            if (!condition.matches("[<>]=?\\s*-?\\d+(\\.\\d+)?")) {
                System.out.println("Invalid Volatile Acidity condition. Please enter a valid format (e.g., '< 0.5', '>= 0.3', '= 1.5').");
                continue; // Re-prompt the user for a valid input
            }

            // If we reach here, the condition is valid
            System.out.println("Volatile Acidity condition accepted: " + condition);

            // Add the validated condition to the query builder
            // Use backticks around column name to ensure proper syntax
            queryBuilder.addCondition("`Volatile Acidity` " + condition);
            System.out.println("Condition added to query builder.");
            break; // Exit the loop after a valid input
        }
    }
}

