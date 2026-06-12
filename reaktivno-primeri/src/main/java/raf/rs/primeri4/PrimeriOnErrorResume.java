package raf.rs.primeri4;

import reactor.core.publisher.Flux;

public class PrimeriOnErrorResume {

    public static void main(String[] args) {
        Flux<Integer> integerFlux =
                Flux.just(1, 2, 0, 5, 0);

        integerFlux
                .map(i -> i+10/i)
                .onErrorResume(e -> Flux.just(4, 5))
                .map(x->x-3)
                .subscribe(System.out::println,
                        System.out::println

                );
    }
}
