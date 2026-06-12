package raf.rs.primeripraksaSI;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class PrakticniSaScan {

    public static void main(String[] args) {
        // dinamicko kreranje where uslova u SQL upitu, kriterijumi za pretragu stuzu u toku

        Flux<String> filteriSaUi = Flux.just("brend = 'Nike'", "cena < 100", "velicina = 'M'");

        filteriSaUi
                .scanWith(()->"SELECT * FROM proizvodi WHERE",(prethodniUpit, noviFilter) -> prethodniUpit + " AND " + noviFilter)
                .subscribe(System.out::println);

        /*
           Flux<String> filteriSaUi = Flux.just("brend = 'Nike'", "cena < 100", "velicina = 'M'");

            filteriSaUi
                .scan((prethodniUpit, noviFilter) -> prethodniUpit + " AND " + noviFilter)
                .map(uslovi -> "SELECT * FROM proizvodi WHERE " + uslovi)
                .subscribe(System.out::println);
         */

        // racunanje tekuceg proseka temperature procesora

        class Stat {
            int brojMerenja = 0;
            double ukupanZbir;
            Stat(double vrednost) { this.ukupanZbir = vrednost; }
        }

        Flux<Double> cpuLoadStream = Flux.just(20.0, 80.0, 35.0, 90.0);



        cpuLoadStream
                .scan(new Stat(0.0),(stat, trenutniCpu) -> {
                    stat.brojMerenja++;
                    stat.ukupanZbir += trenutniCpu;
                    return stat;
                })
                .skip(1)
                .map(stat -> stat.ukupanZbir / stat.brojMerenja) // prosek
                .subscribe(prosek -> System.out.println("Tekući prosek CPU-a: " + prosek));

        // detekcija manjeg razmaka izmedju nekih dogadjaja u apliakciji (na primer klikova)

        class Klik {
            final long timestamp = System.currentTimeMillis();
        }

        Random random = new Random();

        Flux<Klik> tokKlikova = Flux.range(1, 10)
                .map(i -> new Klik())
                // 2. SIMULACIJA PAUZE: Svaki klik odlažemo za nasumičan broj ms (izmedju 100 i 900)
                .delayUntil(klik -> {
                    int nasumicnaPauza = random.nextInt(800) + 100; // 100ms do 900ms
                    return Flux.interval(Duration.ofMillis(nasumicnaPauza)).next();
                });


        tokKlikova
                .scan((prethodni, trenutni) -> {
                    long razlika = trenutni.timestamp - prethodni.timestamp;
                    if (razlika < 500) {
                        System.out.println("Detektovan brzi dvoklik! Razmak: " + razlika + "ms");
                    }
                    return trenutni; // Trenutni klik postaje "prethodni" za sledeći krug
                })
                .subscribe();




        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}


