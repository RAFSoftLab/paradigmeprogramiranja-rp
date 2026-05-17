package raf.rs.primerirepo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import raf.rs.primerirepo.model.User;
import raf.rs.primerirepo.repos.UserRepository;
import raf.rs.primerirepo.service.SocialNetworkService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class TestReactiveApp {

    public static void main(String[] args) {
        SpringApplication.run(TestReactiveApp.class, args);
    }

    @Bean
    public CommandLineRunner demo(SocialNetworkService service) {
        return (args) -> {
            //service.printDifferentDataAboutDatabseState();        };
/*
            service.getFriendsForUser(13L)
                    .doFirst(()-> System.out.println("Prijatelji usera sa id=13:"))
                    .subscribe(System.out::println);
            service.getFriendsForUser(7L)
                    .doFirst(()-> System.out.print("Prijatelji usera sa id=7:"))
                    .subscribe(System.out::println);

            service.getMutualFriends(7L, 13l)
                    .doFirst(()-> System.out.println("Zajednicki prijatelji za 7 i 13"))
                    .subscribe(System.out::println);
                    */


            service.lastNMessages(12l,2).subscribe(System.out::println);

        };
    }
}
