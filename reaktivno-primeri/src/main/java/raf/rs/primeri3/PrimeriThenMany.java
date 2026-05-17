package raf.rs.primeri3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PrimeriThenMany {

    public static void main(String[] args) {
        Mono<Integer> monoInt = Mono.fromCallable(() -> {
            System.out.println("Executing from monoInt...");
            return 1;
        });
        Flux<Double> fluxDouble = Flux.just(1.2, 1.3);

        monoInt.thenMany(fluxDouble)
                .subscribe(System.out::println);

/*
        Flux<String> names = Mono.just("Start")
                .doOnNext(System.out::println)
                .thenMany(Flux.just("Alice", "Bob", "Charlie")).map(String::toUpperCase);

        names.subscribe(System.out::println);
*/


    }
}
