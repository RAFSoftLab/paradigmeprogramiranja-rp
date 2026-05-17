package raf.rs.primeri3;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class PrimeriDistinct {

    public static void main(String[] args) {
        Flux<User> users = Flux.just(new User(1,"bilja"), new User(2,"maja"), new User(3,"ana"), new User(2,"marija"));
        users.distinct(u->u.getId()).subscribe(System.out::println);

        Flux<String> states = Flux.just("START", "START", "RUNNING", "RUNNING", "START")
                .distinctUntilChanged();

        states.subscribe(System.out::println);

        Flux<Double> temperatures = Flux.just(20.1, 20.2, 20.1, 25.0, 25.1)
                .distinctUntilChanged(Function.identity(),(oldTemp, newTemp) ->
                        Math.abs(oldTemp - newTemp) < 0.5
                );
        temperatures.subscribe(System.out::println);

    }
}
