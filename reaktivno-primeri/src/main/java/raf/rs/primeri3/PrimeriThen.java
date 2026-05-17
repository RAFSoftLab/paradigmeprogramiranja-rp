package raf.rs.primeri3;

import reactor.core.publisher.Mono;

public class PrimeriThen {

    public static void main(String[] args) {

        Mono<String> monoString = Mono.just("abc");

        Mono<Integer> monoInt = Mono.fromCallable(() -> {
            System.out.println("Executing from monoInt...");
            return 1;
        });

        monoInt.then().subscribe(System.out::println);

        monoInt.then(monoString).subscribe(System.out::println);
    }
}
