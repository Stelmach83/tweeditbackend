package dev.stelmach.tweeditapi.database;

import dev.stelmach.tweeditapi.entity.Category;
import dev.stelmach.tweeditapi.entity.Post;
import dev.stelmach.tweeditapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDesc();

    List<Post> findAllByCategoryInOrderByCreatedDesc(Set<Category> categories);

    List<Post> findAllByUserInOrderByCreatedDesc(List<User> users);

    List<Post> findAllByCategoryOrderByCreatedDesc(Category category);

    List<Post> findAllByCategoryInOrUserInOrderByCreatedDesc(Set<Category> categories, Set<User> users);

}
