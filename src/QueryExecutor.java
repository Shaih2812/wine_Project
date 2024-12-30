import java.sql.*;

public class QueryExecutor {
    private final Connection connection;

    public QueryExecutor(Connection connection) {
        this.connection = connection;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("Wine number: " + resultSet.getInt("wine number"));
                System.out.println("Color: " + resultSet.getString("color"));
                System.out.println("Quality: " + resultSet.getString("quality"));
                System.out.println("Alcohol: " + resultSet.getFloat("alcohol"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void executeCountQuery(String query, String description) {
        System.out.println("Executing query for: " + description);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                int wineCount = resultSet.getInt("wine_count");
                System.out.println(description + ": " + wineCount);
            }
        } catch (SQLException e) {
            System.out.println("Error executing count query: " + e.getMessage());
            e.printStackTrace();
        }
    }
}