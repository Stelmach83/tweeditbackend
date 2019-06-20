package dev.stelmach.tweeditapi.database;

import dev.stelmach.tweeditapi.entity.Message;
import dev.stelmach.tweeditapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getMessagesByToUser(User user);

}
