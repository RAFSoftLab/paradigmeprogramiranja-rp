package raf.rs.virtuelneniti;

public class MultipleScopedValues {

    private static final ScopedValue<String> USER = ScopedValue.newInstance();
    private static final ScopedValue<String> COMP = ScopedValue.newInstance();
    private static final ScopedValue<String> ROLE = ScopedValue.newInstance();

    Runnable runnable = () -> {
        System.out.println(String.format("Prijava korisnika %s, zaposlen u kompaniji %s, na poziciji %s",USER.get(),COMP.get(), ROLE.get()));
    };

    public static void main(String... args)  {
        new MultipleScopedValues().execute();
    }

    private void execute() {
        ScopedValue.where(USER, "Zika")
                   .where(COMP, "RAF")
                    .where(ROLE,"developer").run(runnable);
    }
}
