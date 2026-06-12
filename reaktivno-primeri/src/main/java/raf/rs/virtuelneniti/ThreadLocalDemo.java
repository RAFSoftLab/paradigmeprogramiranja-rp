package raf.rs.virtuelneniti;

import java.util.UUID;

public class ThreadLocalDemo {

    private static final ThreadLocal<String> transactionId =
            ThreadLocal.withInitial(() -> "ID-" + UUID.randomUUID().toString().substring(0, 8));

    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Inicijalni: " + transactionId.get());
            transactionId.set(Thread.currentThread().getName() + "-noviID");
            System.out.println(Thread.currentThread().getName() + ": " + transactionId.get());
            transactionId.remove();
        };
        new Thread(task, "Thread-A").start();
        new Thread(task, "Thread-B").start();
    }
}
