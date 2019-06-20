package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.entity.Message;
import dev.stelmach.tweeditapi.entity.User;

import java.util.List;

public interface MessageService {

    List<Message> getMessagesByToUser(User user);

    int getUnreadMessagesByUser(User user);

    Message getMessageById(Long id);

    void saveMessage(Message message);

}
