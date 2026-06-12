package raf.rs.primeri4;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PrimeriOnErrorContinue {

    public static void main(String[] args) {

        Flux<Integer> integerFlux =
                Flux.just(1, 2, 3, 4, 5);

        integerFlux
                .map(i -> i/(i-3))
                .onErrorContinue((e, i) -> {
                    System.out.format(
                            "The value %d caused the exception: %s\n", i, e
                    );
                })
                .subscribe(System.out::println,
                        System.out::println
                );

    }
}
