package raf.rs.primeripraksaSI;

import reactor.core.publisher.Flux;

public class ScanTransakcije {

        // Model transakcije
        static class Transakcija {
            final double iznos;
            final String rizik; // "HIGH_RISK" ili "LOW_RISK"

            Transakcija(double iznos, String rizik) {
                this.iznos = iznos;
                this.rizik = rizik;
            }
        }

        // Klasa koja čuva trenutno stanje računa unutar scan operatora
        static class StanjePrevare {
            double akumuliraniIznos = 0;
            boolean blokirajKarticu = false;

            StanjePrevare(double iznos, boolean blokiraj) {
                this.akumuliraniIznos = iznos;
                this.blokirajKarticu = blokiraj;
            }
        }

        public static void main(String[] args) {
            // Strim transakcija koje korisnik pravi jednu za drugom
            Flux<Transakcija> strimPlacanja = Flux.just(
                    new Transakcija(400, "HIGH_RISK"),  // 1. transakcija
                    new Transakcija(200, "LOW_RISK"),   // 2. transakcija
                    new Transakcija(600, "HIGH_RISK"),  // 3. transakcija
                    new Transakcija(500, "HIGH_RISK"),  // 4. transakcija
                    new Transakcija(100, "HIGH_RISK")   // 5. transakcija
            );

            strimPlacanja
                    // Kompleksni scan koji pamti i računa stanje prevare
                    .scan(new StanjePrevare(0, false), (staroStanje, novaTransakcija) -> {
                        // Ako je kartica već blokirana, stanje ostaje blokirano
                        if (staroStanje.blokirajKarticu) {
                            return staroStanje;
                        }

                        // Ako uleti sigurna transakcija, resetujemo sumnjivi iznos na 0!
                        if (novaTransakcija.rizik.equals("LOW_RISK")) {
                            return new StanjePrevare(0, false);
                        }

                        // Ako je HIGH_RISK, dodajemo iznos na prethodni sumnjivi iznos
                        double noviIznos = staroStanje.akumuliraniIznos + novaTransakcija.iznos;
                        boolean trebaBlokirati = noviIznos > 1000;

                        return new StanjePrevare(noviIznos, trebaBlokirati);
                    })
                    // Preskačemo početno prazno stanje koje scan emituje na samom startu
                    .skip(1)
                    // Mapiramo stanje u jasnu poruku za konzolu
                    .map(stanje -> stanje.blokirajKarticu ? "BLOKIRANO!" : "Prošlo (Sumnjiva suma: $" + stanje.akumuliraniIznos + ")")
                    .subscribe(System.out::println);
        }
    }
