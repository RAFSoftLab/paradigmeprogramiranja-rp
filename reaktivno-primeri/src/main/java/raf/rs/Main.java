package raf.rs;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // kreiranje
        Flux<String> seq1 = Flux.just("ana", "mara", "mirjana");

        List<String> iterable = Arrays.asList("ana", "mara", "mirjana");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("ana");
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);


        // subscribe sa lambda
/*
        seq1.subscribe(i-> System.out.println(i));
        seq1.subscribe(x-> System.out.println(x));

        Flux<String> seq1new = seq1.map(String::toUpperCase);
        seq1new.subscribe(System.out::println);

        // tok koji ima grešku
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.out.println("Error: " + error));

        // obrada kraja toka
        Flux<Integer> intsNew = Flux.range(1, 4);
        intsNew.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));


        Flux<Integer> ints2 = Flux.range(1, 10);
        ints2.flatMap(i->Flux.range(i,i+5)).subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));

        // kreiranje subscribera


        Flux<Integer> ints1 = Flux.range(1, 6);

        ints1.subscribe(new MojSubscriber1<Integer>());
        */

        // kombinacija više tokova

        Flux<Integer> brojevi = Flux.range(1,8);
        Flux<String> meseci = Flux.just("Jan","Feb","Mart","April","Maj","Jun","Jul","Avgust");
        Flux<String> kombinacija = Flux.zip(brojevi,meseci).map(par->par.getT1() + "."+par.getT2());
       // kombinacija.subscribe(a-> System.out.println(a));


        Flux<Integer> brojevi1 = Flux.just(20,30,40,50);
      // brojevi.mergeWith(brojevi1).subscribe(x-> System.out.println(x));
        brojevi.concat(brojevi1).subscribe(x-> System.out.println(x));






    }
}