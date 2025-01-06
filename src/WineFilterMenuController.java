import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;

public class WineFilterMenuController {

    @FXML
    private Button colorFilterButton;
    @FXML
    private Button qualityFilterButton;
    @FXML
    private Button alcoholLevelFilterButton;
    @FXML
    private Button phLevelFilterButton;
    @FXML
    private Button fixedAcidityFilterButton;
    @FXML
    private Button volatileAcidityFilterButton;
    @FXML
    private Button residualSugarFilterButton;
    @FXML
    private Button citricAcidFilterButton;
    @FXML
    private Button freeSulfurDioxideFilterButton;
    @FXML
    private Button totalSulfurDioxideFilterButton;
    @FXML
    private Button densityFilterButton;
    @FXML
    private Button sulphatesFilterButton;
    @FXML
    private Button chloridesFilterButton;
    @FXML
    private Button applyFiltersButton;
    @FXML
    private Button resetFiltersButton;
    @FXML
    private Button backToMainMenuButton;

    private Connection connection;

    // Set connection to the database
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @FXML
    private void initialize() {
        colorFilterButton.setOnAction(event -> handleColorFilter());
        qualityFilterButton.setOnAction(event -> handleQualityFilter());
        alcoholLevelFilterButton.setOnAction(event -> handleAlcoholLevelFilter());
        phLevelFilterButton.setOnAction(event -> handlePhLevelFilter());
        fixedAcidityFilterButton.setOnAction(event -> handleFixedAcidityFilter());
        volatileAcidityFilterButton.setOnAction(event -> handleVolatileAcidityFilter());
        residualSugarFilterButton.setOnAction(event -> handleResidualSugarFilter());
        citricAcidFilterButton.setOnAction(event -> handleCitricAcidFilter());
        freeSulfurDioxideFilterButton.setOnAction(event -> handleFreeSulfurDioxideFilter());
        totalSulfurDioxideFilterButton.setOnAction(event -> handleTotalSulfurDioxideFilter());
        densityFilterButton.setOnAction(event -> handleDensityFilter());
        sulphatesFilterButton.setOnAction(event -> handleSulphatesFilter());
        chloridesFilterButton.setOnAction(event -> handleChloridesFilter());
        applyFiltersButton.setOnAction(event -> applyFilters());
        resetFiltersButton.setOnAction(event -> resetFilters());
        backToMainMenuButton.setOnAction(event -> goBackToMainMenu());
    }

    // Handle the filter actions
    private void handleColorFilter() {
        // Logic for filtering by color
        System.out.println("Filtering by Color");
    }

    private void handleQualityFilter() {
        // Logic for filtering by quality
        System.out.println("Filtering by Quality");
    }

    private void handleAlcoholLevelFilter() {
        // Logic for filtering by alcohol levels
        System.out.println("Filtering by Alcohol Levels");
    }

    private void handlePhLevelFilter() {
        // Logic for filtering by pH Level
        System.out.println("Filtering by pH Level");
    }

    private void handleFixedAcidityFilter() {
        // Logic for filtering by Fixed Acidity
        System.out.println("Filtering by Fixed Acidity");
    }

    private void handleVolatileAcidityFilter() {
        // Logic for filtering by Volatile Acidity
        System.out.println("Filtering by Volatile Acidity");
    }

    private void handleResidualSugarFilter() {
        // Logic for filtering by Residual Sugar
        System.out.println("Filtering by Residual Sugar");
    }

    private void handleCitricAcidFilter() {
        // Logic for filtering by Citric Acid
        System.out.println("Filtering by Citric Acid");
    }

    private void handleFreeSulfurDioxideFilter() {
        // Logic for filtering by Free Sulfur Dioxide
        System.out.println("Filtering by Free Sulfur Dioxide");
    }

    private void handleTotalSulfurDioxideFilter() {
        // Logic for filtering by Total Sulfur Dioxide
        System.out.println("Filtering by Total Sulfur Dioxide");
    }

    private void handleDensityFilter() {
        // Logic for filtering by Density
        System.out.println("Filtering by Density");
    }

    private void handleSulphatesFilter() {
        // Logic for filtering by Sulphates
        System.out.println("Filtering by Sulphates");
    }

    private void handleChloridesFilter() {
        // Logic for filtering by Chlorides
        System.out.println("Filtering by Chlorides");
    }

    private void applyFilters() {
        // Logic to apply selected filters
        System.out.println("Filters applied.");
    }

    private void resetFilters() {
        // Logic to reset all filters
        System.out.println("Filters reset.");
    }

    private void goBackToMainMenu() {
        // Logic to go back to the main menu
        Stage stage = (Stage) backToMainMenuButton.getScene().getWindow();
        stage.close();  // Close the filter window
    }
}
