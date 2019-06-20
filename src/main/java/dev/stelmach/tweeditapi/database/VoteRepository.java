package dev.stelmach.tweeditapi.database;

import dev.stelmach.tweeditapi.entity.User;
import dev.stelmach.tweeditapi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByUser(User user);

}
