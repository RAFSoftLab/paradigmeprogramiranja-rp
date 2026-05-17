package raf.rs.primeri2;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PrimerConcatMap {

    public static void main(String[] args) {

        Flux<Integer> fluxInt = Flux.range(1,10);
        fluxInt.concatMap(i->transformAsyncPublisherDelay(i)).subscribe(s->System.out.println(s));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




        /*
        Flux<Integer> fluxInt = Flux.range(1,10);
        long startTime = System.nanoTime();
        fluxInt.concatMap(i->transformAsyncPublisherDelay(i)).subscribe(s->System.out.println(s));
        long stopTime = System.nanoTime();
        System.out.println("concatMapTime:" + (stopTime - startTime));

        startTime = System.nanoTime();
        fluxInt.flatMapSequential(i->transformAsyncPublisherDelay(i)).subscribe(s->System.out.println(s));
        stopTime = System.nanoTime();
        System.out.println("flatMapSequential:" + (stopTime - startTime));


        */

    }

    private static Publisher<String> transformAsyncPublisherDelay(int i) {
        return i % 2 == 0
                ? Flux.just("paran", "kasni")
                  .delayElements(Duration.ofMillis(1))
                : Mono.just("neparan");
    }
}
