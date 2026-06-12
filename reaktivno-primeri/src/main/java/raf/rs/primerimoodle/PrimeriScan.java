package raf.rs.primerimoodle;

import reactor.core.publisher.Flux;

public class PrimeriScan {

    public static void main(String[] args) {
        Flux.just(3, 1, 4, 2)
                .scan((akumulator, sledeci) -> akumulator * sledeci)
                .filter(broj -> broj < 5)
                .subscribe(System.out::println);
    }
}
