package raf.rs.primerimoodle;

import reactor.core.publisher.Flux;

public class PrimerSubscriber {

    public static void main(String[] args) {
            Flux<String> strs = Flux.just("aa","bb","ab","bc","ce");

            strs.take(3).subscribe(new MojSubscriber<>(2));
    }
}
