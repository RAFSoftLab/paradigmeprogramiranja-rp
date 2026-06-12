package raf.rs.virtuelneniti.customstrategy;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;

public class TestQuorumStrategy {

    public static List<String> getServiceData() {
        try (var scope = StructuredTaskScope.open(new QuorumJoiner<String>(3))) {
            scope.fork(() -> callService("Service A", 100));
            scope.fork(() -> callService("Service B", 500));
            scope.fork(() -> callService("Service C", 50));
            scope.fork(() -> callService("Service D", 150));
            scope.fork(() -> callService("Service E", 250));
            scope.fork(() -> callService("Service F", 350));
            List<String> rez = scope.join();
            return rez;
        } catch (IllegalStateException | InterruptedException e) {
            return List.of();
        }
    }

    private static String callService(String name, int delay) throws InterruptedException {
        Thread.sleep(delay);
        System.out.println(name + " finished!");
        return name + " Data";
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getServiceData());
    }
}
