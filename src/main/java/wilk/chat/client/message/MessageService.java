package wilk.chat.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wilk.chat.client.user.UserService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MessageRemoteService messageRemoteService;


    public Message create(Message message){
        Message remoteMessage = messageRemoteService.createMessage(message);
        messageRepository.create(remoteMessage);
        return remoteMessage;
    }

    public void pollForMessages(){
        messageRemoteService.getMessages()
                .forEach(messageRepository::create);
    }

    public List<Message> findAllForContact(String contact) {
        return messageRepository.findAllForContact(contact);
    }
}
