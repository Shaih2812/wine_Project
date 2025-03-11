import java.util.Scanner;

public class FilterOptions {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;
    private final QueryExecutor queryExecutor;
    private final NumberOfWines numberOfWines;
    private final QualityFilter qualityFilter;
    private final PHFilter phFilter;


    public FilterOptions(Scanner scanner, QueryBuilder queryBuilder, QueryExecutor queryExecutor) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
        this.queryExecutor = queryExecutor;
        this.numberOfWines = new NumberOfWines(scanner, queryExecutor);
        this.qualityFilter = new QualityFilter(queryBuilder);
        this.phFilter = new PHFilter(scanner, queryBuilder);
    }

    private void filterByColor() {
        String color = scanner.next();
        queryBuilder.addCondition("color = '" + color + "'");
    }

    private void applyFilters() {
        String query = queryBuilder.getQuery();

        // Check if any conditions have been applied
        if (query.trim().isEmpty()) {
            System.out.println("No filters have been applied.");
        } else {
            System.out.println("Applying filters with query: " + query);
            queryExecutor.executeQuery(query);
        }
    }

    private void resetFilters() {
        queryBuilder.resetQuery();
        System.out.println("Filters have been reset.");
    }

    public void showNumberOfWines() {
        numberOfWines.show();
    }
}
