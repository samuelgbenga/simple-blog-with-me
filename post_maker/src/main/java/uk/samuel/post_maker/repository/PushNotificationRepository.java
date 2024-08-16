package uk.samuel.post_maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.samuel.post_maker.entity.PushNotification;

public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {

    boolean existsByToken(String token);

}
