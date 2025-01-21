import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class WineTableController {

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

    @FXML
    private Button backButton;

    private Stage stage;

    public void setWines(List<Wine> wines) {
        ObservableList<Wine> wineList = FXCollections.observableArrayList(wines);
        resultsTable.setItems(wineList);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
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

        backButton.setOnAction(event -> {
            if (stage != null) {
                stage.close();
            }
        });
    }
}
