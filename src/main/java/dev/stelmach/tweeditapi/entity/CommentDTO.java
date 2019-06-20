package dev.stelmach.tweeditapi.entity;

import java.util.List;

public class CommentDTO {

    private Comment comment;
    private Vote vote;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment, Vote vote) {
        this.comment = comment;
        this.vote = vote;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public CommentDTO addUserVote(List<Vote> userVotes) {
        userVotes.stream()
                .filter(vote -> vote.isVoteForComment(this))
                .findFirst().ifPresent(this::setVote);
        return this;
    }
}
