import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class WineFilterMenuController {

    @FXML
    private ChoiceBox<String> colorChoiceBox;
    @FXML
    private ChoiceBox<String> qualityChoiceBox;
    @FXML
    private ComboBox<String> alcoholFilterChoice;
    @FXML
    private ComboBox<String> pHFilterChoice;
    @FXML
    private Button applyFiltersButton;
    @FXML
    private Button resetFiltersButton;
    @FXML
    private Button backToMainMenuButton;
    @FXML
    private Button goButton;  // Go! Button to execute query

    private Connection connection;
    private QueryBuilder queryBuilder;
    private QueryExecutor queryExecutor;

    // Set connection to the database
    public void setConnection(Connection connection) {
        this.connection = connection;
        this.queryBuilder = new QueryBuilder();
        this.queryExecutor = new QueryExecutor(connection);
    }

    @FXML
    private void initialize() {
        colorChoiceBox.setValue("Choose Color");
        qualityChoiceBox.setValue("Choose Quality");
        alcoholFilterChoice.setValue(null);  // Allow it to be empty initially
        pHFilterChoice.setValue(null);

        // Assign actions to buttons
        applyFiltersButton.setOnAction(event -> applyFilters());
        resetFiltersButton.setOnAction(event -> resetFilters());
        backToMainMenuButton.setOnAction(event -> goBackToMainMenu());
        goButton.setOnAction(event -> executeQueryWithFilters());
    }

    private void applyFilters() {
        // Get the selected values from the filters
        String selectedColor = colorChoiceBox.getValue();
        String selectedQuality = qualityChoiceBox.getValue();
        String selectedAlcohol = alcoholFilterChoice.getValue();
        String phLevel = pHFilterChoice.getValue();

        System.out.println("Selected Color: " + selectedColor);
        System.out.println("Selected Quality: " + selectedQuality);
        System.out.println("Selected Alcohol: " + selectedAlcohol);
        System.out.println("Selected pH Level: " + phLevel);

        // Use the QueryBuilder to add conditions based on selected filters
        if (selectedColor != null && !selectedColor.isEmpty() && !selectedColor.equals("Choose Color")) {
            queryBuilder.addCondition("color = '" + selectedColor + "'");
        }
        if (selectedQuality != null && !selectedQuality.isEmpty() && !selectedQuality.equals("Choose Quality")) {
            queryBuilder.addCondition("quality = '" + selectedQuality + "'");
        }
        if (selectedAlcohol != null && !selectedAlcohol.isEmpty()) {
            String alcoholCondition = "";
            if (selectedAlcohol.equals("> 12")) {
                alcoholCondition = "alcohol > 12";
            } else if (selectedAlcohol.equals("= 12")) {
                alcoholCondition = "alcohol = 12";
            } else if (selectedAlcohol.equals("< 12")) {
                alcoholCondition = "alcohol < 12";
            }
            if (!alcoholCondition.isEmpty()) {
                queryBuilder.addCondition(alcoholCondition);
                System.out.println("Added condition: " + alcoholCondition);
            }
        }
        if (phLevel != null && !phLevel.isEmpty()) {
            queryBuilder.addCondition("pH " + phLevel);
        }
        System.out.println("Final Query: " + queryBuilder.getQuery());
        System.out.println("Filters applied.");
    }

    private void executeQueryWithFilters() {
        // Get the constructed query
        String query = queryBuilder.getQuery();

        if (query.equals(queryBuilder.getBaseQuery())) {
            System.out.println("No filters selected, please select desired filters and try again.");
        } else {
            // Execute the query with the applied filters
            List<Wine> wines = queryExecutor.executeQuery(query);
            System.out.println("Query executed with applied filters.");
            showFilteredResultsInNewWindow(wines);
        }
    }
    private void showFilteredResultsInNewWindow(List<Wine> wines) {
        // Create a new TableView for the filtered results
        TableView<Wine> tableView = new TableView<>();

        // Define columns (same as in your main menu controller)
        TableColumn<Wine, Integer> wineNumberColumn = new TableColumn<>("Wine Number");
        wineNumberColumn.setCellValueFactory(new PropertyValueFactory<>("wineNumber"));

        TableColumn<Wine, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Wine, String> qualityColumn = new TableColumn<>("Quality");
        qualityColumn.setCellValueFactory(new PropertyValueFactory<>("quality"));

        TableColumn<Wine, Float> alcoholColumn = new TableColumn<>("Alcohol");
        alcoholColumn.setCellValueFactory(new PropertyValueFactory<>("alcohol"));

        TableColumn<Wine, Float> pHColumn = new TableColumn<>("pH");
        pHColumn.setCellValueFactory(new PropertyValueFactory<>("pH"));

        // Add other columns as needed...

        // Add columns to the TableView
        tableView.getColumns().addAll(wineNumberColumn, colorColumn, qualityColumn, alcoholColumn, pHColumn);

        // Convert List<Wine> to ObservableList and set it as the data for the TableView
        ObservableList<Wine> wineList = FXCollections.observableArrayList(wines);
        tableView.setItems(wineList);

        // Create a new Stage (pop-up window)
        Stage stage = new Stage();
        stage.setTitle("Filtered Results");

        // Create a Scene with the TableView
        Scene scene = new Scene(tableView, 800, 600); // Adjust size as needed
        stage.setScene(scene);

        // Show the pop-up window
        stage.show();
    }
    private void goBackToMainMenu() {
        // Logic to go back to the main menu
        Stage stage = (Stage) backToMainMenuButton.getScene().getWindow();
        stage.close();  // Close the filter window
    }
    @FXML
    private void handleGoButtonAction() {
            System.out.println("Executing query with filters...");

            // Execute the query with applied filters first
            executeQueryWithFilters();

    }
    private void resetFilters() {
        // Reset all filter values
        colorChoiceBox.setValue("Choose Color");
        qualityChoiceBox.setValue("Choose Quality");
        alcoholFilterChoice.setValue(null);
        pHFilterChoice.setValue(null);

        // Reset the query builder
        queryBuilder.resetQuery();

        System.out.println("Filters reset.");
    }

}
