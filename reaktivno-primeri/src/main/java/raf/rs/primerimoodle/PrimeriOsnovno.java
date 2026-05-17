package raf.rs.primerimoodle;

import reactor.core.publisher.Flux;

public class PrimeriOsnovno {

    public static void main(String[] args) {
        Flux<Integer> ints = Flux.just(1,4,2,6,8)
                .skip(3)
                .map(i -> {
                    if (i%2==0) return i;
                    throw new RuntimeException("Neparan broj");
                });
        ints.subscribe(i -> System.out.println(i),
                e -> System.out.println("Greska: " + e.getMessage()),
                ()-> System.out.println("Kraj"));
    }




}



