package raf.rs.primeripraksaSI;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Random;

public class AerodromSemafor {

    static class Let {
        final String brojLeta;
        final String poreklo;
        final String tip;

        Let(String brojLeta, String poreklo, String tip) {
            this.brojLeta = brojLeta;
            this.poreklo = poreklo;
            this.tip = tip;
        }

        @Override
        public String toString() {
            return String.format("✈️ [%s] Let %s iz mesta: %s je SLETEO!", tip, brojLeta, poreklo);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // 1. Strim domaćih letova (sleću na svakih 400ms do 1200ms)
        Flux<Let> domaciLetovi = Flux.just(
                new Let("JU102", "Niš", "DOMAĆI"),
                new Let("JU104", "Kraljevo", "DOMAĆI")
        ).delayUntil(l -> Flux.interval(Duration.ofMillis(random.nextInt(800) + 400)).next());

        // 2. Strim međunarodnih letova (sleću na svakih 200ms do 600ms - gušći saobraćaj)
        Flux<Let> medjunarodniLetovi = Flux.just(
                new Let("LH1410", "Frankfurt", "INT"),
                new Let("JU381", "London", "INT"),
                new Let("TK1081", "Istanbul", "INT")
        ).delayUntil(l -> Flux.interval(Duration.ofMillis(random.nextInt(400) + 200)).next());

        // 3. Strim VIP letova (slabo saobraćaju, ali mogu da ulete bilo kada)
        Flux<Let> vipLetovi = Flux.just(
                new Let("VIP001", "Monako", "VIP"),
                new Let("VIP007", "Cirih", "VIP")
        ).delayUntil(l -> Flux.interval(Duration.ofMillis(random.nextInt(1500) + 500)).next());


        // 4. SPAJANJE: Svi terminali šalju podatke na istu pistu (isti semafor)
        System.out.println("--- DETEKCIJA LETOVA (MERGE) ---");





        Flux.merge(domaciLetovi, medjunarodniLetovi, vipLetovi)
                .subscribe(let -> System.out.println(let));


        // Držimo aplikaciju upaljenom 5 sekundi da ispratimo sletanja
        Thread.sleep(5000);
    }
}