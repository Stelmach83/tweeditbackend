package dev.stelmach.tweeditapi.database;

import dev.stelmach.tweeditapi.entity.Comment;
import dev.stelmach.tweeditapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getAllByPostOrderByDateAsc(Post post);

    List<Comment> findAllByOrderByDateDesc();

}
