import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WineJDBC extends Application {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WineMenu.fxml"));
        Parent root = loader.load();

        // Initialize QueryBuilder and QueryExecutor
        QueryBuilder queryBuilder = new QueryBuilder();
        QueryExecutor queryExecutor = new QueryExecutor(connection);

        // Get the controller and pass dependencies
        WineMenuController controller = loader.getController();
        controller.setConnection(connection);
        controller.setQueryExecutor(queryExecutor);
        controller.setQueryBuilder(queryBuilder);

        // Setup and show the stage
        primaryStage.setTitle("Wine Database");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Establish the SQL connection
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/wine_project",
                    "root",
                    "Sod123456"
            );
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Launch the JavaFX application
        launch(args);

        // Close the connection on exit
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
