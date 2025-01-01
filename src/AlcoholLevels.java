import java.util.Scanner;

public class AlcoholLevels {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public AlcoholLevels(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
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
}
