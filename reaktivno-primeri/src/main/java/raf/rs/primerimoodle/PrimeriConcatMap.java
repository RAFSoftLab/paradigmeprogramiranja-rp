package raf.rs.primerimoodle;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PrimeriConcatMap {

    public static void main(String[] args) {
        Flux.just("Spori", "Brzi")
                .flatMap(tekst -> {
                    if (tekst.equals("Spori")) {
                        return Mono.just(tekst).delayElement(Duration.ofMillis(300));
                    }
                    return Mono.just(tekst).delayElement(Duration.ofMillis(50));
                })
                .subscribe(System.out::println);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
