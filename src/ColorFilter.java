import java.util.Scanner;

public class ColorFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public ColorFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        System.out.println("Choose color:");
        System.out.println("1. Red");
        System.out.println("2. White");
        int colorChoice = InputHandler.getIntInput(scanner, "Enter your choice: ");
        String color = (colorChoice == 1) ? "Red" : "White";
        queryBuilder.addCondition("color = '" + color + "'");
    }
}
