package raf.rs.primeripraksaSI;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PozivServisa {

    public static void main(String[] args) {
        // za unos u tekstualno polje se poziva servis, necemo da zovemo za svaki unos nego za veci broj unetih karaktera, kada se yastane sa unosom
        Flux<String> inputText = Flux.just("r", "re", "rea", "reak", "reakt", "reakti", "reaktiv", "reaktivn", "reaktivno")
                .delayElements(Duration.ofMillis(30));


        inputText
                .sampleTimeout(item -> Mono.delay(Duration.ofMillis(60)))
                .flatMap(item -> pozivServisa(item))
                .subscribe(System.out::println);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Mono<String> pozivServisa(String input){
        System.out.println("Pozvan servis za ulaz: "+input);
        return Mono.just("rez");
    }
}