import java.util.Scanner;

public class TotalSulfurDioxideFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public TotalSulfurDioxideFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        String condition = "";

        // Keep prompting until a valid condition is entered
        while (true) {
            System.out.print("Enter total sulfur dioxide condition (e.g., < 50, >= 30): ");
            condition = scanner.nextLine().trim();  // Use nextLine to capture the whole input

            // Ensure the user didn't enter an empty string
            if (condition.isEmpty()) {
                System.out.println("Condition cannot be empty. Please try again.");
                continue; // Re-prompt the user
            }

            // Validate the condition format using a regex pattern
            if (!condition.matches("[<>]=?\\s*\\d+")) {
                System.out.println("Invalid Total Sulfur Dioxide condition. Please enter a valid format (e.g., '< 50', '>= 30').");
                continue; // Re-prompt the user for a valid input
            }

            // If we reach here, the condition is valid
            System.out.println("Total Sulfur Dioxide condition accepted: " + condition);

            // Add the validated condition to the query builder, wrap 'total sulfur dioxide' in backticks
            queryBuilder.addCondition("`total sulfur dioxide` " + condition);
            System.out.println("Condition added to query builder.");
            break; // Exit the loop after a valid input
        }
    }
}

