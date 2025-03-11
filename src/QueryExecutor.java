import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {
    private final Connection connection;

    public QueryExecutor(Connection connection) {
        this.connection = connection;
    }

    public List<Wine> executeQuery(String query) {
        List<Wine> wines = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Wine wine = new Wine(
                        resultSet.getInt("wine number"),
                        resultSet.getDate("date"),
                        resultSet.getString("color"),
                        resultSet.getString("quality"),
                        resultSet.getFloat("alcohol"),
                        resultSet.getInt("pH"),
                        resultSet.getString("fixed acidity"),
                        resultSet.getString("volatile acidity"),
                        resultSet.getFloat("citric acid"),
                        resultSet.getInt("residual sugar"),
                        resultSet.getString("chlorides"),
                        resultSet.getFloat("free sulfur dioxide"),
                        resultSet.getInt("total sulfur dioxide"),
                        resultSet.getString("density"),
                        resultSet.getString("sulphates")
                );
                wines.add(wine);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
        return wines;
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