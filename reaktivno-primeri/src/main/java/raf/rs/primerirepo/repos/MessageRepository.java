package raf.rs.primerirepo.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import raf.rs.primerirepo.model.Message;
import reactor.core.publisher.Flux;

public interface MessageRepository extends ReactiveCrudRepository<Message,Long> {
    Flux<Message> findAllBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
