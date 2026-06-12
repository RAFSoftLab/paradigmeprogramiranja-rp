package raf.rs.virtuelneniti;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnstructuredConcurrency {

    public static void main(String[] args) {

    }

    public Rezultat vratiRezultat() throws ExecutionException, InterruptedException {
        try (ExecutorService es = Executors.newVirtualThreadPerTaskExecutor()) {
            var trenutnaFuture = es.submit(()->getTrenutna());
            var prognozaFuture = es.submit(()->getIstorija());
            var istorijaFuture = es.submit(()->getPrognoza());

            return new Rezultat(trenutnaFuture.get(), prognozaFuture.get(), istorijaFuture.get());
        }
    }

    private static String getTrenutna(){
        return null;
    }

    private static String getIstorija(){
        return null;
    }

    private static String getPrognoza(){
        return null;
    }
}
