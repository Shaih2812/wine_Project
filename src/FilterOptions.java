import java.util.Scanner;

public class FilterOptions {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;
    private final QueryExecutor queryExecutor;
    private final NumberOfWines numberOfWines;
    private final QualityFilter qualityFilter;

    public FilterOptions(Scanner scanner, QueryBuilder queryBuilder, QueryExecutor queryExecutor) {
        this.scanner = scanner;
        //test
        this.queryBuilder = queryBuilder;
        this.queryExecutor = queryExecutor;
        this.numberOfWines = new NumberOfWines(scanner, queryExecutor);
        this.qualityFilter = new QualityFilter(scanner, queryBuilder);
    }

    public void showFilterOptions() {
        while (true) {
            System.out.println("Filter by:");
            System.out.println("1. Color");
            System.out.println("2. Quality");
            System.out.println("3. Alcohol Levels");
            System.out.println("4. Apply Filters");
            System.out.println("5. Reset Filters");
            System.out.println("6. Back to Main Menu");

            int choice = InputHandler.getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    filterByColor();
                    break;
                case 2:
                    qualityFilter.apply(); // Delegate to QualityFilter
                    break;
                case 3:
                    filterByAlcoholContent();
                    break;
                case 4:
                    applyFilters();
                    return;
                case 5:
                    resetFilters();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void filterByColor() {
        System.out.println("Enter color to filter by (e.g., Red, White): ");
        String color = scanner.next();
        queryBuilder.addCondition("color = '" + color + "'");
    }

    private void filterByAlcoholContent() {
        float minAlcohol = InputHandler.getFloatInput(scanner, "Enter minimum alcohol levels: ");
        float maxAlcohol = InputHandler.getFloatInput(scanner, "Enter maximum alcohol levels: ");
        queryBuilder.addCondition("alcohol BETWEEN " + minAlcohol + " AND " + maxAlcohol);
    }

    private void applyFilters() {
        String query = queryBuilder.getQuery();
        queryExecutor.executeQuery(query);
    }

    private void resetFilters() {
        queryBuilder.resetQuery();
        System.out.println("Filters have been reset.");
    }

    public void showNumberOfWines() {
        numberOfWines.show();
    }
}
