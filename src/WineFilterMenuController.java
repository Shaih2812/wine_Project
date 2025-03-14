import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class WineFilterMenuController {

    @FXML
    private DatePicker startDateFilterPicker;
    @FXML
    private DatePicker endDateFilterPicker;
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
        // Set default dates - start date to 2022-01-01, end date to 2024-12-10
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 10);

        startDateFilterPicker.setValue(startDate);
        endDateFilterPicker.setValue(endDate);

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
        // Reset the query builder before adding new conditions
        queryBuilder.resetQuery();

        // Get the selected values from the filters
        LocalDate startDate = startDateFilterPicker.getValue();
        LocalDate endDate = endDateFilterPicker.getValue();
        String selectedColor = colorChoiceBox.getValue();
        String selectedQuality = qualityChoiceBox.getValue();
        String selectedAlcohol = alcoholFilterChoice.getValue();
        String phLevel = pHFilterChoice.getValue();

        System.out.println("Selected Start Date: " + startDate);
        System.out.println("Selected End Date: " + endDate);
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

        if (startDate != null && endDate != null) {
            String formattedStartDate = startDate.getYear() + "/" + String.format("%02d", startDate.getMonthValue()) + "/" + String.format("%02d", startDate.getDayOfMonth());
            String formattedEndDate = endDate.getYear() + "/" + String.format("%02d", endDate.getMonthValue()) + "/" + String.format("%02d", endDate.getDayOfMonth());

            System.out.println("Formatted start date for query: " + formattedStartDate);
            System.out.println("Formatted end date for query: " + formattedEndDate);
            queryBuilder.addCondition("DATE_FORMAT(date, '%Y/%m/%d') BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate + "'");
        }
        System.out.println("Final Query: " + queryBuilder.getQuery());
        System.out.println("Filters applied.");
    }

    private void executeQueryWithFilters() {
        // Apply filters first to ensure we have the latest query
        applyFilters();

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

        TableColumn<Wine, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Wine, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Wine, String> qualityColumn = new TableColumn<>("Quality");
        qualityColumn.setCellValueFactory(new PropertyValueFactory<>("quality"));

        TableColumn<Wine, Float> alcoholColumn = new TableColumn<>("Alcohol");
        alcoholColumn.setCellValueFactory(new PropertyValueFactory<>("alcohol"));

        TableColumn<Wine, Float> pHColumn = new TableColumn<>("pH");
        pHColumn.setCellValueFactory(new PropertyValueFactory<>("pH"));

        TableColumn<Wine, Float> volatileAcidityColumn = new TableColumn<>("Volatile Acidity");
        volatileAcidityColumn.setCellValueFactory(new PropertyValueFactory<>("volatileAcidity"));

        TableColumn<Wine, String> fixedAcidityColumn = new TableColumn<>("Fixed Acidity");
        fixedAcidityColumn.setCellValueFactory(new PropertyValueFactory<>("fixedAcidity"));

        TableColumn<Wine, Float> citricAcidColumn = new TableColumn<>("Citric Acid");
        citricAcidColumn.setCellValueFactory(new PropertyValueFactory<>("citricAcid"));

        TableColumn<Wine, Float> residualSugarColumn = new TableColumn<>("Residual Sugar");
        residualSugarColumn.setCellValueFactory(new PropertyValueFactory<>("residualSugar"));

        TableColumn<Wine, Float> chloridesColumn = new TableColumn<>("Chlorides");
        chloridesColumn.setCellValueFactory(new PropertyValueFactory<>("chlorides"));

        TableColumn<Wine, Integer> freesulfurDioxideColumn = new TableColumn<>("Free Sulfur Dioxide");
        freesulfurDioxideColumn.setCellValueFactory(new PropertyValueFactory<>("freeSulfurDioxide"));

        TableColumn<Wine, Integer> totalsulfurDioxideColumn = new TableColumn<>("Total Sulfur Dioxide");
        totalsulfurDioxideColumn.setCellValueFactory(new PropertyValueFactory<>("totalSulfurDioxide"));

        TableColumn<Wine, Float> densityColumn = new TableColumn<>("Density");
        densityColumn.setCellValueFactory(new PropertyValueFactory<>("density"));

        TableColumn<Wine, Float> sulphatesColumn = new TableColumn<>("Sulphates");
        sulphatesColumn.setCellValueFactory(new PropertyValueFactory<>("sulphates"));

        // Add columns to the TableView
        tableView.getColumns().addAll(wineNumberColumn, dateColumn, fixedAcidityColumn, volatileAcidityColumn, citricAcidColumn, residualSugarColumn, chloridesColumn, freesulfurDioxideColumn, totalsulfurDioxideColumn, densityColumn, pHColumn, sulphatesColumn, alcoholColumn, qualityColumn, colorColumn);

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
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 10);

        startDateFilterPicker.setValue(startDate);
        endDateFilterPicker.setValue(endDate);
        colorChoiceBox.setValue("Choose Color");
        qualityChoiceBox.setValue("Choose Quality");
        alcoholFilterChoice.setValue(null);
        pHFilterChoice.setValue(null);

        // Reset the query builder
        queryBuilder.resetQuery();

        System.out.println("Filters reset.");
    }

}
