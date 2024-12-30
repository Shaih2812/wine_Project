public class QueryBuilder {
    private final String baseQuery = "SELECT * FROM wine";
    private StringBuilder queryBuilder;

    public QueryBuilder() {
        queryBuilder = new StringBuilder(baseQuery);
    }

    public void appendWhereClause() {
        if (!queryBuilder.toString().contains("WHERE")) {
            queryBuilder.append(" WHERE");
        } else {
            queryBuilder.append(" AND");
        }
    }

    public void resetQuery() {
        queryBuilder = new StringBuilder(baseQuery);
    }

    public void addCondition(String condition) {
        appendWhereClause();
        queryBuilder.append(" ").append(condition);
    }

    public String getQuery() {
        return queryBuilder.toString();
    }

    public String getBaseQuery() {
        return baseQuery;
    }
}