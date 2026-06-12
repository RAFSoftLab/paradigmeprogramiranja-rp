package raf.rs.primeri4;

import reactor.core.publisher.Flux;

public class PrimeriObradaGresaka {

    public static void main(String[] args) {
        Flux<Integer> integerFlux =
                Flux.just(1, 2, 3, 4, 5);

        integerFlux
                .map(i -> i/(i-3))
                .map(i -> i*2)
                .subscribe(System.out::println,e-> System.out.println("Error: "+e.getMessage()));
    }
}
