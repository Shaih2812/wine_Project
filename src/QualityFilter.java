public class QualityFilter {
    private final QueryBuilder queryBuilder;

    public QualityFilter(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public void apply(String selectedQuality) {
        // Assuming selectedQuality is the value from the dropdown menu
        if (selectedQuality != null && !selectedQuality.isEmpty()) {
            // Escape single quotes in the string
            queryBuilder.addCondition(selectedQuality);
        }
    }
}