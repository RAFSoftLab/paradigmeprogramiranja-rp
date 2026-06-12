package raf.rs.virtuelneniti.primerimoodle;

public class ThreadLocalPrimer {
    private static final ThreadLocal<String> status =
            ThreadLocal.withInitial(()->"PORUCENO");

    public static void main(String[] args) {
        Runnable task1 = () -> {
            System.out.println(Thread.currentThread().getName() + ", status: " + status.get());
            status.set("PRIHVACENO");
            System.out.println(Thread.currentThread().getName() + ", status: " + status.get());
            status.remove();
        };

        Runnable task2 = () -> {
            System.out.println(Thread.currentThread().getName() + ", status: " + status.get());
            status.set("ODBIJENO");
            System.out.println(Thread.currentThread().getName() + ", status: " + status.get());
            status.remove();
        };
        new Thread(task1, "Prva").start();
        new Thread(task2, "Druga").start();
        new Thread(task1, "Treca").start();
    }
}
