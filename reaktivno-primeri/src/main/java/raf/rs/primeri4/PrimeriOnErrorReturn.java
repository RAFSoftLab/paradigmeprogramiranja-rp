package raf.rs.primeri4;

import reactor.core.publisher.Flux;

public class PrimeriOnErrorReturn {

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.just(1, 2, 0, 4, 5, 0);

        integerFlux
                .map(i -> i+10/i)
                .onErrorReturn(ArithmeticException.class,0)
                .subscribe(
                        System.out::println,
                        System.out::println
                );
        integerFlux
                .map(i -> i+10/i)
                .onErrorReturn(e->e.getMessage().contains("/"),0)
                .subscribe(
                        System.out::println,
                        System.out::println
                );
    }
}
