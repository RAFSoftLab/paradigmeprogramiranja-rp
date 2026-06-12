package raf.rs.virtuelneniti;

public class StareNiti {

    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Zdravo svima!");
        Thread thread = new Thread(task);
        thread.start();
    }
}
