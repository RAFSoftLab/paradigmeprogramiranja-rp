package raf.rs.primerirepo.service;

import org.springframework.stereotype.Service;
import raf.rs.primerirepo.model.Friends;
import raf.rs.primerirepo.model.Message;
import raf.rs.primerirepo.model.User;
import raf.rs.primerirepo.repos.FriendsRepository;
import raf.rs.primerirepo.repos.MessageRepository;
import raf.rs.primerirepo.repos.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class SocialNetworkService {

    private final UserRepository userRepo;
    private final FriendsRepository friendsRepo;
    private final MessageRepository messageRepo;

    public SocialNetworkService(UserRepository userRepo, FriendsRepository friendsRepo, MessageRepository messageRepo) {
        this.userRepo = userRepo;
        this.friendsRepo = friendsRepo;
        this.messageRepo = messageRepo;
    }

    public void printDifferentDataAboutDatabseState(){
        Mono<Long> userCount = userRepo.findAll().count();
        userCount.subscribe(System.out::println);
    }


    // 1. sačuvati novog usera i odmah preuzeti sve usere i ispisati ih
    public void saveNewUserAndPrint(){
        userRepo.save(new User("jdoe", "john@example.com"))
                .thenMany(userRepo.findAll())
                .subscribe(user -> {
                    System.out.println("Found User: " + user);
                });
        // ko će se sabskrajbovati u realnoj Spring aplikaciji?
    }

    // 2. vrati sve prijatelje (User) za korisnika sa prosledjenim id-jem

    public Flux<User> getFriendsForUser(Long userId) {
        return friendsRepo.findAllByUser1Id(userId)
                .map(Friends::getUser2Id)
                .mergeWith(friendsRepo.findAllByUser2Id(userId).map(Friends::getUser1Id))
                .flatMap(userRepo::findById);
    }


    // pronadji zajednicke prijatelje za dva korisnika

    public Flux<User> getMutualFriends(Long userA, Long userB) {
        return getFriendsForUser(userA)
                .flatMap(f1->getFriendsForUser(userB).filter(f2->f1.equals(f2)));

    }


    // poslednjih n poruka od svakog prijatelja za korisnika za prosledjeni id

    public Flux<Message> lastNMessages(Long userId, int n) {
        return getFriendsForUser(userId)
                .flatMap(f->messageRepo.findAllBySenderIdAndReceiverId(f.getId(),userId))
                .sort(Comparator.comparing(Message::getTimestamp).reversed())
                .take(n);


    }

}
