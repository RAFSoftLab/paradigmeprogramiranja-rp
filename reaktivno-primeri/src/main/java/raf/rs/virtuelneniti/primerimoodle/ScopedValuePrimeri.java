package raf.rs.virtuelneniti.primerimoodle;

import java.util.List;
import java.util.Map;

public class ScopedValuePrimeri {

    private static final ScopedValue<String> USER = ScopedValue.newInstance();
    private static final ScopedValue<List<String>> ROLES = ScopedValue.newInstance();
    private static final ScopedValue<String> ROLE = ScopedValue.newInstance();

    static void main() throws Exception {
            ScopedValue.CallableOp<Boolean,Exception> callable = () -> ROLES.get().contains(ROLE.get());
            var sv = ScopedValue.where(ROLES, List.of("DevOps","FE","BE","QA","Data"));
            List<String> users = List.of("Zika","Mika","Panta","Mirko");
            List<String> roles = List.of("DevOps","Adim","PM","BE");
            for(int i=0;i<users.size();i++) {
                var rez = sv.where(USER, users.get(i)).where(ROLE, roles.get(i)).call(callable);
                System.out.println(rez);
            }
    }
}

