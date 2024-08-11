package uk.samuel.post_maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.samuel.post_maker.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByEmail(String email);
}
