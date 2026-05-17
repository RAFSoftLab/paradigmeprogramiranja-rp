package raf.rs.primeri2;

import reactor.core.publisher.Flux;

public class PrimeriCast {

    public static void main(String[] args) {
        Flux<Number> brojeviFlux = Flux.just(1L,2L,3L,5L);
        brojeviFlux.cast(Long.class).subscribe(br-> System.out.println(br.getClass()));


    }
}
