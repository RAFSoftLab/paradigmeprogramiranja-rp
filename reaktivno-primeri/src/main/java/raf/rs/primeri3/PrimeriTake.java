package raf.rs.primeri3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PrimeriTake {

    public static void main(String[] args) {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofMillis(100));
/*
        intervalFlux
                .take(Duration.ofSeconds(5))
                .subscribe(
                        val -> System.out.println("Received: " + val),
                        err -> {},
                        () -> System.out.println("Stream timed out and completed!")
                );

        intervalFlux.takeUntil(i->i==10).subscribe(System.out::println);
*/
        Mono<String> stopButtonSignal = Mono.delay(Duration.ofSeconds(2))
                .map(d -> "Stop Clicked");
                //.doOnNext(val->System.out.println("Signal received: " + val));
        intervalFlux.takeUntilOther(stopButtonSignal)
            .doOnComplete(()-> System.out.println("Tok prekinut"))
            .subscribe(System.out::println);

        /*
        stopButtonSignal.subscribe(reason -> {
            System.out.println("LOG: Stream is stopping because: " + reason);
        });
        */


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



}
