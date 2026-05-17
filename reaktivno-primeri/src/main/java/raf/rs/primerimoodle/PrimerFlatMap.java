package raf.rs.primerimoodle;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PrimerFlatMap {

    public static void main(String[] args) {
        Flux<Integer> fluxInt = Flux.just(1, 2, 3);
        List<Integer> rez = new ArrayList<>();
        fluxInt.flatMap(i->transformAsyncPublisherDelay(i)).subscribe(i->rez.add(i));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(rez);

    }

    private static Publisher<Integer> transformAsyncPublisherDelay(int i) {
        return i<=2
                ? Flux.just(i*100)
                  .delayElements(Duration.ofMillis(1))
                : Mono.just(i*2);
    }


}
