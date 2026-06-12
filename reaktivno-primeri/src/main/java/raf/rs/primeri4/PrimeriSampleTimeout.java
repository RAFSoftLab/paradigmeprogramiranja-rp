package raf.rs.primeri4;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PrimeriSampleTimeout {

    public static void main(String[] args) {
        Flux<Integer> intervalFlux = Flux.range(0,50).delayElements(Duration.ofMillis(20));
        intervalFlux
                .sampleTimeout(x-> Mono.delay(Duration.ofMillis(40)))
                .subscribe(System.out::println);




        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
