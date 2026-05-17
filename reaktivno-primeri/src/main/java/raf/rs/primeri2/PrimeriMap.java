package raf.rs.primeri2;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

public class PrimeriMap {
    public static void main(String[] args) {
        Function<String,LocalDate> func1 = transformatorFromPattern("yyyy-MM-dd");
        Mono<String> monoString = Mono.just("2022-01-01");
        Mono<LocalDate> monoDate = monoString.map(func1);
        monoDate.subscribe(d-> System.out.println(d.getYear()),
                e->System.err.println("Neispravan format datuma"));
        Function<String,LocalDate> func2 = transformatorFromPattern("dd.MM.yyyy");
        Flux<String> fluxString = Flux.just("23.05.2025","21.03.2036","10.04.2026","12.01.2026");
        Flux<LocalDate> fluxDate = fluxString.map(func2);
        fluxDate.subscribe(d-> System.out.println(d.getYear()));

        // asinhrona funkcija u map ?
        Mono.just(5).map(i->asyncTransformation(i)); // šta dalje možemo da mapiramo?

    }


    private static Function<String,LocalDate> transformatorFromPattern(String pattern){
        return s->LocalDate.parse(s, DateTimeFormatter.ofPattern(pattern,Locale.ENGLISH));
    }

    private static Mono<Integer> asyncTransformation(int i) {
        return Mono.just(i*2);
    }
}
