package raf.rs.virtuelneniti;

public class VirtuelneNitiPrimer1 {

    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Zdravo svima!");
        Thread.ofVirtual().name("vt1").start(task);

        Thread unstarted = Thread.ofVirtual().name("vt2").unstarted(task);
        unstarted.start();



    }
}
