import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class TableSelectionMenuController {

    private Connection connection;

    @FXML
    private Button viewQualityWinesButton;

    @FXML
    private Button viewStrongRedWinesButton;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @FXML
    private void initialize() {
        viewQualityWinesButton.setOnAction(event -> openQualityWinesWindow());
        viewStrongRedWinesButton.setOnAction(event -> openStrongRedWinesWindow());
    }

    private void openQualityWinesWindow() {
        // Load and display the Quality Wines table
        showTable("quality_wines");
    }

    private void openStrongRedWinesWindow() {
        // Load and display the Strong Red Wines table
        showTable("strong_red_wines");
    }

    private void showTable(String tableName) {
        try {
            // Load the relevant table view for displaying wines
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WineTable.fxml"));
            Scene scene = new Scene(loader.load());

            WineTableController controller = loader.getController();
            controller.setTableName(tableName);  // Pass the table name to the controller
            controller.setConnection(connection);  // Pass the connection so that loadData() can work
            controller.loadData();               // Explicitly load data after properties are set

            Stage stage = new Stage();
            stage.setTitle(tableName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
