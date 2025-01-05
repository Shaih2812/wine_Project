
            import java.sql.*;
import java.util.*;

            public class WineJDBC {
                public static void main(String[] args) {
                    Scanner scanner = new Scanner(System.in);

                    try (Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://127.0.0.1:3306/wine_updated", //project
                            "root",
                            "Sod123456"
                    )) {
                        QueryBuilder queryBuilder = new QueryBuilder();
                        QueryExecutor queryExecutor = new QueryExecutor(connection);
                        FilterOptions filterOptions = new FilterOptions(scanner, queryBuilder, queryExecutor);

                        while (true) {
                            System.out.println("Available filtering options:");
                            System.out.println("1. Show all wines");
                            System.out.println("2. Show filtering options");
                            System.out.println("3. Show number of wines");
                            System.out.println("4. Exit");

                            int choice = InputHandler.getIntInput(scanner, "Enter your choice: ");

                            switch (choice) {
                                case 1:
                                    queryExecutor.executeQuery(queryBuilder.getBaseQuery());
                                    break;
                                case 2:
                                    filterOptions.showFilterOptions();
                                    break;
                                case 3:
                                    filterOptions.showNumberOfWines();
                                    break;
                                case 4:
                                    System.out.println("Exiting... Goodbye!");
                                    return;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
