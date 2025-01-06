import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

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
        showFiltersButton.setOnAction(event -> showFilteringOptions());
        showCountButton.setOnAction(event -> showNumberOfWines());
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

    private void showNumberOfWines() {
        // Add logic to show the number of wines
    }

    private void exitApplication() {
        System.out.println("Exiting... Goodbye!");
        System.exit(0);
    }
}
