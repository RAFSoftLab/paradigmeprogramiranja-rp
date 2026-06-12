package raf.rs.primeri4;

import reactor.core.publisher.Flux;

public class PrimeriScan {

    public static void main(String[] args) {
        Flux<Integer> intervalFlux = Flux.range(1,50);
        intervalFlux.scan((x,y)->x+y).subscribe(System.out::println);

        // Cene stavki dodatih u korpu
        Flux<Double> itemPrices = Flux.just(10.99, 5.50, 23.00);

        itemPrices
                .scanWith(() -> 0.0, (currentTotal, nextPrice) -> currentTotal + nextPrice)
                .subscribe(liveTotal -> System.out.println("Cart Total: $" + liveTotal));
    }
}
