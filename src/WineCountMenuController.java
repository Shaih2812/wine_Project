import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    private Label wineCountLabel;

    private Connection connection;

    // Method to set the database connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // Initialize method, executed after FXML is loaded
    @FXML
    private void initialize() {
        // Setting up button actions without relying on the scene
        allWinesButton.setOnAction(event -> handleWineCount("All wines"));
        redWinesButton.setOnAction(event -> handleWineCount("Red wines"));
        whiteWinesButton.setOnAction(event -> handleWineCount("White wines"));

        // Hide the wine count label initially
        wineCountLabel.setVisible(false);
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

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                int totalWines = rs.getInt("total_wines");
                wineCountLabel.setText("Number of " + type + ": " + totalWines);
                wineCountLabel.setVisible(true);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving number of wines: " + e.getMessage());
        }
    }
}
