package raf.rs.primeri4;

import reactor.core.publisher.Mono;

public class PrimeriDoOnError {

    public static void main(String[] args) {
        Mono<Integer> monoNula = Mono.just(0);
        monoNula.map(x->5/x)
                .doOnError(ArithmeticException.class,e-> System.out.println("Deljenje nulom"));
               // .subscribe();

        monoNula.map(x->5/x)
                .doOnError(e->e.getMessage().contains("zero"),
                           e-> System.out.println("Greska: "+e.getMessage()))
                .subscribe(System.out::println);
    }
}
