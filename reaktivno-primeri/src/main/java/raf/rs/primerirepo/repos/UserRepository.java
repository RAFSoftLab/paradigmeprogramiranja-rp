package raf.rs.primerirepo.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import raf.rs.primerirepo.model.User;

public interface UserRepository extends ReactiveCrudRepository<User,Long> {
}
