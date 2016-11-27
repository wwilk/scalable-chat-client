package wilk.chat.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getAllMessages(){
        return messageService.findAll();
    }

    @RequestMapping(value = "/{senderId}/{receiverId}/{content}", method = RequestMethod.GET)
    public Message createIncoming(@PathVariable String senderId, @PathVariable String receiverId,
                                  @PathVariable String content){
        Message message = new Message();
        message.setContent(content);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setCreated(new Date());
        return messageService.create(message);
    }
}
