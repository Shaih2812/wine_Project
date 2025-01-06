import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WineCountMenuController {

    @FXML
    private Button allWinesButton;

    @FXML
    private Button redWinesButton;

    @FXML
    private Button whiteWinesButton;

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @FXML
    private void initialize() {
        allWinesButton.setOnAction(event -> handleWineCount("All wines"));
        redWinesButton.setOnAction(event -> handleWineCount("Red wines"));
        whiteWinesButton.setOnAction(event -> handleWineCount("White wines"));
    }

    private void handleWineCount(String type) {
        String query = "";

        switch (type) {
            case "All wines":
                query = "SELECT COUNT(*) AS total_wines FROM wine";
                break;
            case "Red wines":
                query = "SELECT COUNT(*) AS total_wines FROM wine WHERE color = 'Red'";
                break;
            case "White wines":
                query = "SELECT COUNT(*) AS total_wines FROM wine WHERE color = 'White'";
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        // Execute the query and show the result (you can use the same logic as before)
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                int totalWines = rs.getInt("total_wines");
                System.out.println("Total number of " + type + ": " + totalWines);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving number of wines: " + e.getMessage());
        }

        // Close the window after action
        Stage stage = (Stage) allWinesButton.getScene().getWindow();
        stage.close();
    }
}
