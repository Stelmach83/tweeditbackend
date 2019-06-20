package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.entity.Comment;
import dev.stelmach.tweeditapi.entity.Post;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> getAllComments();

    List<Comment> allAllFromNewestForPost(Post post);

    List<Comment> getAllOrderByDate();

    Comment getCommentById(Long id);

}
