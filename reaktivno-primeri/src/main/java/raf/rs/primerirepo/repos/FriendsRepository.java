package raf.rs.primerirepo.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import raf.rs.primerirepo.model.Friends;
import reactor.core.publisher.Flux;

public interface FriendsRepository extends ReactiveCrudRepository<Friends,Long> {

    Flux<Friends> findAllByUser1Id(Long user1Id);

    Flux<Friends> findAllByUser2Id(Long user1Id);
}
