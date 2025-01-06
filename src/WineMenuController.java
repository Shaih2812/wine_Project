import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.Connection;

public class WineMenuController {

    private Connection connection;
    private QueryExecutor queryExecutor;
    private QueryBuilder queryBuilder;

    @FXML
    private Button showAllWinesButton;

    @FXML
    private Button showFiltersButton;

    @FXML
    private Button showCountButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<String> resultsTable;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setQueryExecutor(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    @FXML
    private void initialize() {
        // Set button actions
        showAllWinesButton.setOnAction(event -> showAllWines());
        showFiltersButton.setOnAction(event -> openWineFilterWindow());
        showCountButton.setOnAction(event -> openWineCountWindow());
        exitButton.setOnAction(event -> exitApplication());
    }

    private void showAllWines() {
        if (queryExecutor != null && queryBuilder != null) {
            String query = queryBuilder.getBaseQuery();
            queryExecutor.executeQuery(query);
        } else {
            System.out.println("QueryExecutor or QueryBuilder is not set.");
        }
    }

    private void showFilteringOptions() {
        // Add logic to display filtering options
    }

    private void openWineCountWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WineCountMenu.fxml"));
            Scene scene = new Scene(loader.load());

            WineCountMenuController controller = loader.getController();
            controller.setConnection(connection);  // Pass the connection to the new controller

            Stage stage = new Stage();
            stage.setTitle("Wine Count Options");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWineFilterWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WineFilterMenu.fxml"));
            Scene scene = new Scene(loader.load());

            WineFilterMenuController controller = loader.getController();
            controller.setConnection(connection);  // Pass the connection to the new controller

            Stage stage = new Stage();
            stage.setTitle("Wine Filter Options");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exitApplication() {
        System.out.println("Exiting... Goodbye!");
        System.exit(0);
    }
}
