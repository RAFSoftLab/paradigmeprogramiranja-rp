package raf.rs.primeri4;

import reactor.core.publisher.Flux;

public class PrimeriOnErrorMap {

    public static void main(String[] args) {
        Flux<Integer> integerFlux =
                Flux.just(1, 2, 3, 4, 5);

        integerFlux
                .map(i -> i/(i-3))
                .onErrorMap(e -> new RuntimeException(
                        "Unexpected exception", e)
                )
                .subscribe(System.out::println,
                        System.out::println
                );
    }
}
