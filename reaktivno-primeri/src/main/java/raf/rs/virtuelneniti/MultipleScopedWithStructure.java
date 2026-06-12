package raf.rs.virtuelneniti;



import java.util.List;
import java.util.concurrent.StructuredTaskScope;

public class MultipleScopedWithStructure {
    private static final ScopedValue<String> USER = ScopedValue.newInstance();
    private static final ScopedValue<String> COMP = ScopedValue.newInstance();
    private static final ScopedValue<String> ROLE = ScopedValue.newInstance();

    public void runLogic() throws Exception {
        List<String> results = ScopedValue
                .where(USER, "mika")
                .where(COMP, "RAF")
                .where(ROLE, "Admin")
                .call(() -> {
                    return executeTaskWithScope();
                });

        System.out.println(results.stream().reduce("Rezultati:",(str1,str2)->str1+"\n"+str2));

    }

    private List<String> executeTaskWithScope() throws InterruptedException {
        try (var scope = StructuredTaskScope.open()) {
            var rez1 = scope.fork(() -> performAction("Validation"));
            var rez2 = scope.fork(() -> performAction("Security-Check"));
            var rez3 = scope.fork(() -> performAction("Logging"));

            scope.join();
            return List.of(rez1.get(),rez2.get(),rez3.get());
        }
    }

    private String performAction(String actionName) {
        return String.format("[%s] User: %s, Comp: %s, Role: %s",
                actionName, USER.get(), COMP.get(), ROLE.get());
    }

    public static void main(String[] args) throws Exception {
        new MultipleScopedWithStructure().runLogic();
    }


}
