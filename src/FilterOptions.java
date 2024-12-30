import java.util.Scanner;

public class FilterOptions {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;
    private final QueryExecutor queryExecutor;

    public FilterOptions(Scanner scanner, QueryBuilder queryBuilder, QueryExecutor queryExecutor) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
        this.queryExecutor = queryExecutor;
    }

    public void showFilterOptions() {
        while (true) {
            System.out.println("Filtering Options:");
            System.out.println("1. Color");
            System.out.println("2. Alcohol levels");
            System.out.println("3. Quality (rating)");
            System.out.println("4. pH");
            System.out.println("5. Execute query");
            System.out.println("6. Reset filters");
            System.out.println("7. Exit to Main Menu");

            int choice = InputHandler.getIntInput(scanner, "Enter your filter choice: ");
            switch (choice) {
                case 1:
                    applyColorFilter();
                    break;
                case 2:
                    applyAlcoholFilter();
                    break;
                case 3:
                    applyQualityFilter();
                    break;
                case 4:
                    applyPHFilter();
                    break;
                case 5:
                    queryExecutor.executeQuery(queryBuilder.getQuery());
                    return;
                case 6:
                    queryBuilder.resetQuery();
                    System.out.println("Filters reset.");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void applyColorFilter() {
        System.out.println("Choose color:");
        System.out.println("1. Red");
        System.out.println("2. White");
        int colorChoice = InputHandler.getIntInput(scanner, "Enter your choice: ");
        String color = (colorChoice == 1) ? "Red" : "White";
        queryBuilder.addCondition("color = '" + color + "'");
    }

    private void applyAlcoholFilter() {
        System.out.println("Choose alcohol filter:");
        System.out.println("1. Equal to (=)");
        System.out.println("2. Greater than or equal to (>=)");
        System.out.println("3. Less than or equal to (<=)");
        int operatorChoice = InputHandler.getIntInput(scanner, "Enter your choice: ");
        float alcoholValue = InputHandler.getFloatInput(scanner, "Enter alcohol value: ");
        String operator = switch (operatorChoice) {
            case 1 -> "=";
            case 2 -> ">=";
            case 3 -> "<=";
            default -> {
                System.out.println("Invalid choice.");
                yield "=";
            }
        };
        queryBuilder.addCondition("alcohol " + operator + " " + alcoholValue);
    }

    private void applyQualityFilter() {
        System.out.println("Quality options:");
        System.out.println("Hello");
        System.out.println("1. Extremly dissatisfied");
        System.out.println("2. Moderately dissatisfied");
        System.out.println("3. Slightly dissatisfied");
        System.out.println("4. Neutral");
        System.out.println("5. Slightly satisfied");
        System.out.println("6. Moderately satisfied");
        System.out.println("7. Extremly satisfied");

        int qualityChoice = InputHandler.getIntInput(scanner, "Enter your choice: ");
        String quality = switch (qualityChoice) {
            case 1 -> "extremely dissatisfied";
            case 2 -> "moderately dissatisfied";
            case 3 -> "slightly dissatisfied";
            case 4 -> "neutral";
            case 5 -> "slightly satisfied";
            case 6 -> "moderately satisfied";
            case 7 -> "extremely satisfied";
            default -> {
                System.out.println("Invalid choice.");
                yield "";
            }
        };
        if (!quality.isEmpty()) {
            queryBuilder.addCondition("quality = '" + quality + "'");
        }
    }

    private void applyPHFilter() {
        System.out.print("Enter pH condition (e.g., < 6): ");
        String condition = scanner.next();
        queryBuilder.addCondition("pH " + condition);
    }

    public void showNumberOfWines() {
        System.out.println("Choose color:");
        System.out.println("1. Red");
        System.out.println("2. White");
        System.out.println("3. Both");
        int choice = InputHandler.getIntInput(scanner, "Enter your choice: ");
        String query;
        switch (choice) {
            case 1:
                query = "SELECT COUNT(*) AS wine_count FROM wine_updated WHERE color = 'Red'";
                break;
            case 2:
                query = "SELECT COUNT(*) AS wine_count FROM wine_updated WHERE color = 'White'";
                break;
            case 3:
                query = "SELECT COUNT(*) AS wine_count FROM wine_updated";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        queryExecutor.executeCountQuery(query, "Number of Wines");
    }
}