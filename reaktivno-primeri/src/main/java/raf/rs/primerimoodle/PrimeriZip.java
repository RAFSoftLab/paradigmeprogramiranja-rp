package raf.rs.primerimoodle;

import reactor.core.publisher.Flux;

public class PrimeriZip {

    public static void main(String[] args) {
        Flux<Integer> ints1 = Flux.just(2,4,6,10);
        Flux<Integer> ints2 = Flux.just(10,24,30,40);

        Flux.zip(ints1,ints2)
                .map(t->t.getT2()/t.getT1())
                .filter(x->x%2==0)
                .subscribe();
    }
}
