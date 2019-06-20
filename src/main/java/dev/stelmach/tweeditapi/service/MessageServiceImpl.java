package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.database.MessageRepository;
import dev.stelmach.tweeditapi.entity.Message;
import dev.stelmach.tweeditapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public List<Message> getMessagesByToUser(User user) {
        return messageRepository.getMessagesByToUser(user);
    }

    public int getUnreadMessagesByUser(User user) {
        return (int) getMessagesByToUser(user).stream()
                .filter(m -> m.getMessageRead() == 0)
                .count();

    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
