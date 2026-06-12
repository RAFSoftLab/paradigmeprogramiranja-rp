package raf.rs.primeri4;

import reactor.core.publisher.Flux;

public class PrimeriError {

    public static void main(String[] args) {
        Flux<Integer> integerFlux =
                Flux.just(1, 2, 3, 4, 5);

        integerFlux
                .filter(i -> i > 10)
                .switchIfEmpty(
                        Flux.error(
                                new RuntimeException("List must not be empty")
                        )
                )
                .subscribe(System.out::println);
    }
}
