package raf.rs.primerimoodle;

import reactor.core.publisher.Flux;

public class PrimeriOnError {

    public static void main(String[] args) {
        Flux.just(2, 0, 4)
                .map(broj -> 20 / broj)
                .onErrorContinue((e, element) -> {
                    System.out.println("Preskačem: " + element);
                })
                .map(rezultat -> rezultat + 1)
                .subscribe(
                        Sistem -> System.out.println("Uspelo: " + Sistem),
                        greshka -> System.out.println("Fatalna greška!")
                );
    }


}
