import java.util.Scanner;

public class PHFilter {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;

    public PHFilter(Scanner scanner, QueryBuilder queryBuilder) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
    }

    public void apply() {
        System.out.print("Enter pH condition (e.g., <6): ");
        String condition = scanner.next();
        queryBuilder.addCondition("pH " + condition);
    }
}
