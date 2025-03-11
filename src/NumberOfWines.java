import java.util.Scanner;

public class NumberOfWines {
    private final Scanner scanner;
    private final QueryExecutor queryExecutor;

    public NumberOfWines(Scanner scanner, QueryExecutor queryExecutor) {
        this.scanner = scanner;
        this.queryExecutor = queryExecutor;
    }

    public void show() {
        int choice = InputHandler.getIntInput(scanner, "Enter your choice: ");
        String query;
        switch (choice) {
            case 1:
                query = "SELECT COUNT(*) AS wine_count FROM wine WHERE color = 'Red'";
                break;
            case 2:
                query = "SELECT COUNT(*) AS wine_count FROM wine WHERE color = 'White'";
                break;
            case 3:
                query = "SELECT COUNT(*) AS wine_count FROM wine";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        queryExecutor.executeCountQuery(query, "Number of Wines");
    }
}

