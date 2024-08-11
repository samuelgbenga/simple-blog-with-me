package uk.samuel.post_maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.samuel.post_maker.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByPostTitle(String postTitle);

}
