import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class QueryUtils {

    public static <R> Map<String, R> execute(List<String> tables, Function<String, R> query, R defaultValue) {
        Map<String, R> tableToResultMap = new ConcurrentHashMap<>();

        CompletableFuture<?>[] futures = tables.stream()
                .map(table -> CompletableFuture
                        .supplyAsync(() -> tableToResultMap.put(table, query.apply(table)))
                        .exceptionally(throwable -> tableToResultMap.put(table, defaultValue)))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

        return tableToResultMap;
    }

    public static void main(String[] args) {
        //some implementation
        execute(List.of("table1", "table2"), table -> {
            if (table.equals("table1")) {
                return 2;
            } else {
                throw new RuntimeException("Exception");
            }
        }, 5).forEach((key, value) -> System.out.printf("%s: %d\n", key, value));
    }
}