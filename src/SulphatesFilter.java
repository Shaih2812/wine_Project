import java.util.Scanner;

public class SulphatesFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public SulphatesFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        String condition = "";

        // Keep prompting until a valid condition is entered
        while (true) {
            System.out.print("Enter sulphates condition (e.g., < 0.5, >= 0.3): ");
            condition = scanner.nextLine().trim();  // Capture the full input

            // Ensure the user didn't enter an empty string
            if (condition.isEmpty()) {
                System.out.println("Condition cannot be empty. Please try again.");
                continue; // Re-prompt the user
            }

            // Validate the condition format using a regex pattern
            if (!condition.matches("[<>]=?\\s*\\d+(\\.\\d+)?")) {
                System.out.println("Invalid sulphates condition. Please enter a valid format (e.g., '< 0.5', '>= 0.3').");
                continue; // Re-prompt the user for a valid input
            }

            // If we reach here, the condition is valid
            System.out.println("Sulphates condition accepted: " + condition);

            // Add the validated condition to the query builder, wrap 'sulphates' in backticks
            queryBuilder.addCondition("`sulphates` " + condition);
            System.out.println("Condition added to query builder.");
            break; // Exit the loop after a valid input
        }
    }
}
