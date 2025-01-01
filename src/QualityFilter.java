import java.util.Scanner;

public class QualityFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public QualityFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        System.out.println("Quality options:");
        System.out.println("1. Extremely dissatisfied");
        System.out.println("2. Moderately dissatisfied");
        System.out.println("3. Slightly dissatisfied");
        System.out.println("4. Neutral");
        System.out.println("5. Slightly satisfied");
        System.out.println("6. Moderately satisfied");
        System.out.println("7. Extremely satisfied");

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
}
