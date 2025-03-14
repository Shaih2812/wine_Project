import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//test

public class WineTableController {

    @FXML
    private TableView<Wine> resultsTable;

    @FXML
    private TableColumn<Wine, String> dateColumn;

    @FXML
    private TableColumn<Wine, Integer> wineNumberColumn;
    @FXML
    private TableColumn<Wine, String> colorColumn;
    @FXML
    private TableColumn<Wine, String> qualityColumn;
    @FXML
    private TableColumn<Wine, Float> alcoholColumn;
    @FXML
    private TableColumn<Wine, Float> pHColumn;
    @FXML
    private TableColumn<Wine, Float> fixedAcidityColumn;
    @FXML
    private TableColumn<Wine, Float> volatileAcidityColumn;
    @FXML
    private TableColumn<Wine, Float> citricAcidColumn;
    @FXML
    private TableColumn<Wine, Float> residualSugarColumn;
    @FXML
    private TableColumn<Wine, Float> chloridesColumn;
    @FXML
    private TableColumn<Wine, Float> freeSulfurDioxideColumn;
    @FXML
    private TableColumn<Wine, Float> totalSulfurDioxideColumn;
    @FXML
    private TableColumn<Wine, Float> densityColumn;
    @FXML
    private TableColumn<Wine, Float> sulphatesColumn;

    @FXML
    private Button backButton;

    private Stage stage;
    private String tableName;  // New variable to store the table name
    private Connection connection;

    public void setWines(List<Wine> wines) {
        ObservableList<Wine> wineList = FXCollections.observableArrayList(wines);
        resultsTable.setItems(wineList);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;  // Set connection for database queries
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;  // Set the table name (quality_wines or strong_red_wines)
    }

    public void loadData() {
        // Check if connection and tableName are set; if not, skip loading
        if (connection == null || tableName == null) {
            System.out.println("Connection or tableName not set, skipping loadData.");
            return;
        }

        List<Wine> wineList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String query;
            // Adjust the query for the new tables to alias columns appropriately.
            if (tableName.equals("quality_wines")) {
                query = "SELECT " +
                        "`wine number` AS wineNumber, " +
                        "`date` AS date, " +
                        "NULL AS color, " +               // Not available in quality_wines
                        "`quality` AS quality, " +
                        "`alcohol` AS alcohol, " +
                        "0 AS pH, " +
                        "0 AS fixedAcidity, " +
                        "0 AS volatileAcidity, " +
                        "0 AS citricAcid, " +
                        "0 AS residualSugar, " +
                        "0 AS chlorides, " +
                        "0 AS freeSulfurDioxide, " +
                        "0 AS totalSulfurDioxide, " +
                        "0 AS density, " +
                        "0 AS sulphates " +
                        "FROM " + tableName;
            } else if (tableName.equals("strong_red_wines")) {
                query = "SELECT " +
                        "`wine number` AS wineNumber, " +
                        "`date` AS date, " +
                        "`color` AS color, " +
                        "NULL AS quality, " +              // Not available in strong_red_wines
                        "`alcohol` AS alcohol, " +
                        "0 AS pH, " +
                        "0 AS fixedAcidity, " +
                        "0 AS volatileAcidity, " +
                        "0 AS citricAcid, " +
                        "0 AS residualSugar, " +
                        "0 AS chlorides, " +
                        "0 AS freeSulfurDioxide, " +
                        "0 AS totalSulfurDioxide, " +
                        "0 AS density, " +
                        "0 AS sulphates " +
                        "FROM " + tableName;
            } else {
                // For any other table, do a regular query.
                query = "SELECT * FROM " + tableName;
            }

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Wine wine = new Wine(
                        resultSet.getInt("wineNumber"),
                        resultSet.getDate("date"),
                        resultSet.getString("color"),
                        resultSet.getString("quality"),
                        resultSet.getFloat("alcohol"),
                        resultSet.getFloat("pH"),
                        resultSet.getFloat("fixedAcidity"),
                        resultSet.getFloat("volatileAcidity"),
                        resultSet.getFloat("citricAcid"),
                        resultSet.getFloat("residualSugar"),
                        resultSet.getFloat("chlorides"),
                        resultSet.getInt("freeSulfurDioxide"),
                        resultSet.getInt("totalSulfurDioxide"),
                        resultSet.getFloat("density"),
                        resultSet.getFloat("sulphates")
                );
                wineList.add(wine);
            }
            setWines(wineList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        wineNumberColumn.setCellValueFactory(new PropertyValueFactory<>("wineNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        qualityColumn.setCellValueFactory(new PropertyValueFactory<>("quality"));
        alcoholColumn.setCellValueFactory(new PropertyValueFactory<>("alcohol"));
        pHColumn.setCellValueFactory(new PropertyValueFactory<>("pH"));
        fixedAcidityColumn.setCellValueFactory(new PropertyValueFactory<>("fixedAcidity"));
        volatileAcidityColumn.setCellValueFactory(new PropertyValueFactory<>("volatileAcidity"));
        citricAcidColumn.setCellValueFactory(new PropertyValueFactory<>("citricAcid"));
        residualSugarColumn.setCellValueFactory(new PropertyValueFactory<>("residualSugar"));
        chloridesColumn.setCellValueFactory(new PropertyValueFactory<>("chlorides"));
        freeSulfurDioxideColumn.setCellValueFactory(new PropertyValueFactory<>("freeSulfurDioxide"));
        totalSulfurDioxideColumn.setCellValueFactory(new PropertyValueFactory<>("totalSulfurDioxide"));
        densityColumn.setCellValueFactory(new PropertyValueFactory<>("density"));
        sulphatesColumn.setCellValueFactory(new PropertyValueFactory<>("sulphates"));

        // Do not call loadData() here because connection/tableName might not be set yet.
        // loadData();

        backButton.setOnAction(event -> {
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        });
    }
}
