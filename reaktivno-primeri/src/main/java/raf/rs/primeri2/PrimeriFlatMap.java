package raf.rs.primeri2;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.Flow;

public class PrimeriFlatMap {
    public static void main(String[] args) {

        Mono.just(5).flatMap(i->asyncTransformation(i))
                .map(i->i*10).subscribe(System.out::println);

        Flux<Integer> fluxInt = Flux.just(1, 2, 3, 4);
        Flux<String> fluxFlat = fluxInt.flatMap(i -> transformAsyncMono(i));
        //fluxFlat.subscribe(System.out::println);

        Flux<Integer> combo = fluxInt.flatMap(i->transformAsyncPublisher(i));
      //  combo.subscribe(System.out::println);

        fluxInt.flatMap(i->transformAsyncPublisherDelay(i)).subscribe(System.out::println);
        fluxInt.flatMapSequential(i->transformAsyncPublisherDelay(i)).subscribe(System.out::println);


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Mono<String> transformAsyncMono(int i) {
        return i % 2 == 0 ? Mono.just("paran") : Mono.just("neparan");
    }

    private static Mono<Integer> asyncTransformation(int i) {
        return Mono.just(i*2);
    }

    private static Publisher<Integer> transformAsyncPublisher(int i) {
        return i % 2 == 0 ? Flux.just(i, i+1) : Mono.just(i * 10);
    }

    private static Publisher<String> transformAsyncPublisherDelay(int i) {
        return i % 2 == 0
                ? Flux.just("paran", "kasni")
                  .delayElements(Duration.ofMillis(1))
                : Mono.just("neparan");
    }
}
