import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
//test

public class DatabaseCreator {

    private Connection connection;

    public DatabaseCreator(Connection connection) {
        this.connection = connection;
    }

    public void createTables() {
        try (Statement statement = connection.createStatement()) {
            // Create the table for quality wines, including all relevant columns
            String createTable1 = "CREATE TABLE IF NOT EXISTS quality_wines AS " +
                    "SELECT `wine number`, `date`, `alcohol`, `quality`, `pH`, `fixedAcidity`, " +
                    "`volatileAcidity`, `citricAcid`, `residualSugar`, `chlorides`, `freeSulfurDioxide`, " +
                    "`totalSulfurDioxide`, `density`, `sulphates`, `color` " +
                    "FROM wine WHERE `quality` IN ('neutral', 'slightly satisfied', 'satisfied')";
            statement.executeUpdate(createTable1);
            System.out.println("Table quality_wines created successfully.");

            // Create the table for strong red wines, including all relevant columns
            String createTable2 = "CREATE TABLE IF NOT EXISTS strong_red_wines AS " +
                    "SELECT `wine number`, `date`, `alcohol`, `color`, `pH`, `fixedAcidity`, " +
                    "`volatileAcidity`, `citricAcid`, `residualSugar`, `chlorides`, `freeSulfurDioxide`, " +
                    "`totalSulfurDioxide`, `density`, `sulphates` " +
                    "FROM wine WHERE `color` = 'red' AND `alcohol` > 12";
            statement.executeUpdate(createTable2);
            System.out.println("Table strong_red_wines created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
