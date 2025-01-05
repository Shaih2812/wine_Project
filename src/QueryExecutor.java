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
                System.out.println("Alcohol levels: " + resultSet.getFloat("alcohol"));
                System.out.println("PH levels: " + resultSet.getInt("pH"));
                System.out.println("Fixed acidity: " + resultSet.getString("fixed acidity"));
                System.out.println("Volatile acidity: " + resultSet.getString("volatile acidity"));
                System.out.println("Citric acid: " + resultSet.getFloat("citric acid"));
                System.out.println("Residual sugar: " + resultSet.getInt("residual sugar"));
                System.out.println("Chlorides: " + resultSet.getString("chlorides"));
                System.out.println("Free sulfur dioxide: " + resultSet.getFloat("free sulfur dioxide"));
                System.out.println("Total sulfur dioxide: " + resultSet.getInt("total sulfur dioxide"));
                System.out.println("Density: " + resultSet.getString("density"));
                System.out.println("Sulphates: " + resultSet.getString("sulphates"));

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