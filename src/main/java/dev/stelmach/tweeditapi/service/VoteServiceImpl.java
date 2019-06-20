package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.database.VoteRepository;
import dev.stelmach.tweeditapi.entity.User;
import dev.stelmach.tweeditapi.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public List<Vote> getVotedByUser(User user) {
        return voteRepository.findAllByUser(user);
    }

}
