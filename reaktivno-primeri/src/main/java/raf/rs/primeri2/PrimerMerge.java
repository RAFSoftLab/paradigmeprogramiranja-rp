package raf.rs.primeri2;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;

public class PrimerMerge {

    public static void main(String[] args) {
        Flux<Integer> flux1 = Flux.just(1, 2, 3);
        Flux<Integer> flux2 = Flux.just(4, 5, 6);

        Flux<Integer> mergedFlux = Flux.merge(flux1, flux2);
        //mergedFlux.subscribe(System.out::println);

        Flux<Integer> fluxWithDelay = transformAsyncPublisherDelay(6);
        Flux<Integer> mergedFlux1 = Flux.merge(fluxWithDelay, flux1, flux2);
        mergedFlux1.subscribe(System.out::println);

        //fluxWithDelay.mergeWith(flux1).mergeWith(flux2).subscribe(System.out::println);

        Flux<Integer> mergedSorted = Flux.mergeComparing(Collections.reverseOrder(),fluxWithDelay, flux1, flux2);
      //  mergedSorted.subscribe(System.out::println);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Flux<Integer> transformAsyncPublisherDelay(int i) {
        return Flux.just(i*1000,i*2000,i*3000)
                  .delayElements(Duration.ofMillis(1));
    }
}
