package raf.rs.primeri3;

import reactor.core.publisher.Mono;

public class PrimeriThenReturn {

    public static void main(String[] args) {
        Mono<Integer> monoInt = Mono.fromCallable(() -> {
            System.out.println("Executing from monoInt...");
            return 1;
        });
        Mono<String> monoString = monoInt.thenReturn("a");

        monoString.subscribe(System.out::println);
    }

}
