package raf.rs.primeri4;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class PrimeriSample {

    public static void main(String[] args) {

        Flux<Integer> intervalFlux = Flux.range(0,50).delayElements(Duration.ofMillis(20));
        intervalFlux.sample(Duration.ofMillis(100)).subscribe(System.out::println);

        Flux<Integer> otherIntervalFlux = Flux.range(2000,10).delayElements(Duration.ofMillis(50));
        //otherIntervalFlux.subscribe(System.out::println);
       // intervalFlux.sample(otherIntervalFlux).subscribe(System.out::println);





        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
