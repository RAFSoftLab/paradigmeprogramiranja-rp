package raf.rs.primeri3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PrimeriThenEmpty {

    public static void main(String[] args) {
        Mono<Integer> monoInt = Mono.fromCallable(() -> {
            System.out.println("Executing from monoInt...");
            return 1;
        });
        Mono<String> monoString = Mono.fromCallable(() -> {
            System.out.println("Executing from monoString...");
            return "a";
        });
        Mono<Void> monoVoid = Mono.fromRunnable(() -> {
            System.out.println("Executing from monoVoid...");
        });

        Mono<Void> monoVoid1 = Mono.fromRunnable(() -> {
            System.out.println("Executing from second monoVoid...");
        });

        monoInt.then(monoString).thenEmpty(monoVoid).thenEmpty(monoVoid1).
                subscribe(System.out::println);

    }
}
