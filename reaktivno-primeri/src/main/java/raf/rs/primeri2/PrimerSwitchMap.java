package raf.rs.primeri2;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


public class PrimerSwitchMap {

    public static void main(String[] args) {
        Flux<Integer> fluxInt = Flux.just(2,3,6,5,7,8,10,12,14);
        fluxInt.switchMap(i->transformAsyncPublisherDelay(i)).subscribe(s->System.out.println(s));


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static Publisher<String> transformAsyncPublisherDelay(int i) {
        return i % 2 == 0
                ? Flux.just(String.valueOf(i),"paran","kasni")
                             .delayElements(Duration.ofMillis(1))
                : Mono.just(i+" neparan");
    }
}

