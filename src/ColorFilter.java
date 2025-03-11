import java.util.Scanner;

public class ColorFilter {
    private final QueryBuilder queryBuilder;

    public ColorFilter(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public void apply(String selectedColor) {
        if (selectedColor != null && !selectedColor.isEmpty()) {
            // Escape single quotes in the string
            queryBuilder.addCondition(selectedColor);
        }
    }
}
