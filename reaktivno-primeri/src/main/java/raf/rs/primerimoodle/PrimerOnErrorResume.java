package raf.rs.primerimoodle;

import reactor.core.publisher.Flux;

public class PrimerOnErrorResume {

    static void main() {
        Flux<Integer> integerFlux =
                Flux.just(1, 2, 3, 4, 5);

        integerFlux
                .map(i -> i/(i-3))
                .onErrorResume(e -> Flux.just(4, 5))
                .subscribe(System.out::println,
                        System.out::println
                );
    }
}
