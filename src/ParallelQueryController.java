import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParallelQueryController {

    @FXML
    private Button executeParallelQueriesButton;

    @FXML
    private Button compareRedWhiteButton;

    @FXML
    private Button backButton;

    @FXML
    private Label statusLabel;

    @FXML
    private TableView<Wine> resultsTable;

    private Connection connection;
    private ParallelQueryExecutor parallelQueryExecutor;
    private Stage stage;

    public void setConnection(Connection connection) {
        this.connection = connection;
        this.parallelQueryExecutor = new ParallelQueryExecutor(connection);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        executeParallelQueriesButton.setOnAction(event -> executeParallelQueries());
        compareRedWhiteButton.setOnAction(event -> compareRedWhiteWines());
        backButton.setOnAction(event -> goBack());
    }

    private void executeParallelQueries() {
        statusLabel.setText("Executing queries in parallel...");

        // Example: Get high alcohol wines and low pH wines in parallel
        String highAlcoholQuery = "SELECT * FROM wine WHERE alcohol > 12";
        String lowPHQuery = "SELECT * FROM wine WHERE pH < 3";

        ParallelQueryExecutor.QueryResultPair resultPair =
                parallelQueryExecutor.executeQueriesParallel(highAlcoholQuery, lowPHQuery);

        // When both queries complete, display the combined results
        CompletableFuture.allOf(resultPair.getResult1(), resultPair.getResult2())
                .thenRun(() -> {
                    List<Wine> combinedResults = resultPair.getCombinedResults();
                    compareAlcoholandpH(combinedResults);
                    Platform.runLater(() -> {
                        statusLabel.setText("Found " + combinedResults.size() +
                                " wines that have either high alcohol (>12) or low pH (<3)");
                    });
                });
    }

    private void compareRedWhiteWines() {
        statusLabel.setText("Comparing red and white wines...");

        String redWinesQuery = "SELECT * FROM wine WHERE color = 'Red' AND alcohol > 11";
        String whiteWinesQuery = "SELECT * FROM wine WHERE color = 'White' AND alcohol > 11";

        ParallelQueryExecutor.QueryResultPair resultPair =
                parallelQueryExecutor.executeQueriesParallel(redWinesQuery, whiteWinesQuery);

        CompletableFuture.allOf(resultPair.getResult1(), resultPair.getResult2())
                .thenRun(() -> {
                    List<Wine> combinedResults = resultPair.getCombinedResults();
                    showResultsWindow(combinedResults); // Display results in the popup window
                    Platform.runLater(() -> statusLabel.setText("Comparison completed."));
                });
    }

    private void compareAlcoholandpH(List<Wine> wines) {
        Platform.runLater(() -> statusLabel.setText("Comparing wines by Alcohol and pH..."));

        String pHQuery = "SELECT * FROM wine WHERE pH < 3";
        String AlcoholQuery = "SELECT * FROM wine WHERE alcohol > 12";

        ParallelQueryExecutor.QueryResultPair resultPair =
                parallelQueryExecutor.executeQueriesParallel(pHQuery, AlcoholQuery);

        CompletableFuture.allOf(resultPair.getResult1(), resultPair.getResult2())
                        .thenRun(() -> {
                            List<Wine> combineResults = resultPair.getCombinedResults();
                            showResultsWindow(combineResults);
                            Platform.runLater(() -> statusLabel.setText("Comprasion Completed"));
                                });
    }

    public void showResultsWindow(List<Wine> wines) {
        Platform.runLater(() -> {
            Stage resultsStage = new Stage();
            resultsStage.setTitle("Wine Comparison Results");

            TableView<Wine> resultsTable = new TableView<>();

            TableColumn<Wine, String> wineNumberColumn = new TableColumn<>("Wine Number");
            wineNumberColumn.setCellValueFactory(new PropertyValueFactory<>("wineNumber"));

            TableColumn<Wine, String> colorColumn = new TableColumn<>("Color");
            colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

            TableColumn<Wine, Double> alcoholColumn = new TableColumn<>("Alcohol");
            alcoholColumn.setCellValueFactory(new PropertyValueFactory<>("alcohol"));

            resultsTable.getColumns().addAll(wineNumberColumn, colorColumn, alcoholColumn);

            ObservableList<Wine> wineList = FXCollections.observableArrayList(wines);
            resultsTable.setItems(wineList);

            Scene scene = new Scene(resultsTable, 600, 400);
            resultsStage.setScene(scene);
            resultsStage.show();
        });
    }

    private void goBack() {
        if (stage != null) {
            // Make sure to shutdown the executor before closing
            parallelQueryExecutor.shutdown();
            stage.close();
        }
    }
}
