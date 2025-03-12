import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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
    private Button parallelQueryButton; // New button for parallel queries

    @FXML
    private Button exitButton;

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
        parallelQueryButton.setOnAction(event -> openParallelQueryWindow()); // Handler for new button
        exitButton.setOnAction(event -> exitApplication());
    }

    private void showAllWines() {
        if (queryExecutor != null && queryBuilder != null) {
            String query = queryBuilder.getBaseQuery();
            List<Wine> wines = queryExecutor.executeQuery(query);

            try {
                // Load WineTable.fxml for displaying wines
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WineTable.fxml"));
                Scene scene = new Scene(loader.load());

                WineTableController controller = loader.getController();
                controller.setWines(wines);

                Stage stage = new Stage();
                stage.setTitle("All Wines");
                stage.setScene(scene);

                controller.setStage(stage);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("QueryExecutor or QueryBuilder is not set.");
        }
    }

    private void openWineCountWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WineCountMenu.fxml"));
            Scene scene = new Scene(loader.load());

            WineCountMenuController controller = loader.getController();
            controller.setConnection(connection);

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
            controller.setConnection(connection);

            Stage stage = new Stage();
            stage.setTitle("Wine Filter Options");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New method to open the parallel query window
    private void openParallelQueryWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ParallelQueryView.fxml"));
            Scene scene = new Scene(loader.load());

            ParallelQueryController controller = loader.getController();
            controller.setConnection(connection);

            Stage stage = new Stage();
            stage.setTitle("Parallel Queries");
            stage.setScene(scene);
            controller.setStage(stage);
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