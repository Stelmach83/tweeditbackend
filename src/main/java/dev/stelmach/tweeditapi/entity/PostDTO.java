package dev.stelmach.tweeditapi.entity;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {

    private Post post;
    private Vote vote;
    private List<CommentDTO> comments;

    public PostDTO() {
    }

    public PostDTO(Post post, Vote vote, List<CommentDTO> comments) {
        this.post = post;
        this.vote = vote;
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public PostDTO addVote(List<Vote> userVotes) {
        userVotes.stream()
                .filter(vote -> vote.isVoteForPost(this))
                .findFirst().ifPresent(this::setVote);
        return this;
    }

    public PostDTO addComments() {
        List<Comment> postCommments = this.post.getComments();
        List<CommentDTO> buildComments = new ArrayList<>();
        if (postCommments != null) {
            for (Comment c : postCommments) {
                buildComments.add(Comment.mapToCommentDTO(c));
            }
        }
        comments = buildComments;
        return this;
    }

    public Post getPostFromDto() {
        return this.post;
    }

}
