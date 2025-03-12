import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelQueryExecutor {
    private final QueryExecutor queryExecutor;
    private final ExecutorService executorService;

    public ParallelQueryExecutor(Connection connection) {
        this.queryExecutor = new QueryExecutor(connection);
        this.executorService = Executors.newFixedThreadPool(2);
    }

    /**
     * Execute two queries concurrently and return the results as they complete
     * @param query1 First query to execute
     * @param query2 Second query to execute
     * @return A pair of CompletableFuture objects containing the results of each query
     */
    public QueryResultPair executeQueriesParallel(String query1, String query2) {
        CompletableFuture<List<Wine>> future1 = CompletableFuture.supplyAsync(
                () -> queryExecutor.executeQuery(query1),
                executorService
        );

        CompletableFuture<List<Wine>> future2 = CompletableFuture.supplyAsync(
                () -> queryExecutor.executeQuery(query2),
                executorService
        );

        return new QueryResultPair(future1, future2);
    }

    /**
     * Execute two count queries concurrently
     * @param query1 First count query
     * @param query2 Second count query
     * @param description1 Description for first query
     * @param description2 Description for second query
     */
    public void executeCountQueriesParallel(String query1, String query2, String description1, String description2) {
        CompletableFuture.runAsync(
                () -> queryExecutor.executeCountQuery(query1, description1),
                executorService
        );

        CompletableFuture.runAsync(
                () -> queryExecutor.executeCountQuery(query2, description2),
                executorService
        );
    }

    /**
     * Shutdown the executor service
     */
    public void shutdown() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    /**
     * Class to hold a pair of query results as CompletableFutures
     */
    public class QueryResultPair {
        private final CompletableFuture<List<Wine>> result1;
        private final CompletableFuture<List<Wine>> result2;

        public QueryResultPair(CompletableFuture<List<Wine>> result1, CompletableFuture<List<Wine>> result2) {
            this.result1 = result1;
            this.result2 = result2;
        }

        public CompletableFuture<List<Wine>> getResult1() {
            return result1;
        }

        public CompletableFuture<List<Wine>> getResult2() {
            return result2;
        }

        /**
         * Wait for both queries to complete and return a combined list of results
         * @return Combined list of wines from both queries
         */
        public List<Wine> getCombinedResults() {
            List<Wine> wines1 = result1.join();
            List<Wine> wines2 = result2.join();
            wines1.addAll(wines2);
            return wines1;
        }
    }
}