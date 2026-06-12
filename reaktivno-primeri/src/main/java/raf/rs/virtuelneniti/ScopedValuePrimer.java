package raf.rs.virtuelneniti;



public class ScopedValuePrimer {
    public static final ScopedValue<String> USER =  ScopedValue.newInstance();

    Runnable runnable = () -> {
        System.out.println(String.format("Prijava korisnika %s",USER.get()));
    };

    ScopedValue.CallableOp<String,Exception> callable = () -> {
        System.out.println(String.format("Prijava korisnika <Callable> %s",USER.get()));
        return USER.get().toUpperCase();
    };

    public static void main(String... args) throws Exception {
        new ScopedValuePrimer().execute();
    }

    private void execute() throws Exception {
        ScopedValue.where(USER, "Zika").run(runnable);
        var value = ScopedValue.where(USER,"Mika").call(callable);
        System.out.println("Rez = "+value);

    }
}
