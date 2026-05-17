package raf.rs.primeri3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PrimeriSkip {

    public static void main(String[] args) {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofMillis(100));
/*
        intervalFlux
                .skip(Duration.ofSeconds(5))
                .subscribe(
                        val -> System.out.println("Received: " + val),
                        err -> {},
                        () -> System.out.println("Stream timed out and completed!")
                );
*/
 //      intervalFlux.skipUntil(i->i==30).subscribe(System.out::println);

        Flux<Long> livePrices = Flux.interval(Duration.ofMillis(200));

        // priprema ke[a
        Mono<String> cacheWarmup = Mono.delay(Duration.ofSeconds(1))
                .map(d -> "Cache Ready!");

        livePrices
                .skipUntilOther(cacheWarmup)
                .subscribe(price -> System.out.println("Processing Price Tick: " + price));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
