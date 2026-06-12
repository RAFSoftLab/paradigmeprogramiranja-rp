package raf.rs.virtuelneniti;

public class ScopedValueNoValue {
    public static final ScopedValue<String> USER =  ScopedValue.newInstance();

    Runnable runnable = () -> {
        System.out.println(String.format("Prijava korisnika %s"
                ,USER.isBound() ? USER.get() : "nepoznat"));
    };
    public static void main(String... args) {
        new ScopedValueNoValue().execute();
    }

    private void execute() {
       Thread.ofVirtual().start(runnable);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
