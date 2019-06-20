package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.entity.User;
import dev.stelmach.tweeditapi.entity.Vote;

import java.util.List;

public interface VoteService {

    void saveVote(Vote vote);

    List<Vote> getVotedByUser(User user);

}
