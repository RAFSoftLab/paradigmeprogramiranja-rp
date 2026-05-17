package raf.rs.primeri3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

public class PrimeriFilterWhen {

    public static void main(String[] args) {
        Flux<Integer> integerFlux =
                Flux.just(1, 2, 3, 4, 5, 6);

        integerFlux
                .filterWhen(i -> Mono.just(i % 2 == 0))
                .subscribe(System.out::println);

        Flux<Integer> integerFlux1 =
                Flux.just(1, 2, 3, 4, 5, 6);

        integerFlux1
                .filterWhen(i -> Mono.just(false))
                //.filterWhen(i -> Mono.empty()) // Same as false
                .subscribe(System.out::println);

        Flux<String> userIds = Flux.just("user-1", "user-2", "user-3");

        Flux<String> activeUsers = userIds.filterWhen(u ->
                isActiveUserAsynch(u) // Returns Mono<Boolean>
        );

        activeUsers.subscribe(System.out::println);


        Flux<Integer> integerFlux2 =
                Flux.just(1, 2, 3, 4, 5, 6);

        integerFlux
                .filterWhen(i -> Flux.just(i<2, i % 2 == 0))
                .subscribe(System.out::println);


    }

    public static Mono<Boolean> isActiveUserAsynch(String user){
        Random rand = new Random();
        return Mono.just(rand.nextBoolean());
    }



}
