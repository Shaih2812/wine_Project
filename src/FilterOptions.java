import java.util.Scanner;

public class FilterOptions {
    private final Scanner scanner;
    private final QueryBuilder queryBuilder;
    private final QueryExecutor queryExecutor;
    private final NumberOfWines numberOfWines;
    private final QualityFilter qualityFilter;
    private final PHFilter phFilter;
    private final FixedAcidityFilter fixedAcidityFilter;
    private final VolatileAcidityFilter volatileAcidityFilter;
    private final CitricAcidFilter citricAcidFilter;
    private final ResidualSugarFilter residualSugarFilter;
    private final FreeSulfurDioxideFilter freeSulfurDioxideFilter;  // Added FreeSulfurDioxideFilter
    private final TotalSulfurDioxideFilter totalSulfurDioxideFilter;  // Added TotalSulfurDioxideFilter
    private final DensityFilter densityFilter;  // Added DensityFilter
    private final SulphatesFilter sulphatesFilter;  // Added SulphatesFilter
    private final ChloridesFilter chloridesFilter;  // Added ChloridesFilter

    public FilterOptions(Scanner scanner, QueryBuilder queryBuilder, QueryExecutor queryExecutor) {
        this.scanner = scanner;
        this.queryBuilder = queryBuilder;
        this.queryExecutor = queryExecutor;
        this.numberOfWines = new NumberOfWines(scanner, queryExecutor);
        this.qualityFilter = new QualityFilter(scanner, queryBuilder);
        this.phFilter = new PHFilter(scanner, queryBuilder);
        this.fixedAcidityFilter = new FixedAcidityFilter(scanner, queryBuilder);
        this.volatileAcidityFilter = new VolatileAcidityFilter(scanner, queryBuilder);
        this.citricAcidFilter = new CitricAcidFilter(scanner, queryBuilder);
        this.residualSugarFilter = new ResidualSugarFilter(scanner, queryBuilder);
        this.freeSulfurDioxideFilter = new FreeSulfurDioxideFilter(scanner, queryBuilder);  // Initialize FreeSulfurDioxideFilter
        this.totalSulfurDioxideFilter = new TotalSulfurDioxideFilter(scanner, queryBuilder);  // Initialize TotalSulfurDioxideFilter
        this.densityFilter = new DensityFilter(scanner, queryBuilder);  // Initialize DensityFilter
        this.sulphatesFilter = new SulphatesFilter(scanner, queryBuilder);  // Initialize SulphatesFilter
        this.chloridesFilter = new ChloridesFilter(scanner, queryBuilder);  // Initialize ChloridesFilter
    }

    public void showFilterOptions() {
        while (true) {
            System.out.println("Filter by:");
            System.out.println("1. Color");
            System.out.println("2. Quality");
            System.out.println("3. Alcohol Levels");
            System.out.println("4. pH Level");
            System.out.println("5. Fixed Acidity");
            System.out.println("6. Volatile Acidity");
            System.out.println("7. Residual Sugar");
            System.out.println("8. Citric Acid");
            System.out.println("9. Free Sulfur Dioxide");  // New option for Free Sulfur Dioxide
            System.out.println("10. Total Sulfur Dioxide");  // New option for Total Sulfur Dioxide
            System.out.println("11. Density");  // New option for Density
            System.out.println("12. Sulphates");  // New option for Sulphates
            System.out.println("13. Chlorides");  // New option for Chlorides
            System.out.println("14. Apply Filters");
            System.out.println("15. Reset Filters");
            System.out.println("16. Back to Main Menu");

            int choice = InputHandler.getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    filterByColor();
                    break;
                case 2:
                    qualityFilter.apply();  // Apply quality filter
                    break;
                case 3:
                    filterByAlcoholContent();
                    break;
                case 4:
                    phFilter.apply();  // Apply pH filter
                    break;
                case 5:
                    fixedAcidityFilter.apply();  // Apply Fixed Acidity filter
                    break;
                case 6:
                    volatileAcidityFilter.apply();  // Apply Volatile Acidity filter
                    break;
                case 7:
                    residualSugarFilter.apply();  // Apply Residual Sugar filter
                    break;
                case 8:
                    citricAcidFilter.apply();  // Apply Citric Acid filter
                    break;
                case 9:
                    freeSulfurDioxideFilter.apply();  // Apply Free Sulfur Dioxide filter
                    break;
                case 10:
                    totalSulfurDioxideFilter.apply();  // Apply Total Sulfur Dioxide filter
                    break;
                case 11:
                    densityFilter.apply();  // Apply Density filter
                    break;
                case 12:
                    sulphatesFilter.apply();  // Apply Sulphates filter
                    break;
                case 13:
                    chloridesFilter.apply();  // Apply Chlorides filter
                    break;
                case 14:
                    applyFilters();
                    return;
                case 15:
                    resetFilters();
                    break;
                case 16:
                    return;  // Go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void filterByColor() {
        System.out.println("Enter color to filter by (e.g., Red, White): ");
        String color = scanner.next();
        queryBuilder.addCondition("color = '" + color + "'");
    }

    private void filterByAlcoholContent() {
        float minAlcohol = InputHandler.getFloatInput(scanner, "Enter minimum alcohol levels: ");
        float maxAlcohol = InputHandler.getFloatInput(scanner, "Enter maximum alcohol levels: ");
        queryBuilder.addCondition("alcohol BETWEEN " + minAlcohol + " AND " + maxAlcohol);
    }

    private void applyFilters() {
        String query = queryBuilder.getQuery();

        // Check if any conditions have been applied
        if (query.trim().isEmpty()) {
            System.out.println("No filters have been applied.");
        } else {
            System.out.println("Applying filters with query: " + query);
            queryExecutor.executeQuery(query);
        }
    }

    private void resetFilters() {
        queryBuilder.resetQuery();
        System.out.println("Filters have been reset.");
    }

    public void showNumberOfWines() {
        numberOfWines.show();
    }
}
