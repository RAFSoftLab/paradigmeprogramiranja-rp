package raf.rs.virtuelneniti;

import java.util.concurrent.StructuredTaskScope;


public class StructuredConcurrency {

    public static void main(String[] args) {
        try {
            System.out.println(vratiRezultatInvokeAny());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Rezultat vratiRezultatInvokeAll() throws InterruptedException {

        try (var scope = StructuredTaskScope.open()){
            var trenutnaSubtask = scope.fork(()->getTrenutna());
            var istorijaSubtask = scope.fork(()->getIstorija());
            var prognozaSubtask = scope.fork(()->getPrognoza());
            scope.join();
            return new Rezultat(trenutnaSubtask.get(), istorijaSubtask.get(), prognozaSubtask.get());
        }
    }

    public static String vratiRezultatInvokeAny() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.anySuccessfulResultOrThrow())){
            var prognozaSubtask1 = scope.fork(()->getPrognozaS1());
            var prognozaSubtask2= scope.fork(()->getPrognozaS2());
            String rez = (String)scope.join();
            return rez;
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

    private static String getPrognozaS1(){
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Servis 1";
    }
    private static String getPrognozaS2(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Servis 2";
    }
}
