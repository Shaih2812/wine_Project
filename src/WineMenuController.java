import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;
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
    private Button exitButton;

    @FXML
    private TableView<Wine> resultsTable;

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
        // Configure TableView columns
        wineNumberColumn.setCellValueFactory(new PropertyValueFactory<>("wineNumber"));
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

        // Initially hide the TableView
        resultsTable.setVisible(false);

        // Set button actions
        showAllWinesButton.setOnAction(event -> showAllWines());
        showFiltersButton.setOnAction(event -> openWineFilterWindow());
        showCountButton.setOnAction(event -> openWineCountWindow());
        exitButton.setOnAction(event -> exitApplication());
    }

    private void showAllWines() {
        if (queryExecutor != null && queryBuilder != null) {
            String query = queryBuilder.getBaseQuery();
            List<Wine> wines = queryExecutor.executeQuery(query);

            // Convert List<Wine> to ObservableList and set it to the TableView
            ObservableList<Wine> wineList = FXCollections.observableArrayList(wines);
            resultsTable.setItems(wineList);

            // Show the TableView
            resultsTable.setVisible(true);
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

    private void exitApplication() {
        System.out.println("Exiting... Goodbye!");
        System.exit(0);
    }
}
