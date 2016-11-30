package wilk.chat.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wilk.chat.client.user.UserService;

import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;

    public Message create(Message message){
        userService.createUserIfNotExists(message.getReceiverId());
        userService.createUserIfNotExists(message.getSenderId());
        messageRepository.create(message);
        return message;
    }

    public List<Message> findAllForContact(String contact) {
        return messageRepository.findAllForContact(contact);
    }
}
