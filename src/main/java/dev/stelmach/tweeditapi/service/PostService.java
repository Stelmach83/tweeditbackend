package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.entity.Category;
import dev.stelmach.tweeditapi.entity.Post;
import dev.stelmach.tweeditapi.entity.User;

import java.util.List;
import java.util.Set;

public interface PostService {

    void savePost(Post post);

    List<Post> getAllPosts();

    List<Post> getAllFromNewest();

    Post getPostById(Long id);

    List<Post> getPostsForUserBySubsCats(Set<Category> categories);

    List<Post> getPostsByCat(Category category);

    List<Post> getPostForUsersSubs(List<User> userList);

    List<Post> getPostsByFollowedCatsAndUsers(Set<Category> categories, Set<User> userList);

}
